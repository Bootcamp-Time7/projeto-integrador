package com.desafiofinal.praticafinal.dto.queryDto;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public interface DataBaseStockQuery {
    Long getBatch_id();
    Long getId_product();
    String getProduct_type();
    LocalDate getDue_date();
    Long getCurrent_quantity();
}
