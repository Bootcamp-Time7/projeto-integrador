package com.desafiofinal.praticafinal.controller;

import com.desafiofinal.praticafinal.dto.SellerDTO;
import com.desafiofinal.praticafinal.model.Seller;
import com.desafiofinal.praticafinal.service.ISellerService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class holds all endpoints related to the seller
 * @author Amanda, Nicole
 * @version 1.0.0
 */

@RestController
@RequestMapping("/api/v1/fresh-products/seller")
public class SellerController {

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
    /**
     * This route list all sellers /REQ 6
     * @author Nicole
     * @return HTML Response 200: ok
     */
    @GetMapping("/list")
    public ResponseEntity<List<SellerDTO>> getAllSellers() {
        List<Seller> sellerList = service.listAllSeller();
        List<SellerDTO> response = SellerDTO.convertListToDTO(sellerList);
        return new ResponseEntity<List<SellerDTO>>(response, HttpStatus.FOUND);
    }
    /**
     * This route list a seller using the sellerId got through url param. /REQ 6
     * @param sellerId A long
     * @return HTML Response 200: ok
     * @throws Exception 
     */
    @GetMapping("/list/{sellerId}")
    public ResponseEntity<SellerDTO> getSellerById(@PathVariable Long sellerId) throws Exception {
        Seller sellerById = service.getSellerById(sellerId);
        SellerDTO response = new SellerDTO(sellerById);
        return ResponseEntity.status(HttpStatus.OK).body(response);   
    }
    /**
     * This route delete a seller using the sellerId got through url param. /REQ 6
     * @param sellerId A long
     * @return HTML Response 200: ok and a string message of success.
     * @throws Exception
     */
    @DeleteMapping("/delete/{sellerId}")
    public ResponseEntity<String> deleteSeller(@PathVariable Long sellerId) throws Exception {
        String message = service.deleteSeller(sellerId);
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }
}
