package com.desafiofinal.praticafinal.service;

import com.desafiofinal.praticafinal.dto.queryDto.*;
import com.desafiofinal.praticafinal.exception.ElementAlreadyExistsException;
import com.desafiofinal.praticafinal.exception.ElementNotFoundException;
import com.desafiofinal.praticafinal.model.BatchStock;
import com.desafiofinal.praticafinal.model.Sector;
import com.desafiofinal.praticafinal.model.WareHouse;
import com.desafiofinal.praticafinal.repository.IBatchStockRepo;
import com.desafiofinal.praticafinal.repository.ISectorRepo;
import com.desafiofinal.praticafinal.repository.IWareHouseRepo;
import com.desafiofinal.praticafinal.utils.TestUtilsGenerator;
import com.desafiofinal.praticafinal.utils.TestUtilsReq6;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class BatchStockImpServiceTest {

    @InjectMocks
    private BatchStockImpService batchStockImpService;

    @Mock
    ISectorRepo sectorRepo;

    @Mock
    IBatchStockRepo batchStockRepo;

    @Mock
    IWareHouseRepo wareHouseRepo;


    @Test
    void transferToSector_whenThereAreExpiredProducts() {
        List<BatchStock> batchStockList = TestUtilsReq6.getBatchStockList();
        BDDMockito.when(batchStockRepo.findAll())
                .thenReturn(batchStockList);

        DataBaseExpired dataBaseExpired = TestUtilsReq6.getSectorExpiredWithIdImp();
        BDDMockito.when(batchStockRepo.getSectorExpired())
                .thenReturn(dataBaseExpired);

        Optional<WareHouse> wareHouse = Optional.of(TestUtilsReq6.getWareHouse());
        BDDMockito.when(wareHouseRepo.findById(ArgumentMatchers.anyLong()))
                .thenReturn(wareHouse);

        ResponseStock foundResponseStock = TestUtilsReq6.getResponseStock();
        ResponseStock updatedSector = batchStockImpService.transferToSector();

        assertThat(updatedSector).isNotNull();
        assertThat(updatedSector).isEqualTo(foundResponseStock);
        assertThat(updatedSector.getDataBaseStocks().size()).isEqualTo(3);
        assertThat(updatedSector.getDataBaseStocks().get(0).getDueDate().isBefore(LocalDate.now())).isTrue();
    }

    @Test
    void transferToSector_whenThereAreNoExpiredProducts() {
        DataBaseExpired dataBaseExpired = TestUtilsReq6.getSectorExpiredWithIdImp();
        BDDMockito.when(batchStockRepo.getSectorExpired())
                .thenReturn(dataBaseExpired);

        Optional<WareHouse> wareHouse = Optional.of(TestUtilsReq6.getWareHouse());
        BDDMockito.when(wareHouseRepo.findById(ArgumentMatchers.anyLong()))
                .thenReturn(wareHouse);

        Assertions.assertThatThrownBy(()
                -> batchStockImpService.transferToSector())
                .isInstanceOf(ElementNotFoundException.class)
                .hasMessageContaining("There are no expired products");

    }


    @Test
    void getFinantialLoss_whenThereAreProductsExpired() {
        List<DataBaseExpiredQuantity> dataBaseExpiredQuantity = TestUtilsReq6.getDataBaseExpiredQuantityList();

        BDDMockito.when(batchStockRepo.getSectorExpiredQuantity(ArgumentMatchers.anyString()))
                .thenReturn(dataBaseExpiredQuantity);

        String finantialLoss = batchStockImpService.getFinantialLoss("10");

        Assertions.assertThat(finantialLoss).isEqualTo("O prejuízo do mês 10 foi de 50.0%");
    }
    @Test
    void getFinantialLoss_whenThereAreNoProductsExpired() {

        Assertions.assertThatThrownBy(()
                -> batchStockImpService.getFinantialLoss("10"))
                .isInstanceOf(ElementNotFoundException.class)
                .hasMessageContaining("There are no expired products this month");
    }

    @Test
    void getAnualLoss_whenThereAreProductsExpired() {
       String anualLoss = batchStockImpService.getAnualLoss();
       Assertions.assertThat(anualLoss).isEqualTo("The anual finantial loss is predicted by the following line equation: Y = 0*x +0") ;
    }

}