package com.desafiofinal.praticafinal.controller;

import com.desafiofinal.praticafinal.service.BatchStockImpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/fresh-products")
public class Controller {

    @Autowired
    private BatchStockImpService service;

    @GetMapping("/products/regression")
    ResponseEntity<Double> verifyRegression (){
        Double getResponse = service.linearRegression();
        return new ResponseEntity<>(getResponse, HttpStatus.OK);

    }
}
