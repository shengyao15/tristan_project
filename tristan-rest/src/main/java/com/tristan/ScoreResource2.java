package com.tristan;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.stereotype.Component;

@Path("/rest/score")
@Component
public class ScoreResource2 {

    @POST
    @Produces(MediaType.APPLICATION_XML)
    @Path("/queryChannelScore2")
    public Response queryChannelScore(Student student) {
    	
    	System.out.println("bb");
        return Response.ok("hello  " + student).build();
    }

    
}

