package com.desafiofinal.praticafinal.Builder;

import java.time.LocalDate;

import com.desafiofinal.praticafinal.model.Product;
import com.desafiofinal.praticafinal.model.Seller.SellerBuilder;

public class ProductBuilder {
    
    private Product product;

    private ProductBuilder() {}

    public static ProductBuilder aProduct() {
        ProductBuilder builder = new ProductBuilder();

        builder.product = new Product();
         
        builder.product.setBatchList(BatchStockBuilder.aListOfBatchStocks());
        builder.product.setBulk(20.0);
        builder.product.setId(2l);
        builder.product.setPrice(10.5);
        builder.product.setProductName("Lámen do Naruto");
        builder.product.setProductType("Comida");
        // builder.product.setSeller();
        builder.product.setValidateDate(LocalDate.parse("2024-10-01"));

        return builder;
    }

    public Product create() {
        return product;
    }
}
