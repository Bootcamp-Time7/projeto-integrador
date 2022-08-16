package com.desafiofinal.praticafinal.controller;

import com.desafiofinal.praticafinal.dto.SellerDTO;
import com.desafiofinal.praticafinal.dto.queryDto.DatabaseSeller;
import com.desafiofinal.praticafinal.model.Seller;
import com.desafiofinal.praticafinal.repository.CartRepo;
import com.desafiofinal.praticafinal.repository.IBatchStockRepo;
import com.desafiofinal.praticafinal.service.ISellerService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class holds all endpoints related to the seller
 * @author Amanda
 * @version 1.0.0
 */

@RestController
@RequestMapping("/api/v1/fresh-products/seller")
public class SellerController {
    
    @Autowired
     CartRepo queryRepo;

    private final ISellerService service;

    public SellerController(ISellerService service) {
        this.service = service;
    }
    /**
     * This route creates a new seller
     * @param seller id(Long), SellerName(String), ProductList(List of products)
     * @return HTML Response 201: Created
     */
    @PostMapping
    public ResponseEntity<SellerDTO> insertSeller(@RequestBody SellerDTO seller){
        Seller newSeller = SellerDTO.convertToSeller(seller);
        Seller savedSeller = service.saveSeller(newSeller);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SellerDTO(savedSeller));
    }

    @GetMapping("/list")
    public ResponseEntity<List<SellerDTO>> getAllSellers() {
        List<Seller> sellerList = service.listAllSeller();
        List<SellerDTO> convertedResponse = SellerDTO.convertListToDTO(sellerList);
        return ResponseEntity.status(HttpStatus.OK).body(convertedResponse); 
    }

    @PutMapping("/updaterating/{sellerId}")
    public ResponseEntity<List<DatabaseSeller>> updateRating(@PathVariable Long sellerId) {
        List<DatabaseSeller> response = queryRepo.getListRating(sellerId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
