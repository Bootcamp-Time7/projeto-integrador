package com.desafiofinal.praticafinal.dto.queryDto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DataBaseExpiredQuantityImp implements DataBaseExpiredQuantity{

    private String month;
    private Long sector_id;
    private Long current_quantity;
    private Long initial_quantity;

    public DataBaseExpiredQuantityImp(String month, Long sectorId, Long currentQuantity, Long initialQuantity) {
        this.month = getMonth();
        this.sector_id = getSector_id();
        this.current_quantity = getCurrent_quantity();
        this.initial_quantity = getInitial_quantity();
    }

    @Override
    public String getMonth() {

        return this.month;
    }

    @Override
    public Long getSector_id() {
        return this.sector_id;
    }

    @Override
    public Long getCurrent_quantity() {
        return this.current_quantity;
    }

    @Override
    public Long getInitial_quantity() {
        return this.initial_quantity;
    }
}
