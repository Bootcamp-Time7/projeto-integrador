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
            "Select seller_rating, cart_id from cart as c join purchase as pu on c.cart_id = pu.id_cart\n" +
            "join batch_stock as batch on batch.batch_id = pu.id_batch_stock\n" +
            "join product as prod on prod.id = batch.id_product\n" +
            "where prod.id_seller = ?1 group by cart_id;", nativeQuery = true)
    List<DatabaseSeller> getListRating(Long idSeller);
}


