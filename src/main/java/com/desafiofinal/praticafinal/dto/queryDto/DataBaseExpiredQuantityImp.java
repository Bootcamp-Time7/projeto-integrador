package com.desafiofinal.praticafinal.dto.queryDto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DataBaseExpiredQuantityImp implements DataBaseExpiredQuantity{

    private String month;
    private Long sectorId;
    private Long currentQuantity;
    private Long initialQuantity;

    public DataBaseExpiredQuantityImp(String month, Long sectorId, Long currentQuantity, Long initialQuantity) {
        this.month = getMonth();
        this.sectorId = getSector_id();
        this.currentQuantity = getCurrent_quantity();
        this.initialQuantity = getInitial_quantity();
    }

    @Override
    public String getMonth() {

        return this.month;
    }

    @Override
    public Long getSector_id() {
        return this.sectorId;
    }

    @Override
    public Long getCurrent_quantity() {
        return this.currentQuantity;
    }

    @Override
    public Long getInitial_quantity() {
        return this.initialQuantity;
    }
}
