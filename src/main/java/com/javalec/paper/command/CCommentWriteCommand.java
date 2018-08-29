package com.javalec.paper.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import com.javalec.paper.dao.CCDao;



public class CCommentWriteCommand implements Command {

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub

		
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		
		HttpSession session=request.getSession();//
		System.out.println("session==="+session.getAttribute("userid"));
			
		String userId = (String)session.getAttribute("userid");
		int bId = Integer.parseInt(request.getParameter("bId"));
		String contents = request.getParameter("contents");
					
					
		CCDao dao = new CCDao();
		dao.write(bId, userId, contents);
				
	}
}
