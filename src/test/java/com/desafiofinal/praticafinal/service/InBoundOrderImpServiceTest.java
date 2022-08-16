package com.desafiofinal.praticafinal.service;

import com.desafiofinal.praticafinal.dto.BatchStockDTO;
import com.desafiofinal.praticafinal.dto.SectorDTO;
import com.desafiofinal.praticafinal.dto.requestResponseDto.InboundOrderRequestDTO;
import com.desafiofinal.praticafinal.exception.ElementAlreadyExistsException;
import com.desafiofinal.praticafinal.exception.ElementNotFoundException;
import com.desafiofinal.praticafinal.model.*;
import com.desafiofinal.praticafinal.repository.IBatchStockRepo;
import com.desafiofinal.praticafinal.repository.IProductRepo;
import com.desafiofinal.praticafinal.repository.ISectorRepo;
import com.desafiofinal.praticafinal.repository.InBoundOrderRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class InBoundOrderImpServiceTest {

    @Mock
    InBoundOrderRepo inBoundOrderRepo;

    @Mock
    IBatchStockRepo batchStockRepo;

    @Mock
    ISectorRepo sectorRepo;

    @Mock
    IProductRepo productRepo;


    @Captor
    ArgumentCaptor<InBoundOrder> captor;

    @BeforeEach
    public void setup(){}

    @Test
    void saveInBoundOrder() throws Exception {

        var manager = new Manager(1L, "AMANDA", null);
        var wareHouse =  new WareHouse(1L, manager, Collections.emptyList());
        var sector = new Sector(1L, "Carne",
                12,
                Collections.emptyList(),
                wareHouse,
                34);
        var inBoundOrder = new InBoundOrder(1L, new Date(), Collections.emptyList(), sector );
        var seller = new Seller(1L, "TESTE", Collections.emptySet());
        var sectorDTO = new SectorDTO(sector);

        when(inBoundOrderRepo.findById(anyLong())).thenReturn(Optional.empty());
        when(sectorRepo.findById(anyLong())).thenReturn(Optional.of(sector));
        when(inBoundOrderRepo.save(any(InBoundOrder.class))).thenReturn(inBoundOrder);

        var service = new InBoundOrderImpService(inBoundOrderRepo, batchStockRepo, sectorRepo, productRepo);
        var inboundOrderCreated =  service.saveInBoundOrder(new InboundOrderRequestDTO(20L, new Date(), sectorDTO,Collections.emptyList() ));

        org.junit.jupiter.api.Assertions.assertEquals(inBoundOrder.getOrderId(), inboundOrderCreated.getOrderId());
        org.junit.jupiter.api.Assertions.assertEquals(inBoundOrder.getDateTime(), inboundOrderCreated.getDateTime());
        org.junit.jupiter.api.Assertions.assertEquals(inBoundOrder.getBatchStockList(), inboundOrderCreated.getBatchStockList());
        org.junit.jupiter.api.Assertions.assertEquals(inBoundOrder.getSector(), inboundOrderCreated.getSector());

    }

    @Test
    void updateInBoundOrder() throws Exception {
        var manager = new Manager(1L, "AMANDA", null);
        var wareHouse =  new WareHouse(1L, manager, Collections.emptyList());
        var sector = new Sector(1L, "Carne", 12, Collections.emptyList(), wareHouse, 34 );
        var seller = new Seller(1L, "TESTE", Collections.emptySet());
        var sectorDTO = new SectorDTO(sector);
        var inboundOrder = new InBoundOrder(1L,new Date() , Collections.emptyList(), sector );
        var product = new Product(1L, new Date(), 13, "frios", "frango", seller, 13, Collections.emptyList());
        var batchStock = new BatchStock(1L, 12, 14, 20L, 7L, LocalDate.parse("2023-01-01"), LocalDate.parse("2023-01-01"), LocalDate.parse("2023-01-01"), inboundOrder, product );
        List<BatchStockDTO> batchStockListDTO= Collections.singletonList(new BatchStockDTO(batchStock));
        var requestDTO = new InboundOrderRequestDTO(1L, new Date(), sectorDTO, batchStockListDTO);

        when(inBoundOrderRepo.findById(anyLong())).thenReturn(Optional.of(inboundOrder));
        when(sectorRepo.findById(anyLong())).thenReturn(Optional.of(sector));
        when(productRepo.findById(anyLong())).thenReturn(Optional.of(product));
        when(batchStockRepo.findById(anyLong())).thenReturn(Optional.of(batchStock));
        when(batchStockRepo.save(any(BatchStock.class))).thenReturn(batchStock);
        when(inBoundOrderRepo.save(any(InBoundOrder.class))).thenReturn(inboundOrder);
        var service = new InBoundOrderImpService(inBoundOrderRepo, batchStockRepo, sectorRepo, productRepo);

        var inboundOrderUpdated =  service.updateInBoundOrder(requestDTO);

        org.junit.jupiter.api.Assertions.assertNotNull(inboundOrderUpdated);
        verify(inBoundOrderRepo).save(captor.capture());

        var batchStockCaptured = captor.getValue().getBatchStockList().get(0);

        org.junit.jupiter.api.Assertions.assertEquals(inboundOrder.getOrderId(), captor.getValue().getOrderId());
        org.junit.jupiter.api.Assertions.assertEquals(sector, captor.getValue().getSector());
        org.junit.jupiter.api.Assertions.assertEquals(batchStock.getBatchId(), batchStockCaptured.getBatchId());
        org.junit.jupiter.api.Assertions.assertEquals(batchStock.getCurrentQuantity(), batchStockCaptured.getCurrentQuantity());
        org.junit.jupiter.api.Assertions.assertEquals(batchStock.getInitialQuantity(), batchStockCaptured.getInitialQuantity());
        Assertions.assertEquals(batchStock.getMinimumTemperature(), batchStockCaptured.getMinimumTemperature());
    }
//    @Test
//    void saveInBoundOrder_whenNewOrder() {
//        BDDMockito.when(inBoundOrderRepo.save(ArgumentMatchers.any(InBoundOrder.class)))
//                .thenReturn(TestUtilsGenerator.getOrderWithId());
//        BDDMockito.when(productRepo.findById(ArgumentMatchers.anyLong()))
//                .thenReturn(Optional.ofNullable(TestUtilsGenerator.getProductWhitId()));
//        BDDMockito.when(batchStockRepo.findById(ArgumentMatchers.anyLong()))
//                .thenReturn(Optional.ofNullable(TestUtilsGenerator.getBatchStockAndSectorWithCapacity()));
//        BDDMockito.when(sectorRepo.findById(ArgumentMatchers.anyLong()))
//                .thenReturn(Optional.ofNullable(TestUtilsGenerator.getSector()));
//
//        InBoundOrder newOrder = TestUtilsGenerator.getNewOrder();
//
//        InBoundOrder savedOrder = inBoundOrderImpService.saveInBoundOrder(newOrder);
//
//        Assertions.assertThat(savedOrder).isNotNull();
//        Assertions.assertThat(savedOrder.getBatchStockList()).isNotNull();
//        Assertions.assertThat(savedOrder.getBatchStockList().size()).isEqualTo(3);
//        Mockito.verify(inBoundOrderRepo, Mockito.atLeastOnce()).save(newOrder);
//        Mockito.verify(batchStockRepo,Mockito.atLeastOnce()).saveAll(newOrder.getBatchStockList());
//    }
//
//    @Test
//    void saveInBoundOrder_throwException_whenAlreadyExists() {
//        BDDMockito.when(inBoundOrderRepo.findById(ArgumentMatchers.anyLong()))
//                .thenReturn(Optional.ofNullable(TestUtilsGenerator.getOrderWithId()));
//
//        InBoundOrder newOrder = TestUtilsGenerator.getNewOrder();
//
//        Assertions.assertThatThrownBy(()
//                        -> inBoundOrderImpService.saveInBoundOrder(newOrder))
//                .isInstanceOf(ElementAlreadyExistsException.class)
//                .hasMessageContaining("Order already exists");
//    }
//
//    @Test
//    void saveInBoundOrder_throwException_whenProductNotExists(){
//
//        InBoundOrder newOrder = TestUtilsGenerator.getNewOrder();
//
//        Assertions.assertThatThrownBy(()
//        -> inBoundOrderImpService.saveInBoundOrder(newOrder))
//                .isInstanceOf(ElementNotFoundException.class)
//                .hasMessageContaining("Product does not exist");
//    }
//
//    @Test
//    void saveInBoundOrder_throwException_whenSectorNotExists(){
//        BDDMockito.when(productRepo.findById(ArgumentMatchers.anyLong()))
//                .thenReturn(Optional.ofNullable(TestUtilsGenerator.getProductWhitId()));
//
//        InBoundOrder newOrder = TestUtilsGenerator.getNewOrder();
//
//        Assertions.assertThatThrownBy(()
//        -> inBoundOrderImpService.saveInBoundOrder(newOrder))
//                .isInstanceOf(ElementNotFoundException.class)
//                .hasMessageContaining("Sector does not exist");
//    }
//
//    @Test
//    void updateInBoundOrder_whenOrderExists(){
//        BDDMockito.when(inBoundOrderRepo.findById(ArgumentMatchers.anyLong()))
//                .thenReturn(Optional.ofNullable(TestUtilsGenerator.getOrderWithId()));
//        BDDMockito.when(inBoundOrderRepo.save(ArgumentMatchers.any(InBoundOrder.class)))
//                .thenReturn(TestUtilsGenerator.getOrderWithId());
//        BDDMockito.when(productRepo.findById(ArgumentMatchers.anyLong()))
//                .thenReturn(Optional.ofNullable(TestUtilsGenerator.getProductWhitId()));
//        BDDMockito.when(batchStockRepo.findById(ArgumentMatchers.anyLong()))
//                .thenReturn(Optional.ofNullable(TestUtilsGenerator.getBatchStockAndSectorWithCapacity()));
//        BDDMockito.when(sectorRepo.findById(ArgumentMatchers.anyLong()))
//                .thenReturn(Optional.ofNullable(TestUtilsGenerator.getSector()));
//
//        InBoundOrder order = TestUtilsGenerator.getOrderWithId();
//        InBoundOrder updatedOrder = inBoundOrderImpService.updateInBoundOrder(order);
//
//        Assertions.assertThat(updatedOrder).isNotNull();
//        Assertions.assertThat(updatedOrder.getBatchStockList()).isNotNull();
//        Assertions.assertThat(updatedOrder.getBatchStockList().size()).isEqualTo(3);
//        Mockito.verify(inBoundOrderRepo, Mockito.atLeastOnce()).save(order);
//        Mockito.verify(batchStockRepo, Mockito.atLeastOnce()).save(order.getBatchStockList().get(0));
//    }
//
//    @Test
//    void updateInBoundOrder_throwException_whenNotExists(){
//        BDDMockito.when(inBoundOrderRepo.findById(ArgumentMatchers.anyLong()))
//                .thenReturn(Optional.ofNullable(TestUtilsGenerator.getNewOrder()));
//
//        InBoundOrder order = TestUtilsGenerator.getOrderWithId();
//
//        Assertions.assertThatThrownBy(()
//                        -> inBoundOrderImpService.updateInBoundOrder(order))
//                .isInstanceOf(ElementNotFoundException.class)
//                .hasMessageContaining("Inbound does not exists");
//    }
//
//    @Test
//    void updateInBoundOrder_throwException_whenBatchStockNotExists(){
//        BDDMockito.when(inBoundOrderRepo.findById(ArgumentMatchers.anyLong()))
//                .thenReturn(Optional.ofNullable(TestUtilsGenerator.getOrderWithId()));
//        BDDMockito.when(inBoundOrderRepo.save(ArgumentMatchers.any(InBoundOrder.class)))
//                .thenReturn(TestUtilsGenerator.getOrderWithId());
//        BDDMockito.when(productRepo.findById(ArgumentMatchers.anyLong()))
//                .thenReturn(Optional.ofNullable(TestUtilsGenerator.getProductWhitId()));
//        BDDMockito.when(sectorRepo.findById(ArgumentMatchers.anyLong()))
//                .thenReturn(Optional.ofNullable(TestUtilsGenerator.getSector()));
//
//        InBoundOrder order = TestUtilsGenerator.getOrderWithId();
//
//        Assertions.assertThatThrownBy(()
//                        -> inBoundOrderImpService.updateInBoundOrder(order))
//                .isInstanceOf(ElementNotFoundException.class)
//                .hasMessageContaining("Batch stock does not exist");
//    }
//    @Test
//    void updateInBoundOrder_throwException_whenBatchStockNotBelongsToTheOrder(){
//        BDDMockito.when(inBoundOrderRepo.findById(ArgumentMatchers.anyLong()))
//                .thenReturn(Optional.ofNullable(TestUtilsGenerator.getOrderWithId()));
//        BDDMockito.when(inBoundOrderRepo.save(ArgumentMatchers.any(InBoundOrder.class)))
//                .thenReturn(TestUtilsGenerator.getOrderWithId());
//        BDDMockito.when(batchStockRepo.findById(ArgumentMatchers.anyLong()))
//                .thenReturn(Optional.ofNullable(TestUtilsGenerator.getBatchStockWithoutOrder()));
//        BDDMockito.when(productRepo.findById(ArgumentMatchers.anyLong()))
//                .thenReturn(Optional.ofNullable(TestUtilsGenerator.getProductWhitId()));
//        BDDMockito.when(sectorRepo.findById(ArgumentMatchers.anyLong()))
//                .thenReturn(Optional.ofNullable(TestUtilsGenerator.getSector()));
//
//        InBoundOrder order = TestUtilsGenerator.getOrderWithId();
//
//        Assertions.assertThatThrownBy(()
//                        -> inBoundOrderImpService.updateInBoundOrder(order))
//                .isInstanceOf(ElementNotFoundException.class)
//                .hasMessageContaining("Batch stock does not belong to this inbound order");
//    }
}