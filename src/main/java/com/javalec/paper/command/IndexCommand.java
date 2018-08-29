package com.javalec.paper.command;

import java.util.ArrayList;

import org.springframework.ui.Model;

import com.javalec.paper.dao.CDao;
import com.javalec.paper.dto.CDto;




public class IndexCommand implements Command {

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		
		CDao dao = new CDao();
		ArrayList<CDto> dtos = dao.list();
		model.addAttribute("list", dtos);
	}
}