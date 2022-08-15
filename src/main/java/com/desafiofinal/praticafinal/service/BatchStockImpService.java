package com.desafiofinal.praticafinal.service;

import com.desafiofinal.praticafinal.dto.queryDto.*;

import com.desafiofinal.praticafinal.exception.ElementAlreadyExistsException;
import com.desafiofinal.praticafinal.exception.ElementNotFoundException;
import com.desafiofinal.praticafinal.model.Sector;
import com.desafiofinal.praticafinal.model.WareHouse;
import com.desafiofinal.praticafinal.repository.IBatchStockRepo;
import com.desafiofinal.praticafinal.repository.IProductRepo;
import com.desafiofinal.praticafinal.repository.IWareHouseRepo;
import com.desafiofinal.praticafinal.repository.InBoundOrderRepo;
import org.aspectj.weaver.ast.Literal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.desafiofinal.praticafinal.model.BatchStock;
import com.desafiofinal.praticafinal.model.InBoundOrder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

@Service
public class BatchStockImpService implements IBatchStockService {

    private final InBoundOrderRepo inBoundOrderRepo;

    private final IProductRepo productRepo;

    private final IBatchStockRepo batchStockRepo;

    private final IWareHouseRepo wareHouseRepo;

    public BatchStockImpService(InBoundOrderRepo inBoundOrderRepo, IProductRepo productRepo, IBatchStockRepo batchStockRepo,IWareHouseRepo wareHouseRepo) {
        this.inBoundOrderRepo = inBoundOrderRepo;
        this.productRepo = productRepo;
        this.batchStockRepo = batchStockRepo;
        this.wareHouseRepo= wareHouseRepo;
    }

    public ResponseStock transferToSector (){
        ResponseStock responseStock = new ResponseStock();
        List<ResponseStockQuery> listStock = new ArrayList<>();
        List<BatchStock> batchStockList = batchStockRepo.findAll();

        Sector updateSector = buildWareHouse();

        locateBatchStock(listStock, batchStockList, updateSector);

        if(listStock.isEmpty()){
            throw new RuntimeException("There are no expired products");
        }
         responseStock.setDataBaseStocks(listStock);
        return responseStock;

    }

    private void locateBatchStock(List<ResponseStockQuery> listStock, List<BatchStock> batchStockList, Sector updateSector) {

        for(BatchStock batch: batchStockList) {
            LocalDate batchDate = batch.getDueDate();

            if(batchDate.isBefore(LocalDate.now())){
                batch.getInBoundOrder().setSector(updateSector);

                batchStockRepo.save(batch);

                ResponseStockQuery vencidos = ResponseStockQuery.builder()
                                    .productType(batch.getProduct().getProductType())
                                    .batchId(batch.getBatchId())
                                    .currentQuantity(batch.getCurrentQuantity())
                                    .idProduct(batch.getProduct().getId())
                                    .dueDate(batch.getDueDate())
                                    .build();
                listStock.add(vencidos);

            }
        }
    }

    private Sector buildWareHouse() {
        DataBaseExpired expiredSector = batchStockRepo.getSectorExpired();
        Optional<WareHouse> wareHouse;
        wareHouse = wareHouseRepo.findById(expiredSector.getId_warehouse());
        if(wareHouse.isPresent()) {
            System.out.println("warehouse:" + wareHouse);
            Sector updateSector = Sector.builder()
                    .maxCapacity(expiredSector.getMax_capacity())
                    .category(expiredSector.getCategory())
                    .wareHouse(wareHouse.get())
                    .sectorId(expiredSector.getSector_id())
                    .capacity(expiredSector.getCapacity())
                    .build();
            return updateSector;
        }
        else{
            throw new ElementNotFoundException("Warehouse not found");
        }

    }

    public String getFinantialLoss(String month){
        List<DataBaseExpiredQuantity> expiredQuantityList = batchStockRepo.getSectorExpiredQuantity(month);

        if (expiredQuantityList.isEmpty()){
            throw new ElementNotFoundException("There are no expired products this month");
        }
        double currentTotal = expiredQuantityList.stream().mapToDouble(c->c.getCurrent_quantity()).sum();
        double initialTotal = expiredQuantityList.stream().mapToDouble(c->c.getInitial_quantity()).sum();

        double finantialLoss = ((initialTotal - currentTotal)/initialTotal)*100;
        return "O prejuízo do mês " + month + " foi de " + finantialLoss + "%";
    }

    private Double getAllLoss(String month){
        List<DataBaseExpiredQuantity> list = batchStockRepo.getSectorExpiredQuantity(month);
        if (list.isEmpty()){
            return 0.0;
        }
        double currentTotal = list.stream().mapToDouble(c->c.getCurrent_quantity()).sum();
        double initialTotal = list.stream().mapToDouble(c->c.getInitial_quantity()).sum();

        double finantialLoss = ((initialTotal - currentTotal)/initialTotal)*100;
        return finantialLoss;
    }

