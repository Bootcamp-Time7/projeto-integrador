package com.desafiofinal.praticafinal.repository;

import com.desafiofinal.praticafinal.dto.queryDto.DataBasePurchase;
import com.desafiofinal.praticafinal.dto.queryDto.DataBaseStockQuery;
import com.desafiofinal.praticafinal.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepo extends JpaRepository<Cart, Long> {
    @Query(value = "\n" +
            "SELECT COUNT(cart_id) as count_seller FROM cart as c\n" +
            "join purchase as pu on c.cart_id = pu.id_batch_stock\n" +
            "join batch_stock as bat on bat.batch_id = pu.id_batch_stock\n" +
            "join product as prod on bat.id_product = prod.id\n" +
            "WHERE c.order_status = \"FINALIZADO\"\n" +
            "AND prod.id_seller = ?1 ", nativeQuery = true)
    Long getCountPurchase(Long idSeller);

    @Query(value = "\n" +
            "SELECT SUM(c.total_price) FROM cart as c\n" +
            "join purchase as pu on pu.id_cart = c.cart_id\n" +
            "join batch_stock as bat on bat.batch_id = pu.id_batch_stock\n" +
            "join product as prod on bat.id_product = prod.id\n" +
            "WHERE c.order_status = \"FINALIZADO\"\n" +
            "AND prod.id_seller = ?1 ", nativeQuery = true)
    Long getSumPurchase (Long idSeller);


    /*
    * SELECT SUM(total_purchase) FROM purchase as pu
			join cart as c on pu.cart_id = cart.cart_id
			join batch_stock as bat on bat.batch_id = pu.id_batch_stock
            join product as prod on bat.id_product = prod.id
            WHERE c.order_status = "FINALIZADO"
            AND prod.id_seller = 1;
    */
}
