package com.javalec.paper.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.ui.Model;

import com.javalec.paper.dao.MemberDao;
import com.javalec.paper.dto.Member;

public class MemberLoginCommand implements Command {

   @SuppressWarnings("unchecked")
   @Override
   public void execute(Model model) {
      // TODO Auto-generated method stub
	   Map<String, Object> map = model.asMap();
	   HttpServletRequest request = (HttpServletRequest)map.get("request");
	   HttpServletResponse response = (HttpServletResponse)map.get("response");
	   String id=request.getParameter("id");
	   String password=request.getParameter("password");
   
      
      
	      MemberDao dao=new MemberDao();
	      Member vo=new Member();
	      int flag=0;
	      
	      if(password==null) {
	         System.out.println(id);
	         try {
	            vo=dao.exist(id,password);
	            System.out.println(vo.getInterested()+"!!!!!!!!");
	            if(vo.getInterested()==null) {
	                JSONObject obj=new JSONObject();
	                obj.put("idcheck", flag);
	                response.setContentType("application/x-json charset=UTF-8");
	                response.getWriter().print(obj);
	            }
	            else if(vo.getInterested()!=null) {
	               HttpSession session=request.getSession();
	               session.setAttribute("userid", id);
	               session.setAttribute("interest",vo.getInterested());
	               JSONObject obj=new JSONObject();
	                obj.put("idcheck", 1);
	                response.setContentType("application/x-json charset=UTF-8");
	                response.getWriter().print(obj);
	            }
	         } catch (Exception e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	         }
	         
	         
	      }
	      else {
	      try {
	         vo=dao.exist(id, password);
	         if(vo==null) {
	            flag=0;
	         }
	         else if(vo.getInterested()!=null) {
	            HttpSession session=request.getSession();
	            session.setAttribute("userid", id);
	            session.setAttribute("interest",vo.getInterested());
	            
	           // String a=(String) session.getAttribute("userid");
	           // String b=(String) session.getAttribute("interest");
	            
	            flag=1;
	         }
	         
	          JSONObject obj=new JSONObject();
	          obj.put("idcheck", flag);
	          response.setContentType("application/x-json charset=UTF-8");
	          response.getWriter().print(obj);
	         
	      } catch (Exception e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      }
	   }
   }
}