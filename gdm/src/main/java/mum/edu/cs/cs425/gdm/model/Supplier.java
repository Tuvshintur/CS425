package mum.edu.cs.cs425.gdm.model;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "suppliers")
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int supplierId;

    @Digits(message = "* supplierNumber is required", fraction = 0, integer = 9)
    @Column(name = "supplierNumber", unique = true, nullable = false)
    private int supplierNumber;

    @NotBlank(message = "* name is required")
    @Column(name = "name", nullable = false)
    private String name;

    @NotBlank(message = "* contactPhoneNumber is required")
    @Column(name = "contactPhoneNumber", nullable = false)
    private String contactPhoneNumber;

    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Product> productList;

    public Supplier() {
    }

    public Supplier(@NotBlank(message = "* supplierNumber is required") int supplierNumber, @NotBlank(message = "* name is required") String name, @NotBlank(message = "* contactPhoneNumber is required") String contactPhoneNumber) {
        this.supplierNumber = supplierNumber;
        this.name = name;
        this.contactPhoneNumber = contactPhoneNumber;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public int getSupplierNumber() {
        return supplierNumber;
    }

    public void setSupplierNumber(int supplierNumber) {
        this.supplierNumber = supplierNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactPhoneNumber() {
        return contactPhoneNumber;
    }

    public void setContactPhoneNumber(String contactPhoneNumber) {
        this.contactPhoneNumber = contactPhoneNumber;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
