package com.tristan.taobao;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TaobaoPointsQueryResponse {
	long allPoints;
	long limitPoints;
	String validate;
	public long getAllPoints() {
		return allPoints;
	}
	public void setAllPoints(long allPoints) {
		this.allPoints = allPoints;
	}
	public long getLimitPoints() {
		return limitPoints;
	}
	public void setLimitPoints(long limitPoints) {
		this.limitPoints = limitPoints;
	}
	public String getValidate() {
		return validate;
	}
	public void setValidate(String validate) {
		this.validate = validate;
	}
	
	
	
}
