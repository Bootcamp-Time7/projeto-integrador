package com.desafiofinal.praticafinal.service;

import com.desafiofinal.praticafinal.dto.queryDto.*;
import com.desafiofinal.praticafinal.model.BatchStock;
import com.desafiofinal.praticafinal.model.Sector;
import com.desafiofinal.praticafinal.repository.IBatchStockRepo;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class BatchStockImpServiceTest {

    @InjectMocks
    private BatchStockImpService batchStockImpServicee;


    @Mock
    ISectorRepo sectorRepo;

    @Mock
    IBatchStockRepo batchStockRepo;


    @Mock
    IWareHouseRepo wareHouseRepo;


    @Test
    void transferToSector() {
        BDDMockito.when(batchStockRepo.findAll())
                .thenReturn(TestUtilsGenerator.getBatchStockList());
        BDDMockito.when(batchStockRepo.getSectorExpired())
                .thenReturn(TestUtilsGenerator.getSectorExpiredWithIdImp());

//        BDDMockito.when(wareHouseRepo.findById(ArgumentMatchers.anyLong()))
//                .thenReturn(Optional.ofNullable(TestUtilsGenerator.getWareHouse()));

        ResponseStock foundResponseStock = TestUtilsGenerator.getResponseStock();

        ResponseStock updatedSector = batchStockImpServicee.transferToSector();

        Assertions.assertThat(updatedSector).isNotNull();
        assertThat(updatedSector).isEqualTo(foundResponseStock);
       // assertThat(updatedSector.getCategory()).isEqualTo("Vencidos");

    }

//    @Test
//    void getFinantialLoss() {
//        BDDMockito.when(batchStockRepo.getSectorExpiredQuantity(ArgumentMatchers.anyString()))
//                .thenReturn(Optional.ofNullable(TestUtilsGenerator.getDataExpiredQuantityList()));
//
//        List<DataBaseExpiredQuantityImp> dataBaseExpiredQuantities = TestUtilsGenerator.getDataExpiredQuantityList();
//
//        String finantialLoss = batchStockImpServicee.getFinantialLoss("10");
//        Assertions.assertThat(finantialLoss).isEqualTo("O prejuízo do mês 10 foi de 33%");
//    }
}