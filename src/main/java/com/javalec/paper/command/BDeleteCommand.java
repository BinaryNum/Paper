package com.javalec.paper.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.javalec.paper.dao.CDao;

public class BDeleteCommand implements Command {

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		String bId = request.getParameter("bId");
		String bGroup = request.getParameter("bGroup");
		String bStep = request.getParameter("bStep");
		CDao dao = new CDao();
		dao.delete(bId,bGroup,bStep);
	}

}