    public String getAnualLoss()
    {
        List<Integer> month =  new ArrayList<>();
        month.add(1);
        month.add(2);
        month.add(3);
        month.add(4);
        month.add(5);
        month.add(6);
        month.add(7);
        month.add(8);
        month.add(9);
        month.add(10);
        month.add(11);
        month.add(12);

        List<Double> loss = new ArrayList<>();
        loss.add(getAllLoss("1"));
        loss.add(getAllLoss("2"));
        loss.add(getAllLoss("3"));
        loss.add(getAllLoss("4"));
        loss.add(getAllLoss("5"));
        loss.add(getAllLoss("6"));
        loss.add(getAllLoss("7"));
        loss.add(getAllLoss("8"));
        loss.add(getAllLoss("9"));
        loss.add(getAllLoss("10"));
        loss.add(getAllLoss("11"));
        loss.add(getAllLoss("12"));
        System.out.println(loss);
        int n = month.size();

        // sum of array x

        int sx = month.stream().mapToInt(m->m).sum();

        System.out.println(sx);

        // sum of array y
        double sy =0 ;
        for(double lo : loss){
            sy =  loss.stream().mapToDouble(l->lo).sum();
        }

        System.out.println(sy);
        // for sum of product of x and y
        int sxsy = 0;

        // sum of square of x
        int sx2 = 0;
        for (int i = 0; i < n; i++) {
            sxsy += month.get(i) * loss.get(i);
            sx2 += month.get(i) * month.get(i);
        }
        double b = (n * sxsy - sx * sy)
                / (n * sx2 - sx * sx);

        double k = month.size();
        Double meanX = month.stream().mapToDouble(m->m).sum() / k;
        Double meanY = loss.stream().mapToDouble(l->l).sum()/ k;

        System.out.println(meanX);
        System.out.println(meanY);
        double a = (meanY - b * meanX);

        System.out.println("Regression line:");
        System.out.print("Y = ");
        System.out.printf("%.5f", a);
        System.out.print(" + ");
        System.out.printf("%.3f", b);
        System.out.print("*X");
        return "The anual finantial loss is predicted by the following line equation: Y = " + a + "+" + b + "*x";

    }

    @Override
    public List<BatchStock> listBatchStockByCategory (String category) {

        List<InBoundOrder> listInBoundOrder = inBoundOrderRepo.findAll();
        List<BatchStock> batchListByCategory = new ArrayList<>();

        for (InBoundOrder inBoundOrder: listInBoundOrder){
            verifyDueDatePerCategory(category, batchListByCategory, inBoundOrder);
        }
        if(batchListByCategory.isEmpty()){
            throw new ElementAlreadyExistsException("No products were found for this category");
        }else {
            return batchListByCategory;
        }
    }

    public List<ResponseSectorQuery> listBatchSector(long id) {

        List<DataBaseQuery> listBatchSector = batchStockRepo.getListBatchSector(id);

        if (listBatchSector.isEmpty()) {
            throw new ElementNotFoundException("Não há lote de produtos com esse id");
        }

        return buildResponseQueryList(listBatchSector);
    }

    public List<ResponseSectorQuery> listBatchSectorOrdered(long id, String string) {
        List<ResponseSectorQuery> responseSectorQueryList;
        List<DataBaseQuery> dataBaseQuery;

        switch (string) {
            case "l":
            case "L":
                dataBaseQuery = batchStockRepo.getListOrderedById(id);
                responseSectorQueryList = buildResponseQueryList(dataBaseQuery);
                break;

            case "q":
            case "Q":
                dataBaseQuery = batchStockRepo.getListOrderedByQuantity(id);
                responseSectorQueryList = buildResponseQueryList(dataBaseQuery);
                break;

            case "v":
            case "V":
                dataBaseQuery = batchStockRepo.getListOrderedByDueDate(id);
                responseSectorQueryList = buildResponseQueryList(dataBaseQuery);
                break;

            default:
                throw new ElementAlreadyExistsException("Essa opção de ordenação não existe");
        }
        return responseSectorQueryList;
    }



    private void verifyDueDatePerCategory(String category, List<BatchStock> batchListByCategory, InBoundOrder inBoundOrder) {
        String foundCategory = inBoundOrder.getSector().getCategory();

        if(foundCategory.equalsIgnoreCase(category)){
            for (BatchStock batchStock: inBoundOrder.getBatchStockList()){
                LocalDate minusDays2 = batchStock.getDueDate().minusDays(21);
                if(LocalDate.now().isBefore(minusDays2)){
                    batchListByCategory.add(batchStock);
                }
            }
        }
    }

    private List<ResponseSectorQuery> buildResponseQueryList(List<DataBaseQuery> listBatchSector) {
        List<ResponseSectorQuery> responseSectorQueryList = new ArrayList<>();
        List<SectorQuery> sectorQueryList = new ArrayList<>();
        List<StockQuery> stockQueryList = new ArrayList<>();

        buildInitialList(listBatchSector, sectorQueryList, stockQueryList);
        filterBySector(responseSectorQueryList, sectorQueryList, stockQueryList);

        return responseSectorQueryList;
    }

