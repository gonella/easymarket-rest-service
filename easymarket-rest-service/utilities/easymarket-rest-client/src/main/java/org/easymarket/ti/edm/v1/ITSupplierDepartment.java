package org.easymarket.ti.edm.v1;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class ITSupplierDepartment extends ITBaseResource {

    private String supplierType;

    public ITSupplierDepartment() {}

    public String getSupplierType() {
        return supplierType;
    }

    public void setSupplierType(String supplierType) {
        this.supplierType = supplierType;
    }

    @Override
    public String toString(){
        String string = ReflectionToStringBuilder.toString(this);
        return string;
    }
}
