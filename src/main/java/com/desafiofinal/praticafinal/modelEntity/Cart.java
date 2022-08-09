package com.desafiofinal.praticafinal.modelEntity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Builder
@Data
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cartId;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn (name = "id_buyer")
    private Buyer buyer;

    private double totalPrice;

    private LocalDate date;

    private String orderStatus;

    @OneToMany (mappedBy = "idCart")
    private List<CartBatchStock> listCartBatchStock;
}