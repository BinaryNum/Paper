package com.javalec.paper.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import com.javalec.paper.dao.PBasketDao;


import java.util.Map;


public class PBasketCommand implements Command {

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		HttpSession session=request.getSession();
		String userId = (String) session.getAttribute("userid");
		int pId = Integer.parseInt(request.getParameter("pId"));
		System.out.println("BID====="+pId);
		PBasketDao dao = new PBasketDao();
		dao.write(pId, userId);
	}
}
