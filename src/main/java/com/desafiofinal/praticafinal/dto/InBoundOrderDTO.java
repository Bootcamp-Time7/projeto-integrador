package com.desafiofinal.praticafinal.dto;

import com.desafiofinal.praticafinal.dto.requestResponseDto.BatchStockResponseDTO;
import com.desafiofinal.praticafinal.model.BatchStock;
import com.desafiofinal.praticafinal.model.InBoundOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InBoundOrderDTO {

    private List<BatchStockDTO> batchStock;

    public InBoundOrderDTO(InBoundOrder inBoundOrder) {
        this.batchStock = BatchStockDTO.convertToListDto(inBoundOrder.getBatchStockList());
    }

    public static InBoundOrder convertToInBoundOrder(InBoundOrderDTO inBoundOrderDTO){
        List<BatchStock> newBatchStockList = BatchStockDTO.convertToListEntity(inBoundOrderDTO.getBatchStock());

        return InBoundOrder.builder()
                .batchStockList(newBatchStockList)
                .build();
    }

    public static List<InBoundOrderDTO> convertListToDTO(List<InBoundOrder> inBoundOrderList){
        return inBoundOrderList.stream()
                .map(InBoundOrderDTO::new)
                .collect(Collectors.toList());
    }

    public static List<InBoundOrder> convertListToEntity(List<InBoundOrderDTO> inBoundOrderDTOList) {
        return inBoundOrderDTOList.stream()
                .map(InBoundOrderDTO::convertToInBoundOrder)
                .collect(Collectors.toList());
    }

    public static List<InBoundOrder> convertToListEntity2 (List<InBoundOrderDTO> inBoundOrderDTOList){
        List<InBoundOrder> inBoundOrderList = new ArrayList<>();
        for(InBoundOrderDTO inBoundOrderDTO: inBoundOrderDTOList){
            inBoundOrderList.add(convertToInBoundOrder(inBoundOrderDTO));
        }
        return inBoundOrderList;
    }
//
//    public static List<InBoundOrderDTO> convertListToDto2 (List<InBoundOrder> inBoundOrderList){
//        List<InBoundOrderDTO> inBoundOrderDTOList = new ArrayList<>();
//        for(InBoundOrder inBoundOrder: inBoundOrderList){
//            inBoundOrderDTOList.add(new InBoundOrderDTO(inBoundOrder));
//        }
//        return inBoundOrderDTOList;
//    }
}
