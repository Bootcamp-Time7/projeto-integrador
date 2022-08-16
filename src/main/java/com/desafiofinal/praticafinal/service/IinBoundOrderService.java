package com.desafiofinal.praticafinal.service;


import com.desafiofinal.praticafinal.dto.requestResponseDto.InboundOrderRequestDTO;
import com.desafiofinal.praticafinal.model.InBoundOrder;

public interface IinBoundOrderService {
    InBoundOrder saveInBoundOrder(InboundOrderRequestDTO newOrder) throws Exception;
    InBoundOrder updateInBoundOrder(InboundOrderRequestDTO updateOrder) throws Exception;
}
