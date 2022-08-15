package com.desafiofinal.praticafinal.controller;

import com.desafiofinal.praticafinal.dto.SectorDTO;
import com.desafiofinal.praticafinal.dto.requestResponseDto.InBoundOrderRequestDTO;
import com.desafiofinal.praticafinal.dto.requestResponseDto.InBoundOrderResponseDTO;
import com.desafiofinal.praticafinal.model.InBoundOrder;
import com.desafiofinal.praticafinal.model.Sector;
import com.desafiofinal.praticafinal.service.BatchStockImpService;
import com.desafiofinal.praticafinal.service.SectorImpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/fresh-products/sector")
public class SectorController {

    @Autowired
    private SectorImpService service;

    @Autowired
    private BatchStockImpService serviceb;

    @PostMapping("/insert")
    public ResponseEntity<SectorDTO> insertSector(@RequestBody @Valid SectorDTO sectorDto) {
        Sector newSector = SectorDTO.convertDtoToSector(sectorDto);
        Sector savedResponse = service.createSector(newSector);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SectorDTO(savedResponse));

    }

}
