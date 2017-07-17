package com.mediatheque.model.impl;

import com.api.allocine.model.IResult;

public class Result implements IResult{

	private String type;
	private int nb;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getNb() {
		return nb;
	}
	public void setNb(int nb) {
		this.nb = nb;
	}
	
	@Override
	public String toString() {
		return "Result [type=" + type + ", nb=" + nb + "]";
	}
	
}
