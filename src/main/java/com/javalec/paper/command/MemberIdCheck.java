package com.javalec.paper.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.springframework.ui.Model;

import com.javalec.paper.dao.MemberDao;

public class MemberIdCheck implements Command{

	@SuppressWarnings("unchecked")
	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		HttpServletResponse response = (HttpServletResponse)map.get("response");
		 String id=request.getParameter("id");
		 System.out.println(id);
		 MemberDao dao=new MemberDao();
		 int flag;
		try {
			flag = dao.selectOne(id);
		
		 System.out.println(flag);
		 JSONObject obj=new JSONObject();
		obj.put("idflag", flag);
		response.setContentType("application/x-json charset=UTF-8");
		response.getWriter().print(obj);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
