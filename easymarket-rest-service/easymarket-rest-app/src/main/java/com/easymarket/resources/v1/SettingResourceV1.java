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
import com.easymarket.core.Product;
import com.easymarket.core.Setting;
import com.easymarket.dao.SettingDAO;
import com.easymarket.resources.BaseController;
import com.easymarket.service.SettingService;
import com.easymarket.service.impl.SettingServiceImpl;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

@Path("/" + EasyMarketVersion.V1 + "/" + EasyMarketResource.SETTINGS)
@Produces(MediaType.APPLICATION_JSON)
// TODO - On going
// @RolesAllowed(EasyMarketConfiguration.ROLE_ALLOWED_ADMIN)
@Api(value = "/"+EasyMarketResource.SETTINGS, description = "It provides methods for accessing a setting resource.")
public class SettingResourceV1 extends BaseController<Setting> {
	
	private static final String NOTE = "This user settings data which is responsible to store customized configuration.";

	private static final Logger LOGGER = LoggerFactory
			.getLogger(SettingResourceV1.class);

	private SettingService SettingService;

	public SettingResourceV1(SettingDAO dao) {
		// WORKAROUND - until find injection mechanism for dropwizard
		setSettingService(new SettingServiceImpl(dao));
	}
	@ApiOperation(value = "It creates a user setting", notes = NOTE, response = Setting.class)
	@ApiResponses(value = {
	@ApiResponse(code = 400, message = "Invalid data supplied"),
	})
	@POST
	@UnitOfWork
	public Setting createSetting(Setting obj) {
		return getSettingService().createSetting(obj);
	}

	@ApiOperation(value = "It list all setting available", notes = NOTE, response = List.class)
	@ApiResponses(value = {
	})
	@GET
	@UnitOfWork
	public List<Setting> listSetting() {
		return getSettingService().listSetting();
	}

	@ApiOperation(value = "It deletes a setting based on ID", notes = NOTE)
	@ApiResponses(value = {
	@ApiResponse(code = 400, message = "Invalid data supplied"),
	@ApiResponse(code = 404, message = "Entity not found") 
	})
	@Path("/{id}")
	@DELETE
	@UnitOfWork
	public void deleteSetting(@PathParam("id") LongParam paramId) {
		getSettingService().deleteSetting(paramId.get());
		;
	}

	@ApiOperation(value = "It retrieves a setting based on ID", notes = NOTE, response = Setting.class)
	@ApiResponses(value = {
	@ApiResponse(code = 400, message = "Invalid data supplied"),
	@ApiResponse(code = 404, message = "Entity not found") 
	})
	@Path("/{id}")
	@GET
	@UnitOfWork
	public Setting getSetting(@PathParam("id") LongParam paramId) {
		return getSettingService().getSetting(paramId.get());
	}

	public SettingService getSettingService() {
		return SettingService;
	}

	public void setSettingService(SettingService SettingService) {
		this.SettingService = SettingService;
	}

}
