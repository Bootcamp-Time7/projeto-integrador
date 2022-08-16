package com.desafiofinal.praticafinal.service;

import com.desafiofinal.praticafinal.dto.queryDto.DataBaseQueryImp;
import com.desafiofinal.praticafinal.dto.queryDto.ResponseSectorQuery;
import com.desafiofinal.praticafinal.dto.queryDto.ResponseStockQuery;
import com.desafiofinal.praticafinal.exception.ElementNotFoundException;
import com.desafiofinal.praticafinal.model.Cart;
import com.desafiofinal.praticafinal.repository.*;
import com.desafiofinal.praticafinal.utils.TestUtilsGenerator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import javax.xml.crypto.Data;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class BatchStockImpServiceTest {

    @InjectMocks
    BatchStockImpService batchStockImpService;

    @Mock
    CartRepo cartRepo;

    @Mock
    IBatchStockRepo batchStockRepo;

    @Mock
    PurchaseRepo purchaseRepo;

    @Mock
    BuyerRepo buyerRepo;

    @Mock
    IProductRepo productRepo;

    @Mock
    ISectorRepo sectorRepo;

    @Test
    void listBatchStockByCategory() {

    }

    @Test
    void listBatchSector_whenListExists() {
        BDDMockito.when(batchStockRepo.getListBatchSector(ArgumentMatchers.anyLong()))
                .thenReturn(TestUtilsGenerator.getListDataBaseQuery());


       // List<DataBaseQueryImp> newList = TestUtilsGenerator.getListDataBaseQuery ();
        List<ResponseSectorQuery> newList = TestUtilsGenerator.getListResponseSectorQuery ();
        DataBaseQueryImp newBatch = TestUtilsGenerator.getDataBaseQueryImp();
        List<ResponseSectorQuery> foundList = batchStockImpService.listBatchSector(newBatch.getIdProduct());

        assertThat(foundList).isNotNull();
        assertThat(foundList).isEqualTo(newList);

    }

    @Test
    void listBatchSector_whenListDoesNotExist() {
        BDDMockito.when(batchStockRepo.getListBatchSector(ArgumentMatchers.anyLong()))
                .thenReturn(TestUtilsGenerator.getListDataBaseQuery());

        List<DataBaseQueryImp> newListDataBase = TestUtilsGenerator.getListDataBaseQuery ();
        DataBaseQueryImp newBatch = TestUtilsGenerator.getDataBaseQueryImp();

        assertThatThrownBy(() -> {
            batchStockRepo.getListBatchSector(newBatch.getId_product());

        }).isInstanceOf(ElementNotFoundException.class)
                .hasMessageContaining("There are no batchStock for this id");

    }

    @Test
    void listBatchSectorOrdered() {


    }

    @Test
    void getTotalQuantity() {
    }

    @Test
    void getListDueDate() {
    }

    @Test
    void getListCategoryDueDate() {
    }
}