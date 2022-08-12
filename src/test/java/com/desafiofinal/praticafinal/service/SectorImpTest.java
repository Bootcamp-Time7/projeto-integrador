package com.desafiofinal.praticafinal.service;

import com.desafiofinal.praticafinal.dto.ProductDTO;
import com.desafiofinal.praticafinal.dto.SectorDTO;
import com.desafiofinal.praticafinal.exception.ElementNotFoundException;
import com.desafiofinal.praticafinal.model.Manager;
import com.desafiofinal.praticafinal.model.Product;
import com.desafiofinal.praticafinal.model.Sector;
import com.desafiofinal.praticafinal.model.WareHouse;
import com.desafiofinal.praticafinal.repository.ISectorRepo;
import com.desafiofinal.praticafinal.repository.IWareHouseRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Date;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

public class SectorImpTest {

    @Mock
    private  IWareHouseRepo wareHouseRepo;
    @Mock
    private  ISectorRepo sectorRepo;

    @Test

    void saveSector(){
        var manager = new Manager(1L, "AMANDA", null);
        WareHouse wareHouse = new WareHouse(1L, manager, Collections.emptyList());
        Sector sector = new Sector(1L, "FRIO", 12, Collections.emptyList(),  wareHouse);

        when(sectorRepo.save(any(Sector.class))).thenReturn(sector);
        when(wareHouseRepo.findById(anyLong())).thenReturn(Optional.of(wareHouse));

        var service = new SectorImpService(wareHouseRepo, sectorRepo);
        var sectorCreated = service.saveSector(
                new SectorDTO(1L, "FRIO", 12,  1L));


        Assertions.assertEquals(sector.getSectorId(), sectorCreated.getSectorId());
   }


}
