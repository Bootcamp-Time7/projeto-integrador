package com.desafiofinal.praticafinal.controller;

import com.desafiofinal.praticafinal.repository.CartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/fresh-products/product")
public class PurchaseController {


    @Autowired
    CartRepo cartRepo;
    @GetMapping("/productsSeller/{idSeller}")
    public ResponseEntity<String> consultSellerProducts(@PathVariable Long idSeller){
        return ResponseEntity.status(HttpStatus.OK).body("Total de produtos vendidos - " + cartRepo.getCountPurchase(idSeller));
    }

    @Autowired
    CartRepo cartRepoSeller;
    @GetMapping("/valueGenerate/{idSeller}")
    public ResponseEntity<String> consultSellerMoney(@PathVariable Long idSeller){
        return ResponseEntity.status(HttpStatus.OK).body("Total de valores gerados - " + cartRepo.getSumPurchase(idSeller));
    }

//    @GetMapping("/testeContagemDois/{idSeller}")
//    public ResponseEntity<String> consultSellerMoney(@PathVariable PurchaseDTO idSeller){
//        Purchase newPurchase = PurchaseDTO.convertDtoToPurchase(idSeller);
//        Purchase savedPurchase = service.ConsultTotalPrice(newPurchase);
//        return ResponseEntity.status(HttpStatus.OK).body(new PurchaseDTO(savedPurchase));
//    }
}
