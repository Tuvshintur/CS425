package mum.edu.cs.cs425.gdm.service.impl;

import mum.edu.cs.cs425.gdm.model.Supplier;
import mum.edu.cs.cs425.gdm.repository.SupplierRepository;
import mum.edu.cs.cs425.gdm.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;

    public SupplierServiceImpl(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Override
    public Iterable<Supplier> getAllSuppliers() {
        return supplierRepository.findAll(Sort.by("name"));
    }

    @Override
    public Page<Supplier> getAllSuppliersPaged(int pageNo) {
        return supplierRepository.findAll(PageRequest.of(pageNo, 3, Sort.by("name")));
    }

    @Override
    public Supplier saveSupplier(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    @Override
    public Page<Supplier> getSuppliersByName(String searchString, int pageNo) {
        return supplierRepository.findSuppliersByNameStartsWith(searchString, PageRequest.of(pageNo, 3, Sort.by("name")));
    }

    @Override
    public Supplier getSupplierById(Integer supplierId) {
        return supplierRepository.findById(supplierId).orElse(null);
    }

    @Override
    public void deleteSupplierById(Integer supplierId) {
        supplierRepository.deleteById(supplierId);
    }
}
