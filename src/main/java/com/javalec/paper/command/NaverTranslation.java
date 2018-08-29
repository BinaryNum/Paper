package com.javalec.paper.command;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.ui.Model;





public class NaverTranslation implements Command {

		@SuppressWarnings("unchecked")
		@Override
		public void execute(Model model) {
			// TODO Auto-generated method stub
			Map<String, Object> map = model.asMap();
			HttpServletRequest request = (HttpServletRequest)map.get("request");
			HttpServletResponse response = (HttpServletResponse)map.get("response");
			final String clientId = "tRTs2Dzxf1rnuCEYW2Ke";  //애플리케이션 클라이언트 아이디값";
			final String clientSecret = "dtwGxls7V9";    //애플리케이션 클라이언트 시크릿값";  
			String lan_1 = request.getParameter("lang1");
			String lan_2 = request.getParameter("lang2");
			String sentence=request.getParameter("sentence");
			System.out.println("sen======"+sentence);
			
			
										// lancode 테이블에 ko, en 등 필드 추가 
				
				
				try {
		            String text = URLEncoder.encode(sentence, "UTF-8");
		            String apiURL = "https://openapi.naver.com/v1/papago/n2mt";
		            URL url = new URL(apiURL);
		            HttpURLConnection con = (HttpURLConnection)url.openConnection();
		            con.setRequestMethod("POST");
		            con.setRequestProperty("X-Naver-Client-Id", clientId);
		            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
		           
		            // post request
		            String postParams = "source="+lan_1+"&target="+lan_2+"&text=" + text;
		            con.setDoOutput(true);
		            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		            wr.writeBytes(postParams);
		            wr.flush();
		            wr.close();
		           
		            BufferedReader br;
		            
		            if(con.getResponseCode() == 200) { // 정상 호출
		            	System.out.println("정상~");
		                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
		            } else {  // 에러 발생
		            	System.out.println("에러~");
		            	return;
		                //br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
		            }
		            String inputLine;
		            StringBuffer responsed = new StringBuffer();
		            while ((inputLine = br.readLine()) != null) {
		                responsed.append(inputLine);
		            }
		            br.close();
		            
		           
		            JSONParser parser=new JSONParser();
			          Object obj=parser.parse(responsed.toString());
			          System.out.println(obj);
			          JSONObject jobj=(JSONObject) parser.parse(obj.toString());
			          jobj=(JSONObject) jobj.get("message");
			          jobj=(JSONObject) jobj.get("result");
			         
			         String translated=(String) jobj.get("translatedText");
			         if(translated.equals(null)) {
			        	 translated="입력된 값이 없습니다.";
			         }
			         JSONObject trans=new JSONObject();
			         trans.put("trans", translated);
			 		response.setContentType("application/x-json charset=UTF-8");
			 		response.getWriter().print(trans);
			  
			         
		        } catch (Exception e) {
		            System.out.println(e);
		        }
			
			
		}
		

}
