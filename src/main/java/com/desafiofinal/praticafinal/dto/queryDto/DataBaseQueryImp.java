package com.desafiofinal.praticafinal.dto.queryDto;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Basic;
import java.time.LocalDate;

@Builder
@Data
public class DataBaseQueryImp implements DataBaseQuery{

    private Long sectorId;
    private Long idProduct;
    private Long batchId;
    private Long currentQuantity;
    private LocalDate dueDate;
    private String category;

    public DataBaseQueryImp(Long sectorId, Long idProduct, Long batchId, Long currentQuantity, LocalDate dueDate, String category) {
        this.sectorId = getSector_id();
        this.idProduct = getId_product();
        this.batchId = getBatch_id();
        this.currentQuantity = getCurrent_quantity();
        this.dueDate = getDue_date();
        this.category = getCategory();
    }

    @Override
    public Long getSector_id() {
        return this.sectorId;
    }

    @Override
    public Long getId_product() {
        return this.idProduct;
    }

    @Override
    public Long getBatch_id() {
        return this.batchId;
    }

    @Override
    public Long getCurrent_quantity() {
        return this.currentQuantity;
    }

    @Override
    public LocalDate getDue_date() {
        return this.dueDate;
    }

    @Override
    public String getCategory() {
        return this.getCategory();
    }
}
