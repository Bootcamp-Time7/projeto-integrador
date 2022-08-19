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


}
