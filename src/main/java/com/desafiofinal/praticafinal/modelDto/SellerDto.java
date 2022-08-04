package com.desafiofinal.praticafinal.modelDto;

import com.desafiofinal.praticafinal.modelEntity.Product;
import com.desafiofinal.praticafinal.modelEntity.Seller;
import com.desafiofinal.praticafinal.modelEntity.WareHouse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class SellerDto {

    private long sellerId;
    private String sellerName;
    private List<Product> productList;

    public SellerDto(Seller seller) {
        this.sellerId = seller.getSellerId();
        this.sellerName = seller.getSellerName();
        this.productList = seller.getProductList();
    }
}
