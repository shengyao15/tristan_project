package com.tristan.taobao;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TaobaoPointsRedeemResponse {
	String status;
	String errMsg;
	String validate;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	public String getValidate() {
		return validate;
	}
	public void setValidate(String validate) {
		this.validate = validate;
	}


}
