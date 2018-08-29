package com.javalec.paper.command;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.javalec.paper.dao.CDao;
import com.javalec.paper.dao.CCDao;
import com.javalec.paper.dto.CDto;
import com.javalec.paper.dto.CCDto;


public class BContentCommand implements Command {

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		String bId = request.getParameter("bId");
		CDao dao = new CDao();
		CDto dto = dao.contentView(bId);
		
		
		CCDao cdao = new CCDao();
		ArrayList<CCDto> cdtos = cdao.list(Integer.parseInt(bId));
		model.addAttribute("comments", cdtos);
		model.addAttribute("content_view", dto);
		
	}

}
