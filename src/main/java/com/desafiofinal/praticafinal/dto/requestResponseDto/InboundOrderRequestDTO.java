package com.desafiofinal.praticafinal.dto.requestResponseDto;

import com.desafiofinal.praticafinal.dto.BatchStockDTO;
import com.desafiofinal.praticafinal.dto.SectorDTO;
import com.desafiofinal.praticafinal.model.InBoundOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InboundOrderRequestDTO {

    private long orderId;

    @DateTimeFormat(pattern = "yyyy/MM/dd")
    @NotNull(message = "Manufacturing Date cannot be null. Format: yyyy/MM/dd")
    private Date dateTime;

    @NotNull(message = "Please enter a valid sector")
    private SectorDTO sector;

    @NotEmpty(message = "Back stock list cannot be empty")
    private List<@Valid BatchStockDTO> batchStockList;

//    public static InBoundOrder convertDTOToInboundOrder(InboundOrderRequestDTO inboundOrderRequestDTO) {
//        return InBoundOrder.builder()
//                .orderId(inboundOrderRequestDTO.getOrderId())
//                .dateTime(inboundOrderRequestDTO.getDateTime())
//                .sector(SectorDTO.convertToSector(inboundOrderRequestDTO.getSector()))
//                .batchStockList(BatchStockDTO.convertToListEntity(inboundOrderRequestDTO.getBatchStock()))
//                .build();
//    }

    public InboundOrderRequestDTO(long orderId, Date dateTime, SectorDTO sector) {
        this.orderId = orderId;
        this.dateTime = dateTime;
        this.sector = sector;
    }

    public long getSectorID(){
        return sector.getSectorId();
    }

}
