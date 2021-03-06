package com.javalec.paper.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpSession;


import org.springframework.ui.Model;



public class MemberLogoutCommand implements Command {

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		Map<String, Object> map = model.asMap();
		  HttpServletRequest request = (HttpServletRequest)map.get("request");
		   
		HttpSession session=request.getSession();
		session.invalidate();
		
	}

}
