package com.tristan;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;

@Path("score")
@Component
public class ScoreResource {

    @POST
    @Produces(MediaType.APPLICATION_XML)
    @Path("/queryChannelScore")
    public Response queryChannelScore(String orderNum) {
    	
    	System.out.println("aa");
        return Response.ok("hello  " + orderNum).build();
    	
    }
    
   

}
