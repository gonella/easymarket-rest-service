package com.easymarket.resources.v1;

import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;

import java.util.List;

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
import com.easymarket.core.Advertising;
import com.easymarket.core.Supplier;
import com.easymarket.dao.AdvertisingDAO;
import com.easymarket.resources.BaseController;
import com.easymarket.service.AdvertisingService;
import com.easymarket.service.impl.AdvertisingServiceImpl;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

@Path("/" + EasyMarketVersion.V1 + "/" + EasyMarketResource.ADVERTISINGS)
@Produces(MediaType.APPLICATION_JSON)
// TODO - On going
// @RolesAllowed(EasyMarketConfiguration.ROLE_ALLOWED_ADMIN)
@Api(value = "/"+EasyMarketResource.ADVERTISINGS, description = "It provides methods for accessing a advertising resource.")
public class AdvertisingResourceV1 extends BaseController<Advertising> {

	private static final String NOTE = "The advertising provided by the supplier";

	private static final Logger LOGGER = LoggerFactory
			.getLogger(AdvertisingResourceV1.class);

	private AdvertisingService AdvertisingService;

	public AdvertisingResourceV1(AdvertisingDAO dao) {
		// WORKAROUND - until find injection mechanism for dropwizard
		setAdvertisingService(new AdvertisingServiceImpl(dao));
	}
	@ApiOperation(value = "It creates a advertising", notes = NOTE, response = Advertising.class)
	@ApiResponses(value = {
	@ApiResponse(code = 400, message = "Invalid data supplied"),
	})
	@POST
	@UnitOfWork
	public Advertising createAdvertising(Advertising obj) {
		return getAdvertisingService().createAdvertising(obj);
	}

	@ApiOperation(value = "It list all advertising available", notes = NOTE, response = List.class)
	@ApiResponses(value = {
	})
	@GET
	@UnitOfWork
	public List<Advertising> listAdvertising() {
		return getAdvertisingService().listAdvertising();
	}

	@ApiOperation(value = "It deletes a advertising based on ID", notes = NOTE)
	@ApiResponses(value = {
	@ApiResponse(code = 400, message = "Invalid data supplied"),
	@ApiResponse(code = 404, message = "Entity not found") 
	})
	@Path("/{id}")
	@DELETE
	@UnitOfWork
	public void deleteAdvertising(@PathParam("id") LongParam paramId) {
		getAdvertisingService().deleteAdvertising(paramId.get());
		;
	}

	@ApiOperation(value = "It retrieves a advertising based on ID", notes = NOTE, response = Advertising.class)
	@ApiResponses(value = {
	@ApiResponse(code = 400, message = "Invalid data supplied"),
	@ApiResponse(code = 404, message = "Entity not found") 
	})
	@Path("/{id}")
	@GET
	@UnitOfWork
	public Advertising getAdvertising(@PathParam("id") LongParam paramId) {
		return getAdvertisingService().getAdvertising(paramId.get());
	}

	public AdvertisingService getAdvertisingService() {
		return AdvertisingService;
	}

	public void setAdvertisingService(AdvertisingService AdvertisingService) {
		this.AdvertisingService = AdvertisingService;
	}

}
