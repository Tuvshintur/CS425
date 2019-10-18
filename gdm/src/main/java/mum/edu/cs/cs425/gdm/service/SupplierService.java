package mum.edu.cs.cs425.gdm.service;

import mum.edu.cs.cs425.gdm.model.Product;
import mum.edu.cs.cs425.gdm.model.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

public interface SupplierService {

    public Iterable<Supplier> getAllSuppliers();
    public Page<Supplier> getAllSuppliersPaged(int pageNo);
    public Supplier saveSupplier(Supplier supplier);
    public Page<Supplier> getSuppliersByName(String searchString, int pageNo);
    public Supplier getSupplierById(Integer supplierId);
    public void deleteSupplierById(Integer supplierId);
}
