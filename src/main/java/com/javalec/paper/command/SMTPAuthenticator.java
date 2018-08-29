package com.javalec.paper.command;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class SMTPAuthenticator extends Authenticator{
	public SMTPAuthenticator() {
		super();
	}
	//String username="ehdrjsdlzzzz@gmail.com";//teamlklk@gmail.com 
	//String password="ehdrjs)624";				//teamlklk12
	public PasswordAuthentication getPasswordAuthentication() {
		String username="binarynum01@gmail.com";
		String password="wlstn2323";
		return new PasswordAuthentication(username, password);
	}
}
