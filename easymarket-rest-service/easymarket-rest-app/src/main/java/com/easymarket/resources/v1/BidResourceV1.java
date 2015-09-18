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
import com.easymarket.core.Bid;
import com.easymarket.core.Product;
import com.easymarket.core.Setting;
import com.easymarket.dao.BidDAO;
import com.easymarket.resources.BaseController;
import com.easymarket.service.BidService;
import com.easymarket.service.impl.BidServiceImpl;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

@Path("/" + EasyMarketVersion.V1 + "/" + EasyMarketResource.BIDS)
@Produces(MediaType.APPLICATION_JSON)
// TODO - On going
// @RolesAllowed(EasyMarketConfiguration.ROLE_ALLOWED_ADMIN)
@Api(value = "/"+EasyMarketResource.BIDS, description = "It provides methods for accessing a BID resource.")
public class BidResourceV1 extends BaseController<Bid> {
	private static final String NOTE = "The bid is offer done by the supplier";
	
	private static final Logger LOGGER = LoggerFactory
			.getLogger(BidResourceV1.class);

	private BidService BidService;

	public BidResourceV1(BidDAO dao) {
		// WORKAROUND - until find injection mechanism for dropwizard
		setBidService(new BidServiceImpl(dao));
	}

	@ApiOperation(value = "It creates a bid", notes = NOTE, response = Bid.class)
	@ApiResponses(value = {
	@ApiResponse(code = 400, message = "Invalid data supplied"),
	})
	@POST
	@UnitOfWork
	public Bid createBid(Bid obj) {
		return getBidService().createBid(obj);
	}

	@ApiOperation(value = "It list all bids available", notes = NOTE, response = List.class)
	@ApiResponses(value = {
	})
	@GET
	@UnitOfWork
	public List<Bid> listBid() {
		return getBidService().listBid();
	}

	@ApiOperation(value = "It deletes a bid based on ID", notes = NOTE)
	@ApiResponses(value = {
	@ApiResponse(code = 400, message = "Invalid data supplied"),
	@ApiResponse(code = 404, message = "Entity not found") 
	})
	@Path("/{id}")
	@DELETE
	@UnitOfWork
	public void deleteBid(@PathParam("id") LongParam paramId) {
		getBidService().deleteBid(paramId.get());
		;
	}

	@ApiOperation(value = "It retrieves a bid based on ID", notes = NOTE, response = Bid.class)
	@ApiResponses(value = {
	@ApiResponse(code = 400, message = "Invalid data supplied"),
	@ApiResponse(code = 404, message = "Entity not found") 
	})
	@Path("/{id}")
	@GET
	@UnitOfWork
	public Bid getBid(@PathParam("id") LongParam paramId) {
		return getBidService().getBid(paramId.get());
	}

	public BidService getBidService() {
		return BidService;
	}

	public void setBidService(BidService BidService) {
		this.BidService = BidService;
	}

}
