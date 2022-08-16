package com.desafiofinal.praticafinal.model;
import com.desafiofinal.praticafinal.dto.requestResponseDto.InBoundOrderRequestDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Entity
@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InBoundOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderId;

    private LocalDate dateTime;

    @OneToMany (mappedBy = "inBoundOrder", cascade = CascadeType.PERSIST)
    private List<BatchStock> batchStockList;

    @ManyToOne
    @JoinColumn (name = "id_sector")
    @JsonIgnoreProperties("orderList")
    private Sector sector;

    public InBoundOrder(InBoundOrderRequestDTO dto) {
        this.orderId = dto.getOrderId();
        this.dateTime = dto.getDateTime();
//        this.batchStockList = dto.getBatchStockList();
//        this.sector = sector;

    }

}
