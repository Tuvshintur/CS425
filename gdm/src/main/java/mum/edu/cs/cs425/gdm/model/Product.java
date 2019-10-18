package mum.edu.cs.cs425.gdm.model;

import mum.edu.cs.cs425.gdm.model.validator.UniqueProductNumber;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.Mapping;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long productId;

    @Digits(message = "* productNumber is required", fraction = 0, integer = 9)
    @Column(name = "productNumber", unique = true)
    @UniqueProductNumber(message = "product number already taken")
    private long productNumber;

    @NotBlank(message = "* name is required")
    @Column(name = "name", nullable = false)
    private String name;

    @DecimalMin("0.0")
    @Column(name = "unitPrice")
    private double unitPrice;

    @DecimalMin("0.0")
    @Column(name = "quantityInStock")
    private int quantityInStock;

    @NotNull(message = "* date supplied is required")
    @Column(name = "dateSupplied", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateSupplied;

    @ManyToOne()
    private Supplier supplier;

    public Product() {
    }

    public Product(@NotBlank(message = "* productNumber is required") long productNumber, @NotBlank(message = "* name is required") String name, @NotBlank(message = "* unitPrice is required") double unitPrice, @NotBlank(message = "* quantityInStock is required") int quantityInStock, @NotBlank(message = "* dateSupplied is required") Date dateSupplied) {
        this.productNumber = productNumber;
        this.name = name;
        this.unitPrice = unitPrice;
        this.quantityInStock = quantityInStock;
        this.dateSupplied = dateSupplied;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public long getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(long productNumber) {
        this.productNumber = productNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public Date getDateSupplied() {
        return dateSupplied;
    }

    public void setDateSupplied(Date dateSupplied) {
        this.dateSupplied = dateSupplied;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }
}
