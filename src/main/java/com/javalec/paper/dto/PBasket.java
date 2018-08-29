package com.javalec.paper.dto;

public class PBasket {
	int num;
	int pId;
	String userId;
	public PBasket() {}
	public PBasket(int num, int pId, String userId) {
		this.num=num;
		this.pId=pId;
		this.userId=userId;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getpId() {
		return pId;
	}
	public void setpId(int pId) {
		this.pId = pId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}
