package com.easymarket.resources.v1;

import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.easymarket.config.EasyMarketResource;
import com.easymarket.config.EasyMarketVersion;
import com.easymarket.core.Product;
import com.easymarket.core.Supplier;
import com.easymarket.core.param.ProductRequest;
import com.easymarket.dao.ProductDAO;
import com.easymarket.dao.SupplierDAO;
import com.easymarket.resources.BaseController;
import com.easymarket.service.ProductService;
import com.easymarket.service.SupplierService;
import com.easymarket.service.impl.ProductServiceImpl;
import com.easymarket.service.impl.SupplierServiceImpl;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;

@Path("/" + EasyMarketVersion.V1 + "/" + EasyMarketResource.PRODUCTS)
@Produces(MediaType.APPLICATION_JSON)
// TODO - On going
// @RolesAllowed(EasyMarketConfiguration.ROLE_ALLOWED_ADMIN)
@Api(value = "/"+EasyMarketResource.PRODUCTS, description = "It provides methods for accessing a product resource.")
public class ProductResourceV1 extends BaseController<Product> {
    private static final String NOTE = "The product is a item which may buy by the user";

    private static final Logger LOGGER = LoggerFactory
            .getLogger(ProductResourceV1.class);

    private ProductService productService;
    private SupplierService supplierService;

    public ProductResourceV1(ProductDAO productDao, SupplierDAO supplierDao) {
        // WORKAROUND - until find injection mechanism for dropwizard
        setProductService(new ProductServiceImpl(productDao));
        setSupplierService(new SupplierServiceImpl(supplierDao));
    }

    @ApiOperation(value = "It creates a product", notes = NOTE, response = Product.class)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Invalid data supplied"),
    })
    @POST
    @UnitOfWork
    public Product createProduct(Product obj) {
        return getProductService().createProduct(obj);
    }

    @ApiOperation(value = "It list all products available", notes = NOTE, response = List.class)
    @ApiResponses(value = {
    })
    @GET
    @UnitOfWork
    public List<Product> listProduct() {
        return getProductService().listProduct();
    }

    @ApiOperation(value = "It deletes a product based on ID", notes = NOTE)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Invalid data supplied"),
            @ApiResponse(code = 404, message = "Entity not found")
    })
    @Path("/{id}")
    @DELETE
    @UnitOfWork
    public void deleteProduct(@PathParam("id") LongParam paramId) {
        getProductService().deleteProduct(paramId.get());
        ;
    }

    @ApiOperation(value = "It retrieves a product based on ID", notes = NOTE, response = Product.class)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Invalid data supplied"),
            @ApiResponse(code = 404, message = "Entity not found")
    })
    @Path("/{id}")
    @GET
    @UnitOfWork
    public Product getProduct(@PathParam("id") LongParam paramId) {
        return getProductService().getProduct(paramId.get());
    }

    public ProductService getProductService() {
        return productService;
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @ApiOperation(value = "It find all products near by the customer.", notes = NOTE, response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Invalid data supplied"),
    })
    @Path("/nearby")
    @GET
    @UnitOfWork
    public List<Product> findProductFromSupplierNearBy(@BeanParam ProductRequest request) throws Exception {

        List<Supplier> supplierNearByCustomer = getSupplierService().findSupplierByGpsCoordinate(request);

        List<Product> findSupplierByGpsCoordinate = getProductService().findProductsFromSuppliers(request,supplierNearByCustomer);

        return findSupplierByGpsCoordinate;
    }

    public SupplierService getSupplierService() {
        return supplierService;
    }

    public void setSupplierService(SupplierService supplierService) {
        this.supplierService = supplierService;
    }
}
