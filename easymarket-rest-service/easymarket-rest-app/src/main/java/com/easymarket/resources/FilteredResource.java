package com.easymarket.resources;

import com.easymarket.filter.DateRequired;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/filtered")
public class FilteredResource {

    @GET
    @DateRequired
    @Path("hello")
    public String sayHello() {
        return "hello";
    }
}
