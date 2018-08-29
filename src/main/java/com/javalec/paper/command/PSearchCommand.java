package com.javalec.paper.command;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.javalec.paper.dao.PaperDao;
import com.javalec.paper.dto.Paper;

public class PSearchCommand implements Command {

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		String search = request.getParameter("search");
		PaperDao dao = new PaperDao();
		ArrayList<Paper> dtos = dao.search(search);
		model.addAttribute("paper", dtos);
	}
}