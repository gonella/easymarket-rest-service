package com.easymarket.core.param;

import javax.ws.rs.QueryParam;

public class ProductRequest extends NearByRequest{

    @QueryParam("departmentId")
    private Long departmentId;

    //TODO - Ongoing :(
    //@QueryParam("supplierIds")
    //private List<String> supplierIds;


    public ProductRequest(){}

    /*
    public List<String> getSupplierIds() {
        return supplierIds;
    }


    public void setSupplierIds(List<String> supplierIds) {
        this.supplierIds = supplierIds;
    }*/


    public Long getDepartmentId() {
        return departmentId;
    }


    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    };

}
