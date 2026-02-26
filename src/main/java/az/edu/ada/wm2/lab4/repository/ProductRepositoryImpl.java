package az.edu.ada.wm2.lab4.repository;

import az.edu.ada.wm2.lab4.model.Product;
import az.edu.ada.wm2.lab4.repository.ProductRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private final Map<UUID, Product> storage = new HashMap<>();

    public Product save(Product product) {
        if (product.getId() == null) {
            UUID newId = UUID.randomUUID();
            product = new Product(newId, product.getProductName(), product.getPrice(), product.getExpirationDate());
        }
        storage.put(product.getId(), product);
        return product;
    }

    public Optional<Product> findById(UUID id) {
        return Optional.ofNullable(storage.get(id));
    }

    public List<Product> findAll() {
        return new ArrayList<>(storage.values());
    }

    public void deleteById(UUID id) {
        storage.remove(id);
    }

    public boolean existsById(UUID id) {
        return storage.containsKey(id);
    }
}