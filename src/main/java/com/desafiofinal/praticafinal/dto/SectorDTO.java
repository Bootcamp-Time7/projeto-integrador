package com.desafiofinal.praticafinal.dto;

import com.desafiofinal.praticafinal.model.InBoundOrder;
import com.desafiofinal.praticafinal.model.Sector;
import com.desafiofinal.praticafinal.model.WareHouse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SectorDTO {

    private long sectorId;
    private String category;
    private double capacity;
    private double maxCapacity;
  // private List<InBoundOrderDTO> orderList;
    private WareHouseDTO wareHouse;

    public SectorDTO(Sector sector) {
        this.sectorId = sector.getSectorId();
        this.category = sector.getCategory();
        this.capacity = sector.getCapacity();
        this.maxCapacity=sector.getMaxCapacity();
      //  this.orderList = InBoundOrderDTO.convertListToDTO(sector.getOrderList());
        this.wareHouse = new WareHouseDTO(sector.getWareHouse());
    }

    public static Sector convertToSector(SectorDTO sectorDTO){
        return Sector.builder()
                .sectorId(sectorDTO.sectorId)
                .build();
    }

    public static Sector convertDtoToSector (SectorDTO sectorDTO){
        return Sector.builder()
                .sectorId(sectorDTO.sectorId)
                .category(sectorDTO.getCategory())
                .capacity(sectorDTO.getCapacity())
                .maxCapacity(sectorDTO.getMaxCapacity())
              //  .orderList(InBoundOrderDTO.convertToListEntity2(sectorDTO.getOrderList()))
                .wareHouse(WareHouseDTO.convertToWareHouse(sectorDTO.getWareHouse()))
                .build();
    }


}
