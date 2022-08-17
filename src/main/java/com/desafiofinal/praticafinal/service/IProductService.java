package com.desafiofinal.praticafinal.service;

import com.desafiofinal.praticafinal.dto.ProductDTO;
import com.desafiofinal.praticafinal.model.Product;

import java.util.List;

public interface IProductService {
    Product saveProduct(ProductDTO product);

    List<Product> listAllProducts();

}
