package com.easymarket.service.impl;

import java.util.List;

import javax.ws.rs.NotFoundException;

import com.easymarket.core.Product;
import com.easymarket.core.Supplier;
import com.easymarket.core.param.ProductRequest;
import com.easymarket.dao.ProductDAO;
import com.easymarket.service.ProductService;
import com.google.common.base.Optional;

//Service not working(fixing inject by dropwizard guicey(https://github.com/xvik/dropwizard-guicey)
//@Service
public class ProductServiceImpl implements ProductService {

    private ProductDAO dao = null;

    public ProductServiceImpl(ProductDAO dao) {
        this.dao = dao;
    }

    @Override
    public Product createProduct(Product obj) {
        return dao.create(obj);
    }

    @Override
    public void deleteProduct(Long id) {
        Optional<Product> result = dao.findById(id);
        dao.delete(result.get());

    }

    @Override
    public Product getProduct(Long id) {
        return findSafely(id);
    }

    private Product findSafely(long id) {
        final Optional<Product> result = dao.findById(id);
        if (!result.isPresent()) {
            throw new NotFoundException("No such product.");
        }
        return result.get();
    }

    @Override
    public List<Product> listProduct() {
        return dao.findAll();
    }

    @Override
    public List<Product> findProductsFromSuppliers(ProductRequest request,List<Supplier> listSupplier) {

        List<Product> result = dao.findProductsFromSuppliers(listSupplier);

        return result;
    }
    @Override
    public List<Product> findProductsFromSuppliersBasedOnDepartment(ProductRequest request,List<Supplier> listSupplier) {

        List<Product> result = dao.findProductsFromSuppliersBasedOnDepartment(request.getDepartmentId(),listSupplier);;

        return result;
    }
}
