package com.tristan.taobao;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TaobaoPointsRedeemRequest {
	String userID;
	String type;
	long redeemPoints;
	String validate;


	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public long getRedeemPoints() {
		return redeemPoints;
	}

	public void setRedeemPoints(long redeemPoints) {
		this.redeemPoints = redeemPoints;
	}

	public String getValidate() {
		return validate;
	}

	public void setValidate(String validate) {
		this.validate = validate;
	}

}
