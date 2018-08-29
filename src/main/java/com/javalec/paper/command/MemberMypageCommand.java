package com.javalec.paper.command;


import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import com.javalec.paper.dao.PBasketDao;
import com.javalec.paper.dao.PaperDao;
import com.javalec.paper.dto.PID;
import com.javalec.paper.dto.Paper;


public class MemberMypageCommand implements Command {

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		HttpSession session=request.getSession();
		String userId = (String) session.getAttribute("userid");
		PaperDao dao = new PaperDao();
		PBasketDao basket = new PBasketDao();
		
		ArrayList<PID> basketVo = basket.list(userId);
		ArrayList<Paper> basketDtos = dao.basket(basketVo);
		
		model.addAttribute("basket", basketDtos);
		
	}
}