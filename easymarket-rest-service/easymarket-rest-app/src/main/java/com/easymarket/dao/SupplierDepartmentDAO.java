package com.easymarket.dao;

import java.util.List;

import org.hibernate.SessionFactory;

import com.easymarket.core.SupplierDepartment;
import com.google.common.base.Optional;

import io.dropwizard.hibernate.AbstractDAO;

public class SupplierDepartmentDAO extends AbstractDAO<SupplierDepartment> {
    private static final String HQL_FIND_ALL = "com.easymarket.core.SupplierDepartment.findAll";
    private static final String HQL_FIND_DEPARTMENT_BASED_ON_SUPPIER_TYPE = "com.easymarket.core.SupplierDepartment.findDepartmentBasedOnSupplierType";

    public SupplierDepartmentDAO(SessionFactory factory) {
        super(factory);
    }

    public Optional<SupplierDepartment> findById(Long id) {
        return Optional.fromNullable(get(id));
    }

    public SupplierDepartment create(SupplierDepartment obj) {
        return persist(obj);
    }

    public List<SupplierDepartment> findAll() {
        return list(namedQuery(HQL_FIND_ALL));
    }

    public void delete(SupplierDepartment obj) {
        currentSession().delete(obj);
    }

    public List<SupplierDepartment> findDepartmentBasedOnSupplierType(String supplierType) {
        return list(namedQuery(HQL_FIND_DEPARTMENT_BASED_ON_SUPPIER_TYPE).setParameter("supplierType", supplierType));
    }
}
