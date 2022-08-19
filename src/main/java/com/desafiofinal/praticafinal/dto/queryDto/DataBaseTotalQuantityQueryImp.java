package com.desafiofinal.praticafinal.dto.queryDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DataBaseTotalQuantityQueryImp implements DataBaseTotalQuantityQuery {
    private Long sector_id;
    private Long id_product;
    // Long getBatch_id();
    private Long total_quantity;
    private String category;


    @Override
    public Long getSector_id() {
        return this.sector_id;
    }

    @Override
    public Long getId_product() {
        return this.id_product;
    }

    @Override
    public Long getTotal_quantity() {
        return this.total_quantity;
    }

    @Override
    public String getCategory() {
        return this.category;
    }
}
