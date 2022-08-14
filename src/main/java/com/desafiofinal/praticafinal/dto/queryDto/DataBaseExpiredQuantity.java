package com.desafiofinal.praticafinal.dto.queryDto;

import java.time.LocalDate;

public interface DataBaseExpiredQuantity {

    String getMonth();
    Long getSector_id();
    Long getCurrent_quantity();
    Long getInitial_quantity();

}
