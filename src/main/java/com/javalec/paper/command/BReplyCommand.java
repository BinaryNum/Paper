package com.javalec.paper.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import com.javalec.paper.dao.CDao;

public class BReplyCommand implements Command {

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		HttpSession session=request.getSession();
		String bId = request.getParameter("bId");
		String bName = (String) session.getAttribute("userid");
		String bTitle = request.getParameter("bTitle");
		String bContent = request.getParameter("bContent");
		String bGroup = request.getParameter("bGroup");
		String bStep = request.getParameter("bStep");
		String bIndent = request.getParameter("bIndent");
		
		CDao dao = new CDao();
		dao.reply(bId, bName, bTitle, bContent, bGroup, bStep, bIndent);
	}

}
