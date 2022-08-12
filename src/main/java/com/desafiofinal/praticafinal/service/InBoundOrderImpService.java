package com.desafiofinal.praticafinal.service;

import com.desafiofinal.praticafinal.dto.BatchStockDTO;
import com.desafiofinal.praticafinal.dto.InBoundOrderResponseDTO;
import com.desafiofinal.praticafinal.dto.InboundOrderRequestDTO;
import com.desafiofinal.praticafinal.exception.ElementNotFoundException;
import com.desafiofinal.praticafinal.exception.ElementeAlreadyExistsException;
import com.desafiofinal.praticafinal.model.BatchStock;
import com.desafiofinal.praticafinal.model.InBoundOrder;
import com.desafiofinal.praticafinal.model.Product;
import com.desafiofinal.praticafinal.model.Sector;
import com.desafiofinal.praticafinal.repository.IBatchStockRepo;
import com.desafiofinal.praticafinal.repository.IProductRepo;
import com.desafiofinal.praticafinal.repository.ISectorRepo;
import com.desafiofinal.praticafinal.repository.InBoundOrderRepo;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;

@Service
public class InBoundOrderImpService implements IinboundOrderService {

    private InBoundOrderRepo inBoundOrderRepo;
    private IBatchStockRepo batchStockRepo;
    private ISectorRepo sectorRepo;
    private IProductRepo productRepo;

    public InBoundOrderImpService(InBoundOrderRepo inBoundOrderRepo, IBatchStockRepo batchStockRepo, ISectorRepo sectorRepo, IProductRepo productRepo) {
        this.inBoundOrderRepo = inBoundOrderRepo;
        this.batchStockRepo = batchStockRepo;
        this.sectorRepo = sectorRepo;
        this.productRepo = productRepo;
    }

    @Transactional
    public InBoundOrder saveInBoundOrder (InboundOrderRequestDTO inboundOrderRequestDTO) throws Exception {
        var inBoundOrder = buildInboundOrder(inboundOrderRequestDTO, true);
        var batchStock = buildBatchStockList(inboundOrderRequestDTO, inBoundOrder);
        inBoundOrder.setBatchStockList(batchStock);
        return inBoundOrderRepo.save(inBoundOrder);
    }


    public InBoundOrder updateInBoundOrder (InboundOrderRequestDTO inBoundOrderRequestDTO) throws Exception {
        var inBoundOrder = buildInboundOrder(inBoundOrderRequestDTO, false);

        List<BatchStock> batchList = buildBatchStockList(inBoundOrderRequestDTO, inBoundOrder);
        verifyBatchStock(batchList, inBoundOrder);
        inBoundOrder.setBatchStockList(batchList);
        return inBoundOrderRepo.save(inBoundOrder);

    }

    private InBoundOrder buildInboundOrder(InboundOrderRequestDTO dto, boolean isCreating){
        Optional<InBoundOrder> foundInBoundOrder = inBoundOrderRepo.findById(dto.getOrderId());
        if(foundInBoundOrder.isPresent() && isCreating)
            throw new ElementeAlreadyExistsException("Order does already exist");
        if(foundInBoundOrder.isEmpty() && !isCreating)
            throw new ElementNotFoundException("Order does not exist");
        var sector = getSector(dto.getSectorID());
        return new InBoundOrder(dto.getOrderId(), dto.getDateTime(), emptyList(), sector);
    }

    private Product getProduct(BatchStockDTO batchStockDTO) {
        return productRepo.findById(batchStockDTO.getProduct()).orElseThrow(() -> new RuntimeException("Product does ot exists"));
    }

    private Sector getSector(long sectorID) {
        return sectorRepo.findById(sectorID).orElseThrow(() -> new RuntimeException("Sector does not exists"));
    }

    private List<BatchStock> buildBatchStockList(InboundOrderRequestDTO inboundOrderRequestDTO, InBoundOrder inboundOrder) {
        return inboundOrderRequestDTO.getBatchStockList().stream().map(dto -> {
            Product product = getProduct(dto);
            return new BatchStock(dto, inboundOrder, product);
        }).collect(Collectors.toList());
    }

    private void verifyBatchStock(List<BatchStock> batchStockList, InBoundOrder inBoundOrder)  {
        for(BatchStock responseStock : batchStockList) {
            Optional<BatchStock> foundBatch = batchStockRepo.findById(responseStock.getBatchId());
            if(foundBatch.isPresent()) {
                if ((foundBatch.get().getInBoundOrder().getOrderId() == inBoundOrder.getOrderId())) {
                    responseStock.setInBoundOrder(inBoundOrder);
                    batchStockRepo.save(responseStock);
                } else {
                    throw new ElementNotFoundException("Batch stock does not belongs to this inbound order");
                }
            } else {
                throw new ElementNotFoundException("Batch stock does not exists");
            }
        }
    }
}


