package com.desafiofinal.praticafinal.service;

import com.desafiofinal.praticafinal.dto.BatchStockDTO;
import com.desafiofinal.praticafinal.dto.InboundOrderRequestDTO;
import com.desafiofinal.praticafinal.dto.SectorDTO;
import com.desafiofinal.praticafinal.model.*;
import com.desafiofinal.praticafinal.repository.IBatchStockRepo;
import com.desafiofinal.praticafinal.repository.IProductRepo;
import com.desafiofinal.praticafinal.repository.ISectorRepo;
import com.desafiofinal.praticafinal.repository.InBoundOrderRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InBoundOrderImpServiceTest {

    @Mock
    private InBoundOrderRepo inBoundOrderRepo;

    @Mock
    private IBatchStockRepo batchStockRepo;

    @Mock
    private ISectorRepo sectorRepo;

    @Mock
    private IProductRepo productRepo;

    @Captor
    ArgumentCaptor<InBoundOrder> captor;

    @Test
    void saveInBoundOrder() throws Exception {

        var manager = new Manager(1L, "AMANDA", null);
        var wareHouse =  new WareHouse(1L, manager, Collections.emptyList());
        var sector = new Sector(1L, "Carne",
                12,
                Collections.emptyList(),
                wareHouse );
        var inBoundOrder = new InBoundOrder(1L, new Date(), Collections.emptyList(), sector );
        var seller = new Seller(1L, "TESTE", Collections.emptySet());
        var sectorDTO = new SectorDTO(sector);

        when(inBoundOrderRepo.findById(anyLong())).thenReturn(Optional.empty());
        when(sectorRepo.findById(anyLong())).thenReturn(Optional.of(sector));
        when(inBoundOrderRepo.save(any(InBoundOrder.class))).thenReturn(inBoundOrder);

        var service = new InBoundOrderImpService(inBoundOrderRepo, batchStockRepo, sectorRepo, productRepo);
        var inboundOrderCreated =  service.saveInBoundOrder(new InboundOrderRequestDTO(20L, new Date(), sectorDTO,Collections.emptyList() ));

        Assertions.assertEquals(inBoundOrder.getOrderId(), inboundOrderCreated.getOrderId());
        Assertions.assertEquals(inBoundOrder.getDateTime(), inboundOrderCreated.getDateTime());
        Assertions.assertEquals(inBoundOrder.getBatchStockList(), inboundOrderCreated.getBatchStockList());
        Assertions.assertEquals(inBoundOrder.getSector(), inboundOrderCreated.getSector());

    }

    @Test
    void updateInBoundOrder() throws Exception {
        var manager = new Manager(1L, "AMANDA", null);
        var wareHouse =  new WareHouse(1L, manager, Collections.emptyList());
        var sector = new Sector(1L, "Carne", 12, Collections.emptyList(), wareHouse );
        var seller = new Seller(1L, "TESTE", Collections.emptySet());
        var sectorDTO = new SectorDTO(sector);
        var inboundOrder = new InBoundOrder(1L,new Date() , Collections.emptyList(), sector );
        var product = new Product(1L, new Date(), 13, "frios", "frango", seller, 13, Collections.emptyList());
        var batchStock = new BatchStock(1L, 12, 14, 20L, 7L, new Date(), new Date(), new Date(), inboundOrder, product );
        List<BatchStockDTO> batchStockListDTO= Collections.singletonList(new BatchStockDTO(batchStock));
        var requestDTO = new InboundOrderRequestDTO(1L, new Date(), sectorDTO, batchStockListDTO);

        when(inBoundOrderRepo.findById(anyLong())).thenReturn(Optional.of(inboundOrder));
        when(sectorRepo.findById(anyLong())).thenReturn(Optional.of(sector));
        when(productRepo.findById(anyLong())).thenReturn(Optional.of(product));
        when(batchStockRepo.findById(anyLong())).thenReturn(Optional.of(batchStock));
        when(batchStockRepo.save(any(BatchStock.class))).thenReturn(batchStock);
        when(inBoundOrderRepo.save(any(InBoundOrder.class))).thenReturn(inboundOrder);
        var service = new InBoundOrderImpService(inBoundOrderRepo, batchStockRepo, sectorRepo, productRepo);

        var inboundOrderUpdated =  service.updateInBoundOrder(requestDTO);

        Assertions.assertNotNull(inboundOrderUpdated);
        verify(inBoundOrderRepo).save(captor.capture());

        var batchStockCaptured = captor.getValue().getBatchStockList().get(0);

        Assertions.assertEquals(inboundOrder.getOrderId(), captor.getValue().getOrderId());
        Assertions.assertEquals(sector, captor.getValue().getSector());
        Assertions.assertEquals(batchStock.getBatchId(), batchStockCaptured.getBatchId());
        Assertions.assertEquals(batchStock.getCurrentQuantity(), batchStockCaptured.getCurrentQuantity());
        Assertions.assertEquals(batchStock.getInitialQuantity(), batchStockCaptured.getInitialQuantity());
        Assertions.assertEquals(batchStock.getMinimumTemperature(), batchStockCaptured.getMinimumTemperature());
    }
}