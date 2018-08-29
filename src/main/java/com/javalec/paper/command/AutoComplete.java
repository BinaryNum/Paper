package com.javalec.paper.command;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.ui.Model;

import com.javalec.paper.dao.PaperDao;
import com.javalec.paper.dto.Paper;

public class AutoComplete implements Command {

	@SuppressWarnings("unchecked")
	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		HttpServletResponse response = (HttpServletResponse)map.get("response");
		String keyword=request.getParameter("keyword");
		JSONArray autoarr=new JSONArray();
		PaperDao dao=new PaperDao();
		List<Paper> auto = dao.select();
		for(int i=0;i<auto.size();i++) {
			JSONObject obj=new JSONObject();
			if(auto.get(i).getName().toLowerCase().startsWith(keyword)||
					auto.get(i).getName().startsWith(keyword)) {
			obj.put("auto", auto.get(i).getName());
			autoarr.add(obj);
			}
		}
		
		try {
			response.setContentType("application/x-json charset=UTF-8");
			response.getWriter().print(autoarr);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
