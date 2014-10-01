package com.tristan.taobao;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.stereotype.Component;


@Path("/rest/taobao")
@Component("taobaoResource")
public class TaobaoResource {

	static String partnerId = "1034991800";
	
	public static void main(String[] args) {
		System.out.println(convertMD5("abc"));
		System.out.println(convertMD5("abc"+"1"+4000));
	}
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/queryPoints/{userID}/{validate}")
	public Response queryPoints(@PathParam("userID") String userID,	@PathParam("validate") String validate) {

		String md5Check = convertMD5(userID);
		
		if(validate!=null && !validate.equals(md5Check)){
			return Response.ok().entity("MD5 验证失败").build();
		}
		
		TaobaoPointsQueryResponse response = new TaobaoPointsQueryResponse();
		long allPoints = 50000;
		long limitPoints = 4000;
		response.setAllPoints(allPoints);
		response.setLimitPoints(limitPoints);
		response.setValidate(convertMD5(allPoints+""+limitPoints));
		
		return Response.ok().entity(response).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/redeemPoints/{userID}/{type}/{redeemPoints}/{validate}")
	public Response redeemPoints(@PathParam("userID") String userID, @PathParam("type") String type, @PathParam("redeemPoints") String redeemPoints, @PathParam("validate") String validate) {

		String md5Check = convertMD5(userID+type+redeemPoints);
		
		if(validate!=null && !validate.equals(md5Check)){
			return Response.ok().entity("MD5 验证失败").build();
		}
		
		TaobaoPointsRedeemResponse response = new TaobaoPointsRedeemResponse();
		String status = "2";
		String errMsg = "this is error message";
		response.setStatus(status);
		response.setErrMsg(errMsg);
		response.setValidate(convertMD5(status));
		return Response.ok().entity(response).build();
	}
	
	
	public static String convertMD5(String key){
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			md.update((key+partnerId).getBytes());
			String md5Check = new BigInteger(1, md.digest()).toString(16);
			return md5Check;
		}catch(Exception e){
			return "";
		}
	}
}
