package com.desafiofinal.praticafinal.service;


import com.desafiofinal.praticafinal.exception.ElementAlreadyExistsException;
import com.desafiofinal.praticafinal.exception.ElementNotFoundException;
import com.desafiofinal.praticafinal.model.Sector;
import com.desafiofinal.praticafinal.repository.ISectorRepo;
import com.desafiofinal.praticafinal.repository.IWareHouseRepo;
import com.desafiofinal.praticafinal.utils.TestUtilsGenerator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Optional;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class SectorImpServiceTest {

    @InjectMocks
    private SectorImpService sectorImpService;

    @Mock
    ISectorRepo sectorRepo;


    @Test
    void create_returnNewSector_whenSectorDoesNotExist() {
        BDDMockito.when(sectorRepo.save(ArgumentMatchers.any(Sector.class)))
                .thenReturn(TestUtilsGenerator.getSectorExpiredWithId());

        Sector newSector = TestUtilsGenerator.getSectorExpired();

        Sector savedSector = sectorImpService.createSector(newSector);

        Assertions.assertThat(savedSector).isNotNull();
        assertThat(savedSector.getSectorId()).isPositive();
        assertThat(savedSector.getCategory()).isEqualTo("Vencidos");

        Mockito.verify(sectorRepo, Mockito.atLeastOnce()).save(newSector);


    }

    @Test
    void create_returnException_whenSectorAlreadyExists (){
        BDDMockito.when(sectorRepo.findByCategory(ArgumentMatchers.anyString()))
                .thenReturn(TestUtilsGenerator.getSectorExpiredWithId());

        Sector newSector = TestUtilsGenerator.getSectorExpiredWithId();

                Assertions.assertThatThrownBy(()
                -> sectorImpService.createSector(newSector))
                .isInstanceOf(ElementAlreadyExistsException.class)
                .hasMessageContaining("This sector already exists");
    }
}