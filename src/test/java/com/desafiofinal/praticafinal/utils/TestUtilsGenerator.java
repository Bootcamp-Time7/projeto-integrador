package com.desafiofinal.praticafinal.utils;

import com.desafiofinal.praticafinal.dto.queryDto.*;
import com.desafiofinal.praticafinal.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TestUtilsGenerator {


    public static InBoundOrder getNewOrder(){
        List<BatchStock> batchStockList = getBatchStockList();
        Sector sector = getSector();
        return InBoundOrder.builder()
                .orderId(0L)
                .dateTime(LocalDate.parse("2023-01-01"))
                .batchStockList(batchStockList)
                .sector(sector)
                .build();
    }

    public static InBoundOrder getOrderWithId(){
        List<BatchStock> batchStockList = getBatchStockList();
        Sector sector = getSector();
        return InBoundOrder.builder()
                .orderId(1L)
                .dateTime(LocalDate.parse("2023-01-01"))
                .batchStockList(batchStockList)
                .sector(sector)
                .build();
    }

    public static List<InBoundOrder> getOrderList(){
        List<InBoundOrder> inBoundOrderList = new ArrayList<>();
        InBoundOrder inBoundOrder = getOrderWithId();
        inBoundOrderList.add(inBoundOrder);
        return inBoundOrderList;
    }


    public static BatchStock getBatchStockAndSectorWithCapacity(){
        Product product = getProduct();
        Sector sector = getSector();
        InBoundOrder inBoundOrder = new InBoundOrder();
        inBoundOrder.setOrderId(1L);
        inBoundOrder.setSector(sector);

        return BatchStock.builder()
                .batchId(1L)
                .product(product)
                .currentTemperature(1F)
                .minimumTemperature(1F)
                .initialQuantity(1)
                .currentQuantity(10)
                .manufacturingDate(LocalDate.parse("2023-01-01"))
                .dueDate(LocalDate.parse("2023-01-01"))
                .inBoundOrder(inBoundOrder)
                .build();
    }
    public static BatchStock getBatchStockWithoutCapacity(){
        Product product = getProduct();
        Sector sector = getSectorWithoutCapacity();
        InBoundOrder inBoundOrder = new InBoundOrder();
        inBoundOrder.setOrderId(1L);
        inBoundOrder.setSector(sector);

        return BatchStock.builder()
                .batchId(1L)
                .product(product)
                .currentTemperature(1F)
                .minimumTemperature(1F)
                .initialQuantity(1)
                .currentQuantity(10)
                .manufacturingDate(LocalDate.parse("2023-01-01"))
                .dueDate(LocalDate.parse("2023-01-01"))
                .inBoundOrder(inBoundOrder)
                .build();
    }

    public static BatchStock getBatchStockWithoutOrder(){
        Product product = getProduct();
        InBoundOrder inBoundOrder = new InBoundOrder();
        inBoundOrder.setOrderId(3L);

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
        BatchStock batchStock = getBatchStockAndSectorWithCapacity();
        BatchStock batchStock1 = getBatchStockAndSectorWithCapacity();
        batchStock1.setBatchId(2L);
        BatchStock batchStock2 = getBatchStockAndSectorWithCapacity();
        batchStock2.setBatchId(3L);

        List<BatchStock> batchStockList = new ArrayList<>();
        batchStockList.add(batchStock);
        batchStockList.add(batchStock1);
        batchStockList.add(batchStock2);

        return batchStockList;
    }

    public static Product getProduct(){
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

    public static Product getNewProduct(){
        Seller seller = getSeller();
        return Product.builder()
                .validateDate(LocalDate.parse("2023-01-01"))
                .price(1.0)
                .productType("cold")
                .productName("ham")
                .seller(seller)
                .build();
    }

    public static List<Product> getProductList(){
        List<Product> productList = new ArrayList<>();
        Product product = getProduct();
        productList.add(product);
        return productList;
    }

    public static Seller getSeller(){
        return Seller.builder()
                .id(1L)
                .sellerName("Maria")
                .build();
    }

    public static Seller getNewSeller(){
        return Seller.builder()
                .sellerName("Maria")
                .build();
    }

    public static Sector getSector(){
        return Sector.builder()
                .sectorId(1L)
                .category("FF")
                .capacity(1.0)
                .maxCapacity(100.0)
                .build();
    }

    public static Sector getSectorWithoutCapacity(){
        return Sector.builder()
                .sectorId(1L)
                .category("FF")
                .capacity(1.0)
                .maxCapacity(0.0)
                .build();
    }

    public static Purchase getPurchase(){
        return Purchase.builder()
                .purchaseId(1L)
                .batchStock(getBatchStockAndSectorWithCapacity())
                .pricePerProduct(1)
                .productQuantity(1)
                .build();
    }

    public static Purchase getPurchaseWithQuantityUnavailable(){
        return Purchase.builder()
                .purchaseId(1L)
                .batchStock(getBatchStockAndSectorWithCapacity())
                .pricePerProduct(1)
                .productQuantity(20)
                .build();
    }

    public static List<Purchase> getPurchaseList(){
        List<Purchase> purchaseList = new ArrayList<>();
        purchaseList.add(getPurchase());
        return purchaseList;
    }

    public static List<Purchase> getPurchaseWithoutCapacity(){
        Purchase purchase = getPurchase();
        purchase.setBatchStock(getBatchStockWithoutCapacity());
        List<Purchase> purchaseList = new ArrayList<>();
        purchaseList.add(purchase);
        return purchaseList;
    }

    public static List<Purchase> getPurchaseWhitQuantityUnavailable(){
        Purchase purchase = getPurchaseWithQuantityUnavailable();
        List<Purchase> purchaseList = new ArrayList<>();
        purchaseList.add(purchase);
        return purchaseList;
    }

    public static Cart getNewCartOpen(){
        Buyer buyer = getBuyer();
        return Cart.builder()
                .buyer(buyer)
                .listPurchase(getPurchaseList())
                .totalPrice(1)
                .date(LocalDate.parse("2023-02-02"))
                .orderStatus("Open")
                .build();
    }

    public static Cart getNewCartOpenWithoutCapacity(){
        Buyer buyer = getBuyer();
        return Cart.builder()
                .buyer(buyer)
                .listPurchase(getPurchaseWithoutCapacity())
                .totalPrice(1)
                .date(LocalDate.parse("2023-02-02"))
                .orderStatus("Open")
                .build();
    }

    public static Cart getNewCartOpenWithQuantityUnavailable(){
        Buyer buyer = getBuyer();
        return Cart.builder()
                .buyer(buyer)
                .listPurchase(getPurchaseWhitQuantityUnavailable())
                .totalPrice(1)
                .date(LocalDate.parse("2023-02-02"))
                .orderStatus("Open")
                .build();
    }

    public static Cart getCartFinished(){
        Buyer buyer = getBuyer();
        return Cart.builder()
                .cartId(1L)
                .buyer(buyer)
                .listPurchase(getPurchaseList())
                .totalPrice(1)
                .date(LocalDate.parse("2023-02-02"))
                .orderStatus("Finished")
                .build();
    }

    public static List<Cart> getCartFinishedList(){
        List<Cart> cartList = new ArrayList<>();
        Cart cart = getCartFinished();
        cartList.add(cart);
        return cartList;
    }

    public static Cart getCartOpen(){
        Buyer buyer = getBuyer();
        return Cart.builder()
                .cartId(1L)
                .buyer(buyer)
                .listPurchase(getPurchaseList())
                .totalPrice(1)
                .date(LocalDate.parse("2023-02-02"))
                .orderStatus("Open")
                .build();
    }

    public static Cart getCartOpenValueExceedLimits(){
        Buyer buyer = getBuyer();
        return Cart.builder()
                .cartId(1L)
                .buyer(buyer)
                .listPurchase(getPurchaseList())
                .totalPrice(100000)
                .date(LocalDate.parse("2023-02-02"))
                .orderStatus("Open")
                .build();
    }

    public static Buyer getBuyer(){
        return Buyer.builder()
                .buyerId(1L)
                .buyerName("Marina")
                .build();
    }


    public static DataBaseQueryImp getDataBaseQueryImp (){
        DataBaseQueryImp dataBaseQueryImp = new DataBaseQueryImp();
        dataBaseQueryImp.setId_product(1L);
        dataBaseQueryImp.setBatch_id(1L);
        dataBaseQueryImp.setSector_id(1L);
        dataBaseQueryImp.setDue_date(LocalDate.parse("2023-02-02"));
        dataBaseQueryImp.setCategory("FF");
        dataBaseQueryImp.setCurrent_quantity(200L);
        return  dataBaseQueryImp;

    }

    public static DataBaseQueryImp getDataBaseQueryImpWithOutBatch (){
        DataBaseQueryImp dataBaseQueryImp = new DataBaseQueryImp();
        dataBaseQueryImp.setId_product(1L);
        dataBaseQueryImp.setSector_id(1L);
        dataBaseQueryImp.setDue_date(LocalDate.parse("2023-02-02"));
        dataBaseQueryImp.setCategory("FF");
        dataBaseQueryImp.setCurrent_quantity(200L);
        return  dataBaseQueryImp;

    }


    public static List<DataBaseQuery> getListDataBaseQuery (){
        DataBaseQuery data0 = getDataBaseQueryImp();
        DataBaseQuery data1 = getDataBaseQueryImp();
        DataBaseQuery data2 = getDataBaseQueryImp();

        List<DataBaseQuery> list = new ArrayList<>();
        list.add(data0);
        list.add(data1);
        list.add(data2);
        return list;
    }

    public static SectorQuery getSectorQuery(){
        return SectorQuery.builder()
                .category("FF")
                .sectorId(1L)
                .build();
    }

    public static StockQuery getStockQuery(){
        return StockQuery.builder()
                .sectorId(1L)
                .dueDate(LocalDate.parse("2023-02-02"))
                .currentQuantity(200)
                .productId(1L)
                .batchId(1L)
                .build();
    }



    public static List<StockQuery> getListStockQuery (){
        StockQuery stock = getStockQuery();
        StockQuery stock1= getStockQuery();
        stock1.setProductId(1L);

        StockQuery stock2 = getStockQuery();
        stock2.setProductId(1L);

        List<StockQuery> stocklist = new ArrayList<>();
        stocklist.add(stock);
        stocklist.add(stock1);
        stocklist.add(stock2);

        return stocklist;
    }

    public static ResponseSectorQuery getResponseSectorQuery() {

        return ResponseSectorQuery.builder()
                .stockList(getListStockQuery())
                .productId(1L)
                .sector(getSectorQuery())
                .build();
    }


    public static List<ResponseSectorQuery> getListResponseSectorQuery (Long id){
        ResponseSectorQuery sectorQuery = getResponseSectorQuery();
        ResponseSectorQuery sectorQuery1 =getResponseSectorQuery();
        sectorQuery1.setProductId(1L);

        ResponseSectorQuery sectorQuery2 =getResponseSectorQuery();

        List<ResponseSectorQuery> responselist = new ArrayList<>();
       // responselist.add(sectorQuery);
        responselist.add(sectorQuery1);
        //responselist.add(sectorQuery2);

        return responselist;
    }

    public static DataBaseStockQueryImp getDataBaseStockQuery(Long id) {
        DataBaseStockQueryImp dataBaseStockQueryImp = new DataBaseStockQueryImp();
        dataBaseStockQueryImp.builder()
                .productType("FF")
                .sectorId(id)
                .batchId(1L)
                .currentQuantity(300L)
                .dueDate(LocalDate.parse("2022-08-26"))
                .productId(1L)
                .build();
        return dataBaseStockQueryImp;
    }

    public static List<DataBaseStockQuery> getDataBaseStockQueryList(){
        DataBaseStockQuery data0 = getDataBaseStockQuery(1L);
        DataBaseStockQuery data1 = getDataBaseStockQuery(2L);
        DataBaseStockQuery data2 = getDataBaseStockQuery(3L);

        List<DataBaseStockQuery> list = new ArrayList<>();

        list.add(data0);
        list.add(data1);
        list.add(data2);

        return list;
    }

    public static ResponseStockQuery getResponseStockQuery() {

        return ResponseStockQuery.builder()
                .currentQuantity(10L)
                .dueDate(LocalDate.parse("2022-02-20"))
                .idProduct(1L)
                .productType("FF")
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

    public static ResponseStock getResponseStock() {

        return ResponseStock.builder()
                .dataBaseStocks(getListResponseStockQuery())
                .build();
    }

    public static StockQuery getStockQuery2(){
        DataBaseQueryImp dataQuery = new DataBaseQueryImp();
        return StockQuery.builder()
                .sectorId(1L)
                .dueDate(LocalDate.parse("2023-02-02"))
                .currentQuantity(200)
                .productId(1L)
                .batchId(dataQuery.getBatch_id())
                .build();
    }

    public static DataBaseQueryImp dataBaseQueryImpWithId (Long id){
        return  DataBaseQueryImp.builder()
                .category("FF")
                .batch_id(43L)
                .due_date(LocalDate.parse("2022-08-26"))
                .current_quantity(300L)
                .id_product(id)
                .sector_id(1L)
                .build();

    }

    public static List<DataBaseQuery> dataBaseQueries (){
        DataBaseQuery data0 = dataBaseQueryImpWithId(1L);
        DataBaseQuery data1 = dataBaseQueryImpWithId(2L);
        DataBaseQuery data2 = dataBaseQueryImpWithId(3L);


        List<DataBaseQuery> list = new ArrayList<>();

        list.add(data0);
        list.add(data1);
        list.add(data2);

        return list;
    }

    public static DataBaseTotalQuantityQueryImp dataBaseQuantity(Long id){
        return DataBaseTotalQuantityQueryImp.builder()
                .total_quantity(300L)
                .category("FF")
                .id_product(id)
                .sector_id(1L)
                .build();
    }


    public static  List<DataBaseTotalQuantityQuery> listTotalQuantity(){
        DataBaseTotalQuantityQuery data0 = dataBaseQuantity(1L);
        DataBaseTotalQuantityQuery data1 = dataBaseQuantity(1L);
        DataBaseTotalQuantityQuery data2 = dataBaseQuantity(2L);

        List<DataBaseTotalQuantityQuery>list = new ArrayList<>();
        list.add(data0);
        list.add(data1);
        list.add(data2);

        return list;
    }
}

