package com.javalec.paper.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.javalec.paper.dao.CDao;
import com.javalec.paper.dto.CDto;

public class BReplyViewCommand implements Command {

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		String bId = request.getParameter("bId");
		CDao dao = new CDao();
		CDto dto = dao.reply_view(bId);
		
		model.addAttribute("reply_view", dto);
		
	}

}
