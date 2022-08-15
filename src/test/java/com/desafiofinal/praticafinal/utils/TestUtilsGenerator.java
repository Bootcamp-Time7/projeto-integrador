package com.desafiofinal.praticafinal.utils;

import com.desafiofinal.praticafinal.dto.queryDto.*;
import com.desafiofinal.praticafinal.model.*;
import org.mockito.InjectMocks;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TestUtilsGenerator {

    @InjectMocks
    private DataBaseStockQuery dataBaseStockQuery;

    public static InBoundOrder getNewOrder() {
        List<BatchStock> batchStockList = getBatchStockList();
        Sector sector = getSector();
        return InBoundOrder.builder()
                .orderId(0L)
                .dateTime(LocalDate.parse("2023-01-01"))
                .batchStockList(batchStockList)
                .sector(sector)
                .build();
    }

    public static InBoundOrder getOrderWithId() {
        List<BatchStock> batchStockList = getBatchStockList();
        Sector sector = getSector();
        return InBoundOrder.builder()
                .orderId(1L)
                .dateTime(LocalDate.parse("2023-01-01"))
                .batchStockList(batchStockList)
                .sector(sector)
                .build();
    }


    public static BatchStock getBatchStock() {
        Product product = getProductWhitId();
        InBoundOrder inBoundOrder = new InBoundOrder();
        inBoundOrder.setOrderId(1L);

        return BatchStock.builder()
                .batchId(1L)
                .product(product)
                .currentTemperature(1F)
                .minimumTemperature(1F)
                .initialQuantity(1)
                .manufacturingDate(LocalDate.parse("2023-01-01"))
                .dueDate(LocalDate.parse("2023-01-01"))
                .inBoundOrder(inBoundOrder)
                .build();
    }

    public static List<BatchStock> getBatchStockList() {
        BatchStock batchStock = getBatchStock();
        BatchStock batchStock1 = getBatchStock();
        batchStock1.setBatchId(2L);
        BatchStock batchStock2 = getBatchStock();
        batchStock2.setBatchId(3L);

        List<BatchStock> batchStockList = new ArrayList<>();
        batchStockList.add(batchStock);
        batchStockList.add(batchStock1);
        batchStockList.add(batchStock2);

        return batchStockList;
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

    public static Sector getSector() {
        return Sector.builder()
                .sectorId(1L)
                .category("FF")
                .capacity(1.0)
                .maxCapacity(10.0)
                .build();
    }


    public static List<InBoundOrder> getOrderList() {
        InBoundOrder inBoundOrder = getOrderWithId();
        InBoundOrder inBoundOrder1 = getOrderWithId();

        inBoundOrder1.setOrderId(1L);
        InBoundOrder inBoundOrder2 = getOrderWithId();

        inBoundOrder2.setOrderId(2L);

        List<InBoundOrder> orderList = new ArrayList<>();
        orderList.add(inBoundOrder);
        orderList.add(inBoundOrder1);
        orderList.add(inBoundOrder2);

        return orderList;
    }

    public static WareHouse getWareHouse() {
        Manager manager = Manager.builder()
                .managerId(1L)
                .managerName("manager")
                .build();
        return WareHouse.builder()
                .wareHouseId(2L)
                .manager(manager)
                .sectorList(getSectorList())
                .build();
    }

    public static Sector getSectorExpired() {

        return Sector.builder()
                .sectorId(0L)
                .capacity(1.0)
                .category("Vencidos")
                .maxCapacity(10.0)
                // .wareHouse(getWareHouse())
                // .orderList(getOrderList())
                .build();
    }


    public static Sector getSectorExpiredWithId() {

        return Sector.builder()
                .sectorId(13L)
                .capacity(1.0)
                .category("Vencidos")
                .maxCapacity(10.0)
                //.wareHouse(getWareHouse())
                // .orderList(getOrderList())
                .build();
    }

    public static DataBaseExpiredImp getSectorExpiredWithIdImp() {

        return DataBaseExpiredImp.builder()
                .sectorId(13L)
                .category("Vencidos")
                .idWarehouse(getWareHouse().getWareHouseId())
                .capacity(200D)
                .maxCapacity(3000D)
                .build();
    }

    public static ResponseStock getResponseStock() {

        return ResponseStock.builder()
                .dataBaseStocks(getListResponseStockQuery())
                .build();
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

    public static List<ResponseStockQuery> getListResponseStockQuery() {
        ResponseStockQuery responseStockQuery = getResponseStockQuery();
        ResponseStockQuery responseStockQuery1 = getResponseStockQuery();

        responseStockQuery1.setBatchId(1L);
        ResponseStockQuery responseStockQuery2 = getResponseStockQuery();

        responseStockQuery2.setBatchId(2L);

        List<ResponseStockQuery> responseStockQueries = new ArrayList<>();
        responseStockQueries.add(responseStockQuery);
        responseStockQueries.add(responseStockQuery1);
        responseStockQueries.add(responseStockQuery2);

        return responseStockQueries;
    }

    public static List<Sector> getSectorList() {
        Sector sector = getSector();
        Sector sector1 = getSector();

        sector1.setSectorId(1L);
        Sector sector2 = getSector();

        sector2.setSectorId(2L);

        List<Sector> sectorList = new ArrayList<>();
        sectorList.add(sector);
        sectorList.add(sector1);
        sectorList.add(sector2);

        return sectorList;
    }

    public static DataBaseExpiredQuantityImp getDataExpiredQuantity() {

        return DataBaseExpiredQuantityImp.builder()
                .month("10")
                .current_quantity(10L)
                .initial_quantity(20L)
                .sector_id(13L)
                .build();
    }
     public static List<DataBaseExpiredQuantityImp> getDataExpiredQuantityList(){
        DataBaseExpiredQuantityImp data = getDataExpiredQuantity();

        DataBaseExpiredQuantityImp data1 =getDataExpiredQuantity();
        data1.setSector_id(1L);

        DataBaseExpiredQuantityImp data2 = getDataExpiredQuantity();
        data2.setSector_id(2L);

        List<DataBaseExpiredQuantityImp> list = new ArrayList<>();
        list.add(data);
        list.add(data1);
        list.add(data2);

        return list;
     }

}