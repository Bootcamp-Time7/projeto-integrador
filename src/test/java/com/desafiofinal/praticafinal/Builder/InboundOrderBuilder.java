package com.desafiofinal.praticafinal.Builder;

import java.time.LocalDate;

import com.desafiofinal.praticafinal.model.InBoundOrder;

public class InboundOrderBuilder {
    
    private InBoundOrder inBoundOrder;

    private InboundOrderBuilder() {}

    public static InboundOrderBuilder aInboundOrder() {
        InboundOrderBuilder builder = new InboundOrderBuilder();

        builder.inBoundOrder = new InBoundOrder();
        
        builder.inBoundOrder.setDateTime(LocalDate.parse("2022-02-01"));
        builder.inBoundOrder.setOrderId(3l);
        // builder.inBoundOrder.setSector(sector);

        return builder;
    }

    public InBoundOrder create() {
        return inBoundOrder;
    }
}
