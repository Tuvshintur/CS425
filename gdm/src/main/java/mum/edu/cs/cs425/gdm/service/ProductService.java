package mum.edu.cs.cs425.gdm.service;

import mum.edu.cs.cs425.gdm.model.Product;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface ProductService {

    public Iterable<Product> getAllProducts();
    public Page<Product> getAllProductsPaged(int pageNo);
    public Product saveProduct(Product product);
    public Product getProductById(Integer productId);
    public Page<Product> getAllProductsByNamePaged(String searchString, int pageNo);
    public Optional<Product> findByProductNumber(long productNumber);
    public void deleteProductById(Integer productId);
}
