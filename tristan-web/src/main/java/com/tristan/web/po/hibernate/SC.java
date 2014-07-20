package com.tristan.web.po.hibernate;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Max;

@Entity
@Table(name="SC")
public class SC {
	
	@EmbeddedId
    private SCPK scpk ;
	
	@Column
	private int grade;
	

	public SCPK getScpk() {
		return scpk;
	}

	public void setScpk(SCPK scpk) {
		this.scpk = scpk;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}


}
