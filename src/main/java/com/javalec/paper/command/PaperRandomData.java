package com.javalec.paper.command;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.ui.Model;

import com.javalec.paper.dao.PaperDao;
import com.javalec.paper.dto.Paper;

public class PaperRandomData implements Command{

	@SuppressWarnings("unchecked")
	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		HttpServletResponse response = (HttpServletResponse)map.get("response");

		HttpSession session=request.getSession();
		String interest=(String) session.getAttribute("interest");
		//interest = "IT" ; //
		PaperDao dao=new PaperDao();

		ArrayList<Paper> list=new ArrayList<Paper>();
		list=dao.selectrandom(interest);
		
		int index;
		int duplication[] = new int[6];
		int count=0;
		double randomValue=Math.random();
		
		try {
		JSONArray paperarray=new JSONArray();
		ArrayList<Integer> ranNumber = new ArrayList<Integer>();
		for(int i=0; i <list.size();i++){
		ranNumber.add(i);
		}
		
		Collections.shuffle(ranNumber);

		
	
		for(int i=0;i<3;i++) {
			JSONObject obj=new JSONObject();
			
			obj.put("name", list.get(ranNumber.get(i)).getName());
			obj.put("image", list.get(ranNumber.get(i)).getImage());
			obj.put("pId", list.get(ranNumber.get(i)).getpId());
			paperarray.add(obj);
			//System.out.println(paperarray.get(i));
		}
		response.setContentType("application/x-json charset=UTF-8");

		response.getWriter().print(paperarray);
	}catch(Exception e) {
		e.printStackTrace();
	}
	}

}
