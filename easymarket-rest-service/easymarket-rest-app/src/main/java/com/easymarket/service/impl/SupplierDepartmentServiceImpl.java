package com.easymarket.service.impl;

import java.util.List;

import javax.ws.rs.NotFoundException;

import com.easymarket.core.SupplierDepartment;
import com.easymarket.dao.SupplierDepartmentDAO;
import com.easymarket.service.SupplierDepartmentService;
import com.google.common.base.Optional;

//Service not working(fixing inject by dropwizard guicey(https://github.com/xvik/dropwizard-guicey)
//@Service
public class SupplierDepartmentServiceImpl implements SupplierDepartmentService {

    private SupplierDepartmentDAO dao = null;

    public SupplierDepartmentServiceImpl(SupplierDepartmentDAO dao) {
        this.dao = dao;
    }

    @Override
    public SupplierDepartment createSupplierDepartment(SupplierDepartment obj) {
        return dao.create(obj);
    }

    @Override
    public void deleteSupplierDepartment(Long id) {
        Optional<SupplierDepartment> result = dao.findById(id);
        dao.delete(result.get());

    }

    @Override
    public SupplierDepartment getSupplierDepartment(Long id) {
        return findSafely(id);
    }

    private SupplierDepartment findSafely(long id) {
        final Optional<SupplierDepartment> result = dao.findById(id);
        if (!result.isPresent()) {
            throw new NotFoundException("No such department.");
        }
        return result.get();
    }

    @Override
    public List<SupplierDepartment> listSupplierDepartment() {
        return dao.findAll();
    }

    @Override
    public List<SupplierDepartment> findDepartmentBasedOnSupplierType(String supplierType) {
        List<SupplierDepartment> result = dao.findDepartmentBasedOnSupplierType(supplierType);
        return result;
    }
}
