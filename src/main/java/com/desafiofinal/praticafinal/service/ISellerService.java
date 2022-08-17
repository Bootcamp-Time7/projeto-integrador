package com.desafiofinal.praticafinal.service;

import com.desafiofinal.praticafinal.dto.SellerDTO;
import com.desafiofinal.praticafinal.model.Seller;

public interface ISellerService {
    Seller saveSeller(SellerDTO seller);
}
