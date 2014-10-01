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
public class ScoreResource3 {

    @POST
    @Produces(MediaType.APPLICATION_XML)
    @Path("/queryChannelScore3")
    public Response queryChannelScore(Student student) {
    	
    	System.out.println("cc " + student.getCountry());
    	
    	StudentResponse response = new StudentResponse();
    	response.setScore("98");
    	response.setStatus("1");
    	
    	
        return Response.ok().entity(response).build();
    }

    
}

