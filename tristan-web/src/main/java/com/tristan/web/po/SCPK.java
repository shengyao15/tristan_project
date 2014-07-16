package com.tristan.web.po;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class SCPK implements Serializable {

	@Column(length=10)
	String cno;
	
	@Column(length=10)
	String sno;
	
	public SCPK() {
		// TODO Auto-generated constructor stub
	}
	
	public SCPK(String sno, String cno) {
		this.sno = sno;
		this.cno = cno;
	}
}
