// package com.desafiofinal.praticafinal.service;

// import static org.mockito.ArgumentMatchers.anyLong;
// import static org.mockito.Mockito.when;

// import java.util.List;
// import java.util.Optional;

// import org.assertj.core.api.Assertions;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.junit.jupiter.MockitoExtension;

// import com.desafiofinal.praticafinal.Builder.SellerBuilder;
// import com.desafiofinal.praticafinal.model.Seller;
// import com.desafiofinal.praticafinal.repository.ISellerRepo;

// @ExtendWith(MockitoExtension.class)
// public class SellerServiceTest {
    
//     @InjectMocks
//     SellerImplService sellerService;

//     @Mock
//     ISellerRepo sellerRepo;

//     @Test
//     void testGetAll() {

//         // Seller seller = SellerBuilder.aSeller().create();
//         // List<Seller> sellersList = SellerBuilder.aListOfSellers();
//         // when(sellerRepo.findAll()).thenReturn(sellersList);
//         List<Seller> allSellers = sellerRepo.findAll();
//         Assertions.assertThat(allSellers).isNotNull();
//         // when(sellerRepo.findById(anyLong())).thenReturn(Optional.of(seller));           
//     }

//     @Test
//     void testGetById() {
//         Seller seller = SellerBuilder.aSeller().create();
//         when(sellerRepo.findById(anyLong())).thenReturn(Optional.of(seller));
        
//         Seller sellerById = sellerService.getSellerById(seller.getId());

//         Assertions.assertThat(sellerById).isNotNull();
        
//     }
// }
