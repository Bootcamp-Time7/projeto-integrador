package com.desafiofinal.praticafinal.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.desafiofinal.praticafinal.model.Seller;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class SellerDTO {

    private long idSeller;
    private String sellerName;
    private Double rating;

    public SellerDTO(Seller seller) {
        this.idSeller = seller.getId();
        this.sellerName = seller.getSellerName();
        this.rating = seller.getRating();
    }
    public static Seller convertToSeller(SellerDTO sellerDTO) {
        return Seller.builder()
                .id(sellerDTO.getIdSeller())
                .sellerName(sellerDTO.getSellerName())
                .rating(sellerDTO.getRating())
                .build();
    }

    public static List<Seller> convertToListEntity (List<SellerDTO> DTOList){
        return  DTOList.stream()
                .map(SellerDTO::convertToSeller)
                .collect(Collectors.toList());
    }
    public static List<SellerDTO> convertListToDTO(List<Seller> list){
        return list.stream()
                .map(SellerDTO::new)
                .collect(Collectors.toList());
    }
}
