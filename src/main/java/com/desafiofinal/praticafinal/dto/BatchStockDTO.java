package com.desafiofinal.praticafinal.dto;

import com.desafiofinal.praticafinal.model.BatchStock;
import lombok.*;
import org.springframework.lang.Nullable;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class BatchStockDTO {


    private long batchNumber;

    @Nullable
    @NotNull(message = "Please enter a valid product")
    private Long product;

    @NotNull(message = "Current temperature cannot be null")
    private Float currentTemperature;

    @NotNull(message = "Initial temperature cannot be null")
    private Float minimumTemperature;

    @NotNull(message = "Initial quantity cannot be null")
    @DecimalMin(value = "1", message = "Initial quantity cannot be less than 1")
    private Long initialQuantity;

    @NotNull(message = "Current quantity cannot be null")
    private Long currentQuantity;

    @DateTimeFormat(pattern = "yyyy/MM/dd")
    @NotNull(message = "Manufacturing Date cannot be null. Format: yyyy/MM/dd")
    private Date manufacturingDate;

    @DateTimeFormat(pattern = "yyyy/MM/dd")
    @NotNull(message = "Manufacturing Date cannot be null. Format: yyyy/MM/dd")
    private Date manufacturingTime;

    @Future(message = "Due date must be in the future")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    @NotNull(message = "Due date cannot be null")
    private Date dueDate;


    public BatchStockDTO(BatchStock batchStock) {
        this.batchNumber = batchStock.getBatchId();
        this.product = batchStock.getProduct().getId();
        this.currentTemperature = batchStock.getCurrentTemperature();
        this.minimumTemperature = batchStock.getMinimumTemperature();
        this.initialQuantity = batchStock.getInitialQuantity();
        this.currentQuantity = batchStock.getCurrentQuantity();
        this.manufacturingDate = batchStock.getManufacturingDate();
        this.manufacturingTime = batchStock.getManufacturingTime();
        this.dueDate = batchStock.getDueDate();
    }



}
