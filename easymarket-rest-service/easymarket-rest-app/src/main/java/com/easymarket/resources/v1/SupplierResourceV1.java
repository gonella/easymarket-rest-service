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
import com.easymarket.core.Supplier;
import com.easymarket.core.param.SupplierRequest;
import com.easymarket.dao.SupplierDAO;
import com.easymarket.resources.BaseController;
import com.easymarket.service.SupplierService;
import com.easymarket.service.impl.SupplierServiceImpl;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;

@Path("/" + EasyMarketVersion.V1 + "/" + EasyMarketResource.SUPPLIERS)
@Produces(MediaType.APPLICATION_JSON)
// TODO - On going
// @RolesAllowed(EasyMarketConfiguration.ROLE_ALLOWED_ADMIN)
@Api(value = "/"+EasyMarketResource.SUPPLIERS, description = "It provides methods for accessing a supplier resource.")
public class SupplierResourceV1 extends BaseController<Supplier> {
    private static final String NOTE = "The supplier is a market/comercial place used by user to buy products.";

    private static final Logger LOGGER = LoggerFactory
            .getLogger(SupplierResourceV1.class);

    private SupplierService SupplierService;

    public SupplierResourceV1(SupplierDAO dao) {
        // WORKAROUND - until find injection mechanism for dropwizard
        setSupplierService(new SupplierServiceImpl(dao));
    }

    @ApiOperation(value = "It creates a supplier", notes = NOTE, response = Supplier.class)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Invalid data supplied"),
    })
    @POST
    @UnitOfWork
    public Supplier createSupplier(Supplier obj) {
        return getSupplierService().createSupplier(obj);
    }

    @ApiOperation(value = "It list all suppliers available", notes = NOTE, response = List.class)
    @ApiResponses(value = {
    })
    @GET
    @UnitOfWork
    public List<Supplier> listSupplier() {
        return getSupplierService().listSupplier();
    }

    @ApiOperation(value = "It deletes a supplier based on ID", notes = NOTE, response = Supplier.class)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Invalid data supplied"),
            @ApiResponse(code = 404, message = "Entity not found")
    })
    @Path("/{id}")
    @DELETE
    @UnitOfWork
    public void deleteSupplier(@PathParam("id") LongParam paramId) {
        getSupplierService().deleteSupplier(paramId.get());
        ;
    }

    @ApiOperation(value = "It retrieves a supplier based on ID", notes = NOTE, response = Supplier.class)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Invalid data supplied"),
            @ApiResponse(code = 404, message = "Entity not found")
    })
    @Path("/{id}")
    @GET
    @UnitOfWork
    public Supplier getSupplier(@PathParam("id") LongParam paramId) {
        return getSupplierService().getSupplier(paramId.get());
    }

    @ApiOperation(value = "It find all supplier close to gps coordinates informed based on limit.", notes = NOTE, response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Invalid data supplied"),
    })
    @Path("/nearby")
    @GET
    @UnitOfWork
    public List<Supplier> findSupplierByGpsCoordinate(@BeanParam SupplierRequest supplierRequest) throws Exception {

        //NearByRequest
        List<Supplier> findSupplierByGpsCoordinate = getSupplierService().findSupplierByGpsCoordinate(supplierRequest);

        return findSupplierByGpsCoordinate;
    }

    public SupplierService getSupplierService() {
        return SupplierService;
    }

    public void setSupplierService(SupplierService SupplierService) {
        this.SupplierService = SupplierService;
    }

}
