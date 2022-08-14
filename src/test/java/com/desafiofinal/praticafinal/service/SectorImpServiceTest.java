package com.desafiofinal.praticafinal.service;


import com.desafiofinal.praticafinal.exception.ElementNotFoundException;
import com.desafiofinal.praticafinal.model.Sector;
import com.desafiofinal.praticafinal.repository.ISectorRepo;
import com.desafiofinal.praticafinal.repository.IWareHouseRepo;
import com.desafiofinal.praticafinal.utils.TestUtilsGenerator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Optional;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;

class SectorImpServiceTest {

    @InjectMocks
    private SectorImpService sectorImpService;

    @Mock
    ISectorRepo sectorRepo;

    @Mock
    IWareHouseRepo wareHouseRepo;

    @Test
    void create_returnNewSector_whenSectorDoesNotExist() {
        BDDMockito.when(sectorRepo.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.ofNullable(TestUtilsGenerator.getSectorVencidos()));

        Sector newSector = TestUtilsGenerator.getSectorVencidos();

        Sector savedSector = sectorImpService.createSector(newSector);

        Assertions.assertThat(savedSector).isNotNull();
        assertThat(savedSector.getSectorId()).isPositive();
        assertThat(savedSector.getCategory()).isEqualTo("Vencidos");

        Mockito.verify(sectorRepo, Mockito.atLeastOnce()).save(newSector);


    }

    @Test
    void create_returnException_whenSectorAlreadyExists (){
        BDDMockito.when(sectorRepo.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.ofNullable(TestUtilsGenerator.getSectorVencidos()));

        Sector newSector = TestUtilsGenerator.getSectorVencidos();

                Assertions.assertThatThrownBy(()
                -> sectorImpService.createSector(newSector))
                .isInstanceOf(ElementNotFoundException.class)
                .hasMessageContaining("Id does not exists");
    }
}