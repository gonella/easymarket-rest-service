package com.easymarket.resources.v1;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.easymarket.config.EasyMarketResource;
import com.easymarket.config.EasyMarketVersion;
import com.easymarket.core.SupplierDepartment;
import com.easymarket.dao.SupplierDepartmentDAO;
import com.easymarket.resources.BaseController;
import com.easymarket.service.SupplierDepartmentService;
import com.easymarket.service.impl.SupplierDepartmentServiceImpl;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;

@Path("/" + EasyMarketVersion.V1 + "/" + EasyMarketResource.SUPPLIERDEPARTMENTS)
@Produces(MediaType.APPLICATION_JSON)
// TODO - On going
// @RolesAllowed(EasyMarketConfiguration.ROLE_ALLOWED_ADMIN)
@Api(value = "/" + EasyMarketResource.SUPPLIERDEPARTMENTS, description = "It provides methods for accessing a department resource.")
public class SupplierDepartmentResourceV1 extends BaseController<SupplierDepartment> {
    private static final String NOTE = "The departament is section where product was placed";

    private static final Logger LOGGER = LoggerFactory
            .getLogger(SupplierDepartmentResourceV1.class);

    private SupplierDepartmentService supplierDepartmentService;

    public SupplierDepartmentResourceV1(SupplierDepartmentDAO dao) {
        // WORKAROUND - until find injection mechanism for dropwizard
        setSupplierDepartmentService(new SupplierDepartmentServiceImpl(dao));
    }

    @ApiOperation(value = "It creates a supplier department", notes = NOTE, response = SupplierDepartment.class)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Invalid data supplied"),
    })
    @POST
    @UnitOfWork
    public SupplierDepartment createSupplierDepartment(SupplierDepartment obj) {
        return getSupplierDepartmentService().createSupplierDepartment(obj);
    }

    @ApiOperation(value = "It list all departments available", notes = NOTE, response = List.class)
    @ApiResponses(value = {})
    @GET
    @UnitOfWork
    public List<SupplierDepartment> listSupplierDepartment() {
        return getSupplierDepartmentService().listSupplierDepartment();
    }

    @ApiOperation(value = "It deletes a department based on ID", notes = NOTE)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Invalid data supplied"),
            @ApiResponse(code = 404, message = "Entity not found")
    })
    @Path("/{id}")
    @DELETE
    @UnitOfWork
    public void deleteSupplierDepartment(@PathParam("id") LongParam paramId) {
        getSupplierDepartmentService().deleteSupplierDepartment(paramId.get());
        ;
    }

    @ApiOperation(value = "It retrieves a department based on ID", notes = NOTE, response = SupplierDepartment.class)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Invalid data supplied"),
            @ApiResponse(code = 404, message = "Entity not found")
    })
    @Path("/{id}")
    @GET
    @UnitOfWork
    public SupplierDepartment getSupplierDepartment(@PathParam("id") LongParam paramId) {
        return getSupplierDepartmentService().getSupplierDepartment(paramId.get());
    }

    public SupplierDepartmentService getSupplierDepartmentService() {
        return supplierDepartmentService;
    }

    public void setSupplierDepartmentService(SupplierDepartmentService supplierDepartmentService) {
        this.supplierDepartmentService = supplierDepartmentService;
    }

    @ApiOperation(value = "It finds all departments related with supplier type informed", notes = NOTE, response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Invalid data supplied"),
    })
    @Path("/type")
    @GET
    @UnitOfWork
    public List<SupplierDepartment> findDepartmentBasedOnSupplierType(@QueryParam(value = "supplierType") String supplierType) throws Exception {

        List<SupplierDepartment> result = getSupplierDepartmentService().findDepartmentBasedOnSupplierType(supplierType);

        return result;
    }
}
