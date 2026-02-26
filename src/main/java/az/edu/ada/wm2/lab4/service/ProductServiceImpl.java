package az.edu.ada.wm2.lab4.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import org.springframework.stereotype.Service;
import az.edu.ada.wm2.lab4.model.Product;
import az.edu.ada.wm2.lab4.repository.ProductRepository;
import az.edu.ada.wm2.lab4.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(Product product) {
        if (product.getId() == null) {
            UUID newId = UUID.randomUUID();
            product = new Product(newId, product.getProductName(), product.getPrice(), product.getExpirationDate());
        }
        return productRepository.save(product);
    }

    public Product getProductById(UUID id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product updateProduct(UUID id, Product updatedProduct) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Product not found with id: " + id);
        }
        return productRepository.save(new Product(id, updatedProduct.getProductName(), updatedProduct.getPrice(), updatedProduct.getExpirationDate()));
    }

    public void deleteProduct(UUID id) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Product not found with id: " + id);
        }
        productRepository.deleteById(id);
    }

    public List<Product> getProductsExpiringBefore(LocalDate date) {
        return productRepository.findAll().stream()
                .filter(product -> product.getExpirationDate().isBefore(date))
                .toList();
    }

    public List<Product> getProductsByPriceRange(BigDecimal min, BigDecimal max) {
        return productRepository.findAll().stream()
                .filter(product -> product.getPrice().compareTo(min) >= 0 && product.getPrice().compareTo(max) <= 0)
                .toList();
    }
}