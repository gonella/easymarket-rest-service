package com.easymarket.service;

import java.util.List;

import com.easymarket.core.Product;
import com.easymarket.core.Supplier;
import com.easymarket.core.param.ProductRequest;

public interface ProductService {

    public Product createProduct(Product product);
    public void deleteProduct(Long id);
    public Product getProduct(Long id);
    public List<Product> listProduct();
    public List<Product> findProductsFromSuppliers(ProductRequest request, List<Supplier> listSupplier);
    public List<Product> findProductsFromSuppliersBasedOnDepartment(ProductRequest request, List<Supplier> listSupplier);
}
