package org.easymarket.ti.edm.v1;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class ITProduct extends ITBaseResource {

    private ITBarcode barcode;

    private Double price;

    private ITSupplierDepartment department;

    private String category;

    private ITSupplier supplier;

    public ITProduct() {
    }

    public ITBarcode getBarcode() {
        return barcode;
    }

    public void setBarcode(ITBarcode barcode) {
        this.barcode = barcode;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public ITSupplier getSupplier() {
        return supplier;
    }

    public void setSupplier(ITSupplier supplier) {
        this.supplier = supplier;
    }

    @Override
    public String toString() {
        String string = ReflectionToStringBuilder.toString(this);
        return string;
    }

    public ITSupplierDepartment getDepartment() {
        return department;
    }

    public void setDepartment(ITSupplierDepartment department) {
        this.department = department;
    }
}
