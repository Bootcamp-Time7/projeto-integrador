package com.desafiofinal.praticafinal.dto;

import com.desafiofinal.praticafinal.model.BatchStock;
import com.desafiofinal.praticafinal.model.InBoundOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BatchStockDto {


    private long batchId;

    private float currentTemperature;

    private float minimumTemperature;

    private long initialQuantity;

    private long currentQuantity;

    private LocalDate manufacturingDate;

    private LocalDate manufacturingTime;

    private LocalDate dueDate;

    private InBoundOrder inBoundOrderId;

    private ProductDto productDto;

    public BatchStockDto(BatchStock batchStock) {
        this.batchId = batchStock.getBatchId();
        this.currentTemperature = batchStock.getCurrentTemperature();
        this.minimumTemperature = batchStock.getMinimumTemperature();
        this.initialQuantity = batchStock.getInitialQuantity();
        this.currentQuantity = batchStock.getCurrentQuantity();
        this.manufacturingDate = batchStock.getManufacturingDate();
        this.manufacturingTime = batchStock.getManufacturingTime();
        this.dueDate = batchStock.getDueDate();
        this.inBoundOrderId = batchStock.getInBoundOrder();
      //  this.product = new Product(batchStock.getProduct());
    }

    public static BatchStock convertBatchStockDtoToBatchStock (BatchStockDto batchStockDto){
        return BatchStock.builder()
                .batchId(batchStockDto.getBatchId())
                .currentTemperature(batchStockDto.getCurrentTemperature())
                .minimumTemperature(batchStockDto.getMinimumTemperature())
                .initialQuantity(batchStockDto.getInitialQuantity())
                .currentQuantity(batchStockDto.getCurrentQuantity())
                .manufacturingDate(batchStockDto.getManufacturingDate())
                .manufacturingTime(batchStockDto.getManufacturingTime())
                .dueDate(batchStockDto.getDueDate())
//               .inBoundOrderId()
//                .product(batchStockDto.getProduct())
                .build();
    }

    public static List<BatchStock> convertToListEntity (List<BatchStockDto> batchStockDtoList){
        List<BatchStock> batchStockList = new ArrayList<>();
        for(BatchStockDto batchStockDto: batchStockDtoList){
            batchStockList.add(convertBatchStockDtoToBatchStock(batchStockDto));
        }
        return batchStockList;
    }

    public static List<BatchStockDto> convertToListDto (List<BatchStock> batchStockList){
        List<BatchStockDto> batchStockListDto = new ArrayList<>();
        for(BatchStock batchStock: batchStockList){
            batchStockListDto.add(new BatchStockDto(batchStock));
        }
        return batchStockListDto;
    }
}