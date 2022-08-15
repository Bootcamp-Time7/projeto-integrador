package com.desafiofinal.praticafinal.dto.queryDto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DataBaseExpiredImp implements  DataBaseExpired{
    private Long sectorId;
    private Double capacity;
    private String category;
    private Double maxCapacity;
    private Long idWarehouse;

    public DataBaseExpiredImp(Long sectorId, Double capacity, String category, Double maxCapacity, Long idWarehouse) {
        this.sectorId = getSector_id();
        this.capacity = getCapacity();
        this.category = getCategory();
        this.maxCapacity =  getMax_capacity();
        this.idWarehouse = getId_warehouse();
    }

    @Override
    public Long getSector_id() {
        return this.sectorId;
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
        return this.maxCapacity;
    }

    @Override
    public Long getId_warehouse() {
        return this.idWarehouse;
    }
}
