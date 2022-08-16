package com.desafiofinal.praticafinal.utils;

import com.desafiofinal.praticafinal.dto.queryDto.*;
import com.desafiofinal.praticafinal.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TestUtilsReq6 {


    public static List<DataBaseExpiredQuantity> getDataBaseExpiredQuantityList() {
        DataBaseExpiredQuantity data0 = getDataExpiredQuantity();

        DataBaseExpiredQuantity data1 = getDataExpiredQuantity();

        DataBaseExpiredQuantity data2 = getDataExpiredQuantity();

        List<DataBaseExpiredQuantity> list = new ArrayList<>();
        list.add(data0);
        list.add(data1);
        list.add(data2);

        return list;
    }

    public static DataBaseExpiredQuantityImp getDataExpiredQuantity() {

        DataBaseExpiredQuantityImp dataBaseExpiredQuantityImp = new DataBaseExpiredQuantityImp();
        dataBaseExpiredQuantityImp.setCurrent_quantity(10L);
        dataBaseExpiredQuantityImp.setInitial_quantity(20L);
        dataBaseExpiredQuantityImp.setMonth("10");
        dataBaseExpiredQuantityImp.setSector_id(13L);
//
        return dataBaseExpiredQuantityImp;
    }

    public static List<BatchStock> getBatchStockList() {
        BatchStock batchStock1 = getBatchStock();
        batchStock1.setBatchId(1L);
        BatchStock batchStock2 = getBatchStock();
        batchStock2.setBatchId(2L);
        BatchStock batchStock3 = getBatchStock();
        batchStock3.setBatchId(3L);

        List<BatchStock> batchStockList = new ArrayList<>();
        batchStockList.add(batchStock1);
        batchStockList.add(batchStock2);
        batchStockList.add(batchStock3);

        return batchStockList;
    }

    public static BatchStock getBatchStock() {
        Product product = getProductWhitId();
        InBoundOrder inBoundOrder = new InBoundOrder();
        inBoundOrder.setOrderId(1L);

        return BatchStock.builder()
                .product(product)
                .currentTemperature(1F)
                .minimumTemperature(1F)
                .initialQuantity(10)
                .currentQuantity(10)
                .manufacturingDate(LocalDate.parse("2023-01-01"))
                .dueDate(LocalDate.parse("2022-02-20"))
                .inBoundOrder(inBoundOrder)
                .build();
    }

    public static Product getProductWhitId() {
        Seller seller = getSeller();
        return Product.builder()
                .id(1L)
                .validateDate(LocalDate.parse("2023-01-01"))
                .price(1.0)
                .productType("cold")
                .productName("ham")
                .seller(seller)
                .build();
    }
    public static Seller getSeller() {
        return Seller.builder()
                .id(1L)
                .sellerName("Maria")
                .build();
    }

    public static DataBaseExpired getSectorExpiredWithIdImp() {
        DataBaseExpiredImp dataBaseExpiredImp = new DataBaseExpiredImp();
        dataBaseExpiredImp.setSector_id(13L);
        dataBaseExpiredImp.setCategory("Vencidos");
        dataBaseExpiredImp.setId_warehouse(1L);
        dataBaseExpiredImp.setCapacity(3000D);
        dataBaseExpiredImp.setMax_capacity(3000D);
        return dataBaseExpiredImp;
    }

    public static WareHouse getWareHouse() {
        Manager manager = Manager.builder()
                .managerId(1L)
                .managerName("Mauri")
                .build();
        WareHouse wareHouse = new WareHouse();
        wareHouse.setWareHouseId(1L);
        wareHouse.setManager(manager);
        wareHouse.setSectorList(TestUtilsGenerator.getSectorList());
        return wareHouse;
    }


    public static ResponseStock getResponseStock() {
        ResponseStock responseStock = new ResponseStock();
        responseStock.setDataBaseStocks(getListResponseStockQuery());
        return responseStock;

    }

    public static List<ResponseStockQuery> getListResponseStockQuery() {
        ResponseStockQuery responseStockQuery1 = getResponseStockQuery();
        responseStockQuery1.setBatchId(1L);

        ResponseStockQuery responseStockQuery2 = getResponseStockQuery();
        responseStockQuery2.setBatchId(2L);

        ResponseStockQuery responseStockQuery3 = getResponseStockQuery();
        responseStockQuery3.setBatchId(3L);

        List<ResponseStockQuery> responseStockQueries = new ArrayList<>();
        responseStockQueries.add(responseStockQuery1);
        responseStockQueries.add(responseStockQuery2);
        responseStockQueries.add(responseStockQuery3);

        return responseStockQueries;
    }

    public static ResponseStockQuery getResponseStockQuery() {

        return ResponseStockQuery.builder()
                .currentQuantity(10L)
                .dueDate(LocalDate.parse("2022-02-20"))
                .idProduct(1L)
                .productType("Vencidos")
                .batchId(1L)
                .build();
    }
}

