package mum.edu.cs.cs425.gdm.repository;

import mum.edu.cs.cs425.gdm.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    Optional<Product> findProductByProductNumber(long productNumber);

    Page<Product> findProductsByNameStartsWith(String searchString, Pageable pageable);
}
