package com.javalec.paper.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.javalec.paper.dao.PaperDao;
import com.javalec.paper.dto.Paper;



public class PContentCommand implements Command {

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		String pId = request.getParameter("pId");
		PaperDao dao = new PaperDao();
		Paper dto = dao.contentView(pId);
		model.addAttribute("content_view", dto);
		
	}

}
