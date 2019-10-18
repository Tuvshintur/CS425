package mum.edu.cs.cs425.gdm.repository;

import mum.edu.cs.cs425.gdm.model.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Integer> {

    Page<Supplier> findSuppliersByNameStartsWith(String searchString, Pageable pageable);
}
