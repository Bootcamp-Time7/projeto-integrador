package com.desafiofinal.praticafinal.repository;

import com.desafiofinal.praticafinal.dto.queryDto.DatabaseSeller;
import com.desafiofinal.praticafinal.model.Cart;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepo extends JpaRepository<Cart, Long> {
    
    @Query(value = "\n" +
    "Select seller_rating from cart as c join purchase as p on c.cart_id = p.id_cart\n" +
    "join batch_stock as batch on batch.batch_id = p.id_batch_stock\n" +
    "join product as p on p.id = batch.id_product\n" +
    " join product as prod on prod.id = batch.id_product where prod.id_seller = ?1 group by cart_id;", nativeQuery = true)
List<DatabaseSeller> getListRating(Long idSeller);
}
