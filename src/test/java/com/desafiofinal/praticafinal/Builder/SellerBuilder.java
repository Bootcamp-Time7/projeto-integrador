package com.desafiofinal.praticafinal.Builder;

import java.util.ArrayList;
import java.util.List;

import com.desafiofinal.praticafinal.model.Seller;

public class SellerBuilder {

    private Seller seller;

    private SellerBuilder() {}

    public static SellerBuilder aSeller() {
        SellerBuilder builder = new SellerBuilder();
         
        builder.seller = new Seller();

        builder.seller.setId(1l);
        builder.seller.setSellerName("Guilherme");

        return builder;
    }

    public static List<Seller> aListOfSellers() {
        ArrayList<Seller> sellerList = new ArrayList<>();
        
        sellerList.add(aSeller().create());

        return sellerList;
    }

    public Seller create() {
        return seller;
    }
}
