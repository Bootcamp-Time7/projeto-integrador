package com.desafiofinal.praticafinal.service;

import com.desafiofinal.praticafinal.dto.queryDto.DatabaseSeller;
import com.desafiofinal.praticafinal.model.Seller;
import com.desafiofinal.praticafinal.repository.CartRepo;
import com.desafiofinal.praticafinal.repository.ISellerRepo;
import lombok.val;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerImplService implements ISellerService {

    @Autowired
    CartRepo cartRepo;

    @Autowired
    ISellerRepo sellerRepo;

    private final ISellerRepo repo;

    public SellerImplService(ISellerRepo repo) {
        this.repo = repo;
    }

    @Override
    public Seller saveSeller(Seller seller) {
        try {
            val sellerSaved = repo.save(seller);
            return sellerSaved;
        } catch (Exception e) {
            throw new Error("Desculpe, não foi possível realizar a sua solicitação", e.getCause());
        }
    }

    @Override
    public List<Seller> listAllSeller() {
        List<Seller> allSellers = repo.findAll();
        return allSellers;
    }

    // @Deprecated
    // @Override
    // public Double updateReviewRate(Long sellerId) {
    //     Optional<Seller> responseSeller = sellerRepo.findById(sellerId);
    //     List<DatabaseSeller> dataList = cartRepo.getListRating(sellerId);

    //     Double total = 0.0;
    //     Integer size = dataList.size();

    //     for (DatabaseSeller dataSeller : dataList) {
    //         total += dataSeller.getSeller_rating();
    //     }

    //     Double averageRating = total / size;
    //     responseSeller.get().setRating(averageRating);
    //     return averageRating;
    // }

    @Override
    public Seller getSellerById(Long sellerId) {
        try {
            Seller sellerById = repo.getReferenceById(sellerId);
            return sellerById;
        } catch (Exception e) {
            throw new Error("Nenhum vendedor com esse id foi encontrado!", e.getCause());
        }
    }
}
