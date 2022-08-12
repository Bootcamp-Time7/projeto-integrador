package com.desafiofinal.praticafinal.controller;

import com.desafiofinal.praticafinal.dto.queryDto.*;
import com.desafiofinal.praticafinal.repository.IBatchStockRepo;
import com.desafiofinal.praticafinal.service.BatchStockImpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/fresh-products/sectorProducts")
public class BatchStockController {

    @Autowired
    private BatchStockImpService service;

    @Autowired
    private IBatchStockRepo repo;

    @GetMapping("/{productId}")
    ResponseEntity<List<ResponseSectorQuery>> getBatchSector(@PathVariable long productId) {
        List<ResponseSectorQuery> getResponse = service.listBatchSector(productId);
        return new ResponseEntity<>(getResponse, HttpStatus.OK);
    }

    @GetMapping("/{productId}/{string}")
    ResponseEntity <List<ResponseSectorQuery>> getBatchSectorOrdered(@PathVariable long productId, @PathVariable String string) throws Exception {
        List<ResponseSectorQuery> getResponse = service.listBatchSectorOrdered(productId, string);
        return new ResponseEntity<>(getResponse, HttpStatus.OK);
    }

    @GetMapping("sector/{productId}")
    ResponseEntity<ResponseSectorTotalQuantity> getTotalQuantitySector(@PathVariable long productId)  {
        ResponseSectorTotalQuantity getResponse = service.getTotalQuantity(productId);
        return new ResponseEntity<>(getResponse, HttpStatus.OK);
    }

    @GetMapping("stockByDays/{category}/{days}")
    ResponseEntity<List<DataBaseStockQuery>> getStockByDueDate(@PathVariable String category, @PathVariable Long days)  {
        List<DataBaseStockQuery> getResponse = repo.getListDueDate(category);
        return new ResponseEntity<>(getResponse, HttpStatus.OK);
    }

//    @GetMapping("{category}/{days}")
//    ResponseEntity<ResponseStock> getTotalQuantitySector(@PathVariable String category, @PathVariable Long days)  {
//       ResponseStock getResponse = repo.(category, days);
//        return new ResponseEntity<>(getResponse, HttpStatus.OK);
//    }
}