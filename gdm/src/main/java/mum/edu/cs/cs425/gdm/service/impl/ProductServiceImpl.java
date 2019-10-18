package mum.edu.cs.cs425.gdm.service.impl;

import mum.edu.cs.cs425.gdm.model.Product;
import mum.edu.cs.cs425.gdm.repository.ProductRepository;
import mum.edu.cs.cs425.gdm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Iterable<Product> getAllProducts() {
        return productRepository.findAll(Sort.by("name"));
    }

    @Override
    public Page<Product> getAllProductsPaged(int pageNo) {
        return productRepository.findAll(PageRequest.of(pageNo, 3, Sort.by("name")));
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product getProductById(Integer productId) {
        return productRepository.findById(productId).orElse(null);
    }

    @Override
    public Page<Product> getAllProductsByNamePaged(String searchString, int pageNo) {
        return productRepository.findProductsByNameStartsWith(searchString, PageRequest.of(pageNo, 3, Sort.by("name")));
    }

    @Override
    public Optional<Product> findByProductNumber(long productNumber) {
        return productRepository.findProductByProductNumber(productNumber);
    }

    @Override
    public void deleteProductById(Integer productId) {
        productRepository.deleteById(productId);
    }
}
