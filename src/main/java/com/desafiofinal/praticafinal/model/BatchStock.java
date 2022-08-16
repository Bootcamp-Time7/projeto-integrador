package com.desafiofinal.praticafinal.model;

import com.desafiofinal.praticafinal.dto.BatchStockDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;


@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BatchStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long batchId;

    private float currentTemperature;
    private float minimumTemperature;
    private long initialQuantity;
    private long currentQuantity;
    private LocalDate manufacturingDate;
    private LocalDate manufacturingTime;
    private LocalDate dueDate;

    @ManyToOne
    @JoinColumn (name = "id_inboundorder", nullable = true)
    @JsonIgnoreProperties("batchStockList")
    private InBoundOrder inBoundOrder;

    @ManyToOne (cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn (name = "id_product")
    private Product product;

    public BatchStock(BatchStockDTO batchStockDTO,InBoundOrder inboundOrder, Product product ) {
        this.batchId = batchStockDTO.getBatchNumber();
        this.currentTemperature = batchStockDTO.getCurrentTemperature();
        this.minimumTemperature = batchStockDTO.getMinimumTemperature();
        this.initialQuantity = batchStockDTO.getInitialQuantity();
        this.currentQuantity = batchStockDTO.getCurrentQuantity();
        this.manufacturingDate = batchStockDTO.getManufacturingDate();
        this.manufacturingTime = batchStockDTO.getManufacturingDate();
        this.dueDate = batchStockDTO.getDueDate();
        this.inBoundOrder = inboundOrder;
        this.product = product;
    }
}