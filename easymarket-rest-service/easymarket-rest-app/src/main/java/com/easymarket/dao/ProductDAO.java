package com.easymarket.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import com.easymarket.core.Product;
import com.easymarket.core.Supplier;
import com.easymarket.util.DaoUtil;
import com.google.common.base.Optional;

import io.dropwizard.hibernate.AbstractDAO;

public class ProductDAO extends AbstractDAO<Product> {
    private static final String HQL_FIND_ALL = "com.easymarket.core.Product.findAll";
    private static final String HQL_FIND_PRODUCTS_FROM_SUPPLIERS                        = "com.easymarket.core.Product.findProductsFromSuppliers";
    private static final String HQL_FIND_PRODUCTS_FROM_SUPPLIERS_BASED_ON_DEPARTMENT    = "com.easymarket.core.Product.findProductsFromSuppliersBasedOnDepartment";

    public ProductDAO(SessionFactory factory) {
        super(factory);
    }

    public Optional<Product> findById(Long id) {
        return Optional.fromNullable(get(id));
    }

    public Product create(Product obj) {
        return persist(obj);
    }

    public List<Product> findAll() {
        return list(namedQuery(HQL_FIND_ALL));
    }

    public void delete(Product obj) {
        currentSession().delete(obj);
    }

    public List<Product> findProductsFromSuppliers(List<Supplier> listSupplier) {
        Long[] ids = DaoUtil.getIds(listSupplier);
        Query namedQuery = namedQuery(HQL_FIND_PRODUCTS_FROM_SUPPLIERS).setParameterList("ids", ids);
        List<Product> list = list(namedQuery);
        return list;
    }
    public List<Product> findProductsFromSuppliersBasedOnDepartment(Long departmentId,List<Supplier> listSupplier) {
        Long[] ids = DaoUtil.getIds(listSupplier);
        Query namedQuery = namedQuery(HQL_FIND_PRODUCTS_FROM_SUPPLIERS_BASED_ON_DEPARTMENT).setParameter("departmentId",departmentId).setParameterList("ids", ids);
        List<Product> list = list(namedQuery);
        return list;
    }
}
