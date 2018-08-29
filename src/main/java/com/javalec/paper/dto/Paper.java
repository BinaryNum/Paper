package com.javalec.paper.dto;

import java.sql.Timestamp;

public class Paper {
protected String name;
protected String image;
protected String download;
protected String field;
protected String author;
protected String institution;
Timestamp pDate;
protected int pId;

public Timestamp getpDate() {
	return pDate;
}


public void setpDate(Timestamp pDate) {
	this.pDate = pDate;
}


public int getpId() {
	return pId;
}


public void setpId(int pId) {
	this.pId = pId;
}
protected String country;

public Paper() {
	
}


public String getAuthor() {
	return author;
}


public void setAuthor(String author) {
	this.author = author;
}


public String getInstitution() {
	return institution;
}


public void setInstitution(String institution) {
	this.institution = institution;
}


public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getImage() {
	return image;
}
public void setImage(String image) {
	this.image = image;
}
public String getDownload() {
	return download;
}
public void setDownload(String download) {
	this.download = download;
}
public String getField() {
	return field;
}
public void setField(String field) {
	this.field = field;
}

public String getCountry() {
	return country;
}
public void setCountry(String country) {
	this.country = country;
}
}
