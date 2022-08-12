package com.desafiofinal.praticafinal.dto;

import com.desafiofinal.praticafinal.model.Sector;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class SectorDTO {

    private Long sectorId;
    private String category;
    private double capacity;
    private Long idWareHouse;

    public SectorDTO(Sector sector) {
        this.sectorId = sector.getSectorId();
        this.category = sector.getCategory();
        this.capacity = sector.getCapacity();
        this.idWareHouse = sector.getWareHouse().getWareHouseId();
    }

//    public SectorDTO(long l, String frio, int i, int i1, long l1) {
//    }

//    public  SectorDTO(long sectorId, String category, double capacity, Long idWareHouse) {
//        this.sectorId = sectorId;
//        this.category = category;
//        this.capacity = capacity;
//        this.idWareHouse = idWareHouse;
//    }


}
