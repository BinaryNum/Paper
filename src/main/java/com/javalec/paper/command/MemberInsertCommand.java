package com.javalec.paper.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.javalec.paper.dao.MemberDao;

public class MemberInsertCommand implements Command {

	@Override
	public void execute(Model model) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		// TODO Auto-generated method stub
		String name=request.getParameter("name");
		String email=request.getParameter("email");
		String password=request.getParameter("pw1");
		String id=request.getParameter("id");
		String interested=request.getParameter("interested");
		String phonenumber=request.getParameter("phonenumber");
		String gender=request.getParameter("gender");
	
		MemberDao dao=new MemberDao();
		try {
			dao.insert(name, password, email, id, interested, phonenumber, gender);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

}
