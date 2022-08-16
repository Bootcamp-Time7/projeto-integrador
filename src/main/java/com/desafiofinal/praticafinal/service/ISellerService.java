package com.desafiofinal.praticafinal.service;

import java.util.List;

import com.desafiofinal.praticafinal.model.Seller;


public interface ISellerService {
    Seller saveSeller(Seller seller);

    List<Seller> listAllSeller();

    // @Deprecated
    // Double updateReviewRate(Long sellerId);

    Seller getSellerById(Long sellerId);
}
