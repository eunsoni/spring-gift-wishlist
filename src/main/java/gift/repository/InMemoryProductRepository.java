package gift.repository;

import gift.domain.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 메모리 기반 상품 Repository 구현체
 * 학습용으로 간단한 메모리 저장소를 사용합니다.
 */
@Repository
public class InMemoryProductRepository implements ProductRepository {
    
    private final List<Product> products = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);
    
    public InMemoryProductRepository() {
        // 초기 데이터 추가
        initializeData();
    }
    
    private void initializeData() {
        save(new Product("카카오 머그컵", 15000, "https://example.com/mug.jpg"));
        save(new Product("카카오 텀블러", 25000, "https://example.com/tumbler.jpg"));
        save(new Product("카카오 에코백", 18000, "https://example.com/ecobag.jpg"));
    }
    
    @Override
    public List<Product> findAll() {
        return new ArrayList<>(products);
    }
    
    @Override
    public Optional<Product> findById(Long id) {
        return products.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst();
    }
    
    @Override
    public Product save(Product product) {
        if (product.getId() == null) {
            // 새로운 상품 추가
            product.setId(idGenerator.getAndIncrement());
            products.add(product);
        } else {
            // 기존 상품 수정
            for (int i = 0; i < products.size(); i++) {
                if (products.get(i).getId().equals(product.getId())) {
                    products.set(i, product);
                    break;
                }
            }
        }
        return product;
    }
    
    @Override
    public void deleteById(Long id) {
        products.removeIf(product -> product.getId().equals(id));
    }
}
