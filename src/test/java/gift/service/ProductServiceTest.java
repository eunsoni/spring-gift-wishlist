package gift.service;

import gift.domain.Product;
import gift.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("ProductService 테스트")
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    private Product testProduct;

    @BeforeEach
    void setUp() {
        testProduct = new Product(1L, "테스트 상품", 10000, "test.jpg");
    }

    @Test
    @DisplayName("모든 상품 조회")
    void getAllProducts() {
        // given
        List<Product> products = Arrays.asList(testProduct);
        when(productRepository.findAll()).thenReturn(products);

        // when
        List<Product> result = productService.getAllProducts();

        // then
        assertEquals(1, result.size());
        assertEquals("테스트 상품", result.get(0).getName());
        verify(productRepository).findAll();
    }

    @Test
    @DisplayName("ID로 상품 조회 - 성공")
    void getProductById_Success() {
        // given
        when(productRepository.findById(1L)).thenReturn(Optional.of(testProduct));

        // when
        Product result = productService.getProductById(1L);

        // then
        assertEquals("테스트 상품", result.getName());
        verify(productRepository).findById(1L);
    }

    @Test
    @DisplayName("ID로 상품 조회 - 실패 (상품 없음)")
    void getProductById_NotFound() {
        // given
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        // when & then
        assertThrows(IllegalArgumentException.class, () -> {
            productService.getProductById(1L);
        });
        verify(productRepository).findById(1L);
    }

    @Test
    @DisplayName("상품 생성 - 성공")
    void createProduct_Success() {
        // given
        Product newProduct = new Product("새 상품", 15000, "new.jpg");
        when(productRepository.save(any(Product.class))).thenReturn(newProduct);

        // when
        Product result = productService.createProduct(newProduct);

        // then
        assertEquals("새 상품", result.getName());
        verify(productRepository).save(newProduct);
    }

    @Test
    @DisplayName("상품 생성 - 실패 (상품명 없음)")
    void createProduct_InvalidName() {
        // given
        Product invalidProduct = new Product("", 15000, "new.jpg");

        // when & then
        assertThrows(IllegalArgumentException.class, () -> {
            productService.createProduct(invalidProduct);
        });
        verify(productRepository, never()).save(any());
    }

    @Test
    @DisplayName("상품 생성 - 실패 (음수 가격)")
    void createProduct_InvalidPrice() {
        // given
        Product invalidProduct = new Product("상품", -1000, "new.jpg");

        // when & then
        assertThrows(IllegalArgumentException.class, () -> {
            productService.createProduct(invalidProduct);
        });
        verify(productRepository, never()).save(any());
    }

    @Test
    @DisplayName("상품 수정 - 성공")
    void updateProduct_Success() {
        // given
        Product updatedProduct = new Product("수정된 상품", 20000, "updated.jpg");
        when(productRepository.findById(1L)).thenReturn(Optional.of(testProduct));
        when(productRepository.save(any(Product.class))).thenReturn(updatedProduct);

        // when
        Product result = productService.updateProduct(1L, updatedProduct);

        // then
        assertEquals("수정된 상품", result.getName());
        verify(productRepository).findById(1L);
        verify(productRepository).save(any(Product.class));
    }

    @Test
    @DisplayName("상품 삭제 - 성공")
    void deleteProduct_Success() {
        // given
        when(productRepository.findById(1L)).thenReturn(Optional.of(testProduct));

        // when
        productService.deleteProduct(1L);

        // then
        verify(productRepository).findById(1L);
        verify(productRepository).deleteById(1L);
    }

    @Test
    @DisplayName("상품 삭제 - 실패 (상품 없음)")
    void deleteProduct_NotFound() {
        // given
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        // when & then
        assertThrows(IllegalArgumentException.class, () -> {
            productService.deleteProduct(1L);
        });
        verify(productRepository).findById(1L);
        verify(productRepository, never()).deleteById(anyLong());
    }
}
