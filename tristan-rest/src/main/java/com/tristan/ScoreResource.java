package com.tristan;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;

@Path("/rest/score")
@Component
public class ScoreResource {

    @GET
    @Produces("text/plain;charset=UTF-8")
    @Path("/queryChannelScore/{orderNum}")
    public Response queryChannelScore(@PathParam("orderNum") String orderNum) {
    	
    	System.out.println("aa");
        return Response.ok().entity("hello:" + orderNum).build();
    	
    }
    
   

}
