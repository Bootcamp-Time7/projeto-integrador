package com.desafiofinal.praticafinal.service;

import com.desafiofinal.praticafinal.dto.SellerDTO;
import com.desafiofinal.praticafinal.model.Seller;
import com.desafiofinal.praticafinal.repository.ISellerRepo;
import lombok.val;
import org.springframework.stereotype.Service;


@Service
public class SellerImplService implements ISellerService{
    private final ISellerRepo repo;

    public SellerImplService(ISellerRepo repo) {
        this.repo = repo;
    }

    @Override
    public Seller saveSeller(SellerDTO seller) {

            return repo.save(new Seller(seller));
    }
}
