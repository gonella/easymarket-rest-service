package com.easymarket.core;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

@Entity
@Table
@NamedQueries({
    @NamedQuery(name = "com.easymarket.core.Product.findAll"                                    , query = "SELECT p FROM Product p"),
    @NamedQuery(name = "com.easymarket.core.Product.findProductsFromSuppliers"                  , query = "SELECT p FROM Product p where p.supplier.id in :ids"),
    @NamedQuery(name = "com.easymarket.core.Product.findProductsFromSuppliersBasedOnDepartment" , query = "SELECT p FROM Product p where p.department.id =:departmentId AND p.supplier.id in :ids")
})
public class Product extends ViewResource implements java.io.Serializable, IBaseResource {

    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    private Barcode barcode;

    @Column(nullable = false)
    private Double price;

    @OneToOne
    private SupplierDepartment department;

    @Column(nullable = false)
    private String category;

    @OneToOne
    private Supplier supplier;

    public Product() {
    }

    public Product(String name) {
        this.setName(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Product)) {
            return false;
        }

        final Product that = (Product) o;

        return Objects.equals(this.getId(), that.getId())
                && Objects.equals(this.getName(), that.getName())
                && Objects.equals(this.getDescription(), that.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDescription());
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public SupplierDepartment getDepartment() {
        return department;
    }

    public void setDepartment(SupplierDepartment department) {
        this.department = department;
    }

    public Barcode getBarcode() {
        return barcode;
    }

    public void setBarcode(Barcode barcode) {
        this.barcode = barcode;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }
    @Override
    public String toString(){
        String string = ReflectionToStringBuilder.toString(this);
        return string;
    }
}
