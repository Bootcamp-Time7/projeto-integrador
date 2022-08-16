package com.desafiofinal.praticafinal.dto.queryDto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class DataBaseExpiredImp implements  DataBaseExpired{
    private Long sector_id;
    private Double capacity;
    private String category;
    private Double max_capacity;
    private Long id_warehouse;

    public DataBaseExpiredImp(Long sectorId, Double capacity, String category, Double maxCapacity, Long idWarehouse) {
        this.sector_id = getSector_id();
        this.capacity = getCapacity();
        this.category = getCategory();
        this.max_capacity =  getMax_capacity();
        this.id_warehouse = getId_warehouse();
    }

    @Override
    public Long getSector_id() {
        return this.sector_id;
    }

    @Override
    public Double getCapacity() {
        return this.capacity;
    }

    @Override
    public String getCategory() {
        return this.category;
    }

    @Override
    public Double getMax_capacity() {
        return this.max_capacity;
    }

    @Override
    public Long getId_warehouse() {
        return this.id_warehouse;
    }
}
