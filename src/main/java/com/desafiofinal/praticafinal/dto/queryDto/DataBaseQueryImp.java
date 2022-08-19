package com.desafiofinal.praticafinal.dto.queryDto;

import lombok.*;

import javax.persistence.Basic;
import java.time.LocalDate;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataBaseQueryImp implements DataBaseQuery{

    private Long sector_id;
    private Long id_product;
    private Long batch_id;
    private Long current_quantity;
    private LocalDate due_date;
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
    public Long getBatch_id() {
        return this.batch_id;
    }

    @Override
    public Long getCurrent_quantity() {
        return this.current_quantity;
    }

    @Override
    public LocalDate getDue_date() {
        return this.due_date;
    }

    @Override
    public String getCategory() {
        return this.category;
    }
}
