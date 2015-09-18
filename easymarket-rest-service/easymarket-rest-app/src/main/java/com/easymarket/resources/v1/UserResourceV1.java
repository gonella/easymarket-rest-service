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
import com.easymarket.core.Supplier;
import com.easymarket.core.User;
import com.easymarket.dao.UserDAO;
import com.easymarket.resources.BaseController;
import com.easymarket.service.UserService;
import com.easymarket.service.impl.UserServiceImpl;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

@Path("/" + EasyMarketVersion.V1 + "/" + EasyMarketResource.USERS)
@Produces(MediaType.APPLICATION_JSON)
// TODO - On going
// @RolesAllowed(EasyMarketConfiguration.ROLE_ALLOWED_ADMIN)
@Api(value = "/"+EasyMarketResource.USERS, description = "It provides methods for accessing a user resource.")
public class UserResourceV1 extends BaseController<User> {
	
	private static final String NOTE = "This is the user model.";

	private static final Logger LOGGER = LoggerFactory
			.getLogger(UserResourceV1.class);

	private UserService UserService;

	public UserResourceV1(UserDAO dao) {
		// WORKAROUND - until find injection mechanism for dropwizard
		setUserService(new UserServiceImpl(dao));
	}

	@ApiOperation(value = "It creates a user", notes = NOTE, response = User.class)
	@ApiResponses(value = {
	@ApiResponse(code = 400, message = "Invalid data supplied"),
	})
	@POST
	@UnitOfWork
	public User createUser(User obj) {
		return getUserService().createUser(obj);
	}

	@ApiOperation(value = "It list all users available", notes = NOTE, response = List.class)
	@ApiResponses(value = {
	})
	@GET
	@UnitOfWork
	public List<User> listUser() {
		return getUserService().listUser();
	}


	@ApiOperation(value = "It deletes a user based on ID", notes = NOTE)
	@ApiResponses(value = {
	@ApiResponse(code = 400, message = "Invalid data supplied"),
	@ApiResponse(code = 404, message = "Entity not found") 
	})
	@Path("/{id}")
	@DELETE
	@UnitOfWork
	public void deleteUser(@PathParam("id") LongParam paramId) {
		getUserService().deleteUser(paramId.get());
		;
	}

	@ApiOperation(value = "It retrieves a user based on ID", notes = NOTE, response = User.class)
	@ApiResponses(value = {
	@ApiResponse(code = 400, message = "Invalid data supplied"),
	@ApiResponse(code = 404, message = "Entity not found") 
	})
	@Path("/{id}")
	@GET
	@UnitOfWork
	public User getUser(@PathParam("id") LongParam paramId) {
		return getUserService().getUser(paramId.get());
	}

	public UserService getUserService() {
		return UserService;
	}

	public void setUserService(UserService UserService) {
		this.UserService = UserService;
	}

}
