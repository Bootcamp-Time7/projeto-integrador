package com.desafiofinal.praticafinal.service;

import com.desafiofinal.praticafinal.dto.ProductDTO;
import com.desafiofinal.praticafinal.dto.SellerDTO;
import com.desafiofinal.praticafinal.model.Product;
import com.desafiofinal.praticafinal.model.Seller;
import com.desafiofinal.praticafinal.repository.IProductRepo;
import com.desafiofinal.praticafinal.repository.ISellerRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)

class ProductImplServiceTest {
    @Mock
    private IProductRepo productRepo;
    @Mock
    private ISellerRepo sellerRepo;
    @Test
    void saveProduct() {


            Seller seller =  new Seller(1L,"Seller", Collections.emptySet());
        SellerDTO sellerDTO = new SellerDTO(seller);
            Product product = new Product(1L,
                    LocalDate.parse("2023-01-01"),
                    14,
                    "Carnes",
                    "Patinho bovino",
                    seller,
                    24,
                    Collections.emptyList());
            when(productRepo.save(any(Product.class))).thenReturn(product);
            when(sellerRepo.findById(anyLong())).thenReturn(Optional.of(seller));

            var service = new ProductImplService(productRepo, sellerRepo);
            var productCreated = service.saveProduct(new ProductDTO(
                    "frios",
                    LocalDate.parse("2023-01-01"),
                    12D,
                    "carne",
                    sellerDTO,
                    17D ));


            Assertions.assertEquals(product.getProductName(), productCreated.getProductName());
        }


    @Test
    void listAllProducts() {
    }
}