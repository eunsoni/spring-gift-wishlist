package gift.service;

import gift.domain.Product;
import gift.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 상품 관리 서비스
 * 상품 관련 비즈니스 로직을 처리합니다.
 */
@Service
public class ProductService {
    
    private final ProductRepository productRepository;
    
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    
    /**
     * 모든 상품을 조회합니다.
     * @return 상품 리스트
     */
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    
    /**
     * ID로 상품을 조회합니다.
     * @param id 상품 ID
     * @return 상품 정보
     * @throws IllegalArgumentException 상품을 찾을 수 없는 경우
     */
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다. ID: " + id));
    }
    
    /**
     * 새로운 상품을 추가합니다.
     * @param product 추가할 상품
     * @return 저장된 상품
     */
    public Product createProduct(Product product) {
        validateProduct(product);
        return productRepository.save(product);
    }
    
    /**
     * 기존 상품을 수정합니다.
     * @param id 수정할 상품 ID
     * @param updatedProduct 수정할 상품 정보
     * @return 수정된 상품
     */
    public Product updateProduct(Long id, Product updatedProduct) {
        // 상품 존재 여부 확인
        getProductById(id);
        
        validateProduct(updatedProduct);
        
        updatedProduct.setId(id);
        return productRepository.save(updatedProduct);
    }
    
    /**
     * 상품을 삭제합니다.
     * @param id 삭제할 상품 ID
     */
    public void deleteProduct(Long id) {
        getProductById(id); // 존재 여부 확인
        productRepository.deleteById(id);
    }
    
    /**
     * 상품 정보를 검증합니다.
     * @param product 검증할 상품
     */
    private void validateProduct(Product product) {
        if (product.getName() == null || product.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("상품명은 필수입니다.");
        }
        if (product.getPrice() < 0) {
            throw new IllegalArgumentException("가격은 0 이상이어야 합니다.");
        }
    }
}
