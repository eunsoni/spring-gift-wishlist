package gift.repository;

import gift.domain.Product;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * 상품 데이터 접근을 위한 인터페이스
 */
@Repository
public interface ProductRepository {
    
    /**
     * 모든 상품을 조회합니다.
     * @return 상품 리스트
     */
    List<Product> findAll();
    
    /**
     * ID로 상품을 조회합니다.
     * @param id 상품 ID
     * @return 상품 정보 (Optional)
     */
    Optional<Product> findById(Long id);
    
    /**
     * 상품을 저장합니다.
     * @param product 저장할 상품
     * @return 저장된 상품
     */
    Product save(Product product);
    
    /**
     * ID로 상품을 삭제합니다.
     * @param id 삭제할 상품 ID
     */
    void deleteById(Long id);
}
