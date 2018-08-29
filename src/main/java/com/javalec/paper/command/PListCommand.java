package com.javalec.paper.command;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.javalec.paper.dao.PaperDao;
import com.javalec.paper.dto.Paper;

public class PListCommand implements Command {

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		PaperDao dao = new PaperDao();
		String country = request.getParameter("country");
		if(country == null) {country="Domestic";}
		String field = request.getParameter("field");
		if(field == null) {field="Economy";}
		ArrayList<Paper> dtos = dao.list(country, field);
		model.addAttribute("paper", dtos);
	}
}