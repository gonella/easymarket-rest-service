package com.easymarket.service;

import java.util.List;

import com.easymarket.core.SupplierDepartment;

public interface SupplierDepartmentService {

    public SupplierDepartment createSupplierDepartment(SupplierDepartment product);
    public void deleteSupplierDepartment(Long id);
    public SupplierDepartment getSupplierDepartment(Long id);
    public List<SupplierDepartment> listSupplierDepartment();
    public List<SupplierDepartment> findDepartmentBasedOnSupplierType(String supplierType);
}
