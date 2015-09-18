package com.easymarket.core;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "supplierdepartment")
@NamedQueries({
    @NamedQuery(name = "com.easymarket.core.SupplierDepartment.findAll", query = "SELECT p FROM SupplierDepartment p"),
    @NamedQuery(name = "com.easymarket.core.SupplierDepartment.findDepartmentBasedOnSupplierType", query = "SELECT p FROM SupplierDepartment p where p.supplierType =:supplierType")

})
public class SupplierDepartment extends ViewResource implements IBaseResource {

    private String supplierType;

    public SupplierDepartment() {}

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SupplierDepartment)) {
            return false;
        }

        final SupplierDepartment that = (SupplierDepartment) o;

        return Objects.equals(this.getId(), that.getId())
                && Objects.equals(this.getName(), that.getName())
                && Objects.equals(this.getDescription(), that.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDescription());
    }

    public String getSupplierType() {
        return supplierType;
    }

    public void setSupplierType(String supplierType) {
        this.supplierType = supplierType;
    }
}