    private void buildInitialList(List<DataBaseQuery> listBatchSector, List<SectorQuery> sectorQueryList, List<StockQuery> stockQueryList) {
        for (DataBaseQuery batchStockSectorDTO : listBatchSector) {
            StockQuery stockQuery = StockQuery.builder()
                    .batchId(batchStockSectorDTO.getBatch_id())
                    .currentQuantity(batchStockSectorDTO.getCurrent_quantity())
                    .dueDate(batchStockSectorDTO.getDue_date())
                    .sectorId(batchStockSectorDTO.getSector_id())
                    .productId(batchStockSectorDTO.getId_product())
                    .build();
            stockQueryList.add(stockQuery);

            SectorQuery sectorQuery = new SectorQuery();
            sectorQuery.setCategory(batchStockSectorDTO.getCategory());
            sectorQuery.setSectorId(batchStockSectorDTO.getSector_id());

            if (!sectorQueryList.contains(sectorQuery)) {
                sectorQueryList.add(sectorQuery);
            }
        }
    }

    private void filterBySector(List<ResponseSectorQuery> responseSectorQueryList, List<SectorQuery> sectorQueryList, List<StockQuery> stockQueryList) {
        ResponseSectorQuery responseSectorQuery;

        for (SectorQuery sector : sectorQueryList) {
            List<StockQuery> responseStock = stockQueryList.stream().filter(stock -> stock.getSectorId() == sector.getSectorId()).collect(Collectors.toList());

            responseSectorQuery = ResponseSectorQuery.builder()
                    .sector(sector)
                    .productId(responseStock.get(0).getProductId())
                    .stockList(responseStock)
                    .build();

            responseSectorQueryList.add(responseSectorQuery);
        }
    }

    public ResponseSectorTotalQuantity getTotalQuantity(long id) {

        List<DataBaseTotalQuantityQuery> dataBaseTotalQuantityQueryList = batchStockRepo.getListQuantity(id);
        if(dataBaseTotalQuantityQueryList.isEmpty()){
            throw new ElementNotFoundException("Não há esse produto em nenhum depósito");
        }
        ResponseSectorTotalQuantity response = new ResponseSectorTotalQuantity();
        response.setProductId(id);
        buildResponse(dataBaseTotalQuantityQueryList, response);

        return response;
    }

    private void buildResponse(List<DataBaseTotalQuantityQuery> dataBaseTotalQuantityQueryList, ResponseSectorTotalQuantity response) {
        List<SectorQuantityQuery> sectorList = new ArrayList<>();

        for(DataBaseTotalQuantityQuery data: dataBaseTotalQuantityQueryList){
            SectorQuantityQuery sectorQuantity = SectorQuantityQuery.builder()
                                .sectorId(data.getSector_id())
                                .totalQuantity(data.getTotal_quantity())
                                .build();
            sectorList.add(sectorQuantity);
        }
        response.setSectorList(sectorList);
    }

    public ResponseStock getListDueDate (Long sectorId, Long days) {

        List<DataBaseStockQuery> listDueDate = batchStockRepo.getListDueDate(sectorId);
        List<ResponseStockQuery> listResponse = new ArrayList<>();

        if(listDueDate.isEmpty()){
            throw new ElementNotFoundException("Lista vazia");
        }

        verifyDueDate(days, listDueDate, listResponse);
        ResponseStock responseStock = new ResponseStock();
        if (listResponse.isEmpty()){
            throw new ElementNotFoundException("Nenhum item dentro da validade nesse intervalo de dias");
        }

        responseStock.setDataBaseStocks(listResponse);
        return responseStock;
    }

    public ResponseStock getListCategoryDueDate (String category, Long days) {

        List<DataBaseStockQuery> listDueDate = batchStockRepo.getListCategory(category);
        List<ResponseStockQuery> listResponse = new ArrayList<>();

        if(listDueDate.isEmpty()){
            throw new ElementNotFoundException("Lista vazia");
        }

        verifyDueDate(days, listDueDate, listResponse);
        ResponseStock responseStock = new ResponseStock();
        if (listResponse.isEmpty()){
            throw new ElementNotFoundException("Nenhum item dentro da validade nesse intervalo de dias");
        }

        responseStock.setDataBaseStocks(listResponse);
        return responseStock;
    }


    private void verifyDueDate(Long days, List<DataBaseStockQuery> listDueDate, List<ResponseStockQuery> listResponse) {
        for (DataBaseStockQuery data : listDueDate) {
            LocalDate minusDays2 = data.getDue_date().minusDays(days);

                if (LocalDate.now().isBefore(minusDays2)) {
                ResponseStockQuery stock = ResponseStockQuery.builder()
                        .batchId(data.getBatch_id())
                        .dueDate(data.getDue_date())
                        .idProduct(data.getId_product())
                        .productType(data.getProduct_type())
                        .currentQuantity(data.getCurrent_quantity())
                        .build();
                listResponse.add(stock);
            }
        }
    }




}


