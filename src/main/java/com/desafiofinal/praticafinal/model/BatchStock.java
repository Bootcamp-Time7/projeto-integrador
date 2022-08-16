package com.desafiofinal.praticafinal.model;

import com.desafiofinal.praticafinal.dto.BatchStockDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;


@Entity
@Data
@Builder
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
    private @NotNull(message = "Manufacturing Date cannot be null. Format: yyyy/MM/dd") LocalDate manufacturingDate;
    private @NotNull(message = "Manufacturing Date cannot be null. Format: yyyy/MM/dd") LocalDate manufacturingTime;
    private @Future(message = "Due date must be in the future") @NotNull(message = "Due date cannot be null")
    LocalDate dueDate;

    @ManyToOne
    @JoinColumn (name = "id_inboundorder")
    @JsonIgnoreProperties("batchStockList")
    private InBoundOrder inBoundOrder;

    @ManyToOne (cascade = CascadeType.REFRESH)
    @JsonIgnoreProperties("batchList")
    @JoinColumn (name = "id_product")
    private Product product;

    public BatchStock(BatchStockDTO batchStockDTO, InBoundOrder inboundOrder, Product product ) {
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
