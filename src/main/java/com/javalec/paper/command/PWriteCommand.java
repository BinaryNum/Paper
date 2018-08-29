package com.javalec.paper.command;

import javax.servlet.http.HttpServletRequest;


import org.springframework.ui.Model;


import com.javalec.paper.dao.PaperDao;


import java.util.Enumeration;
import java.util.Map;

import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.MultipartRequest;

public class PWriteCommand implements Command {

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub

		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		//파일업로드
		String imagePath = request.getRealPath("img");
		String downloadPath = request.getRealPath("resources\\download\\country");
				int size = 1024 * 1024 * 10; //10M
				String imageFile = "";
				String imageOriFile = "";
				String downloadFile = "";
				String downloadOriFile = "";
				int i = 0;
				try{
					MultipartRequest multi = new MultipartRequest(request, downloadPath, size, "UTF-8", new DefaultFileRenamePolicy());
					
					Enumeration files = multi.getFileNames();
					while(files.hasMoreElements()) {
						if(i ==0) {
							String str = (String)files.nextElement();
							downloadFile = multi.getFilesystemName(str);
							downloadOriFile = multi.getOriginalFileName(str);
						i=1;
						}
						else {
							
							String str = (String)files.nextElement();
							imageFile = multi.getFilesystemName(str);
							imageOriFile = multi.getOriginalFileName(str);
						}
					}
					
					String name = multi.getParameter("name");
					String image = imageFile;
					String download = downloadFile;
					String field = multi.getParameter("field");
					String country = multi.getParameter("country");
					String institution = multi.getParameter("institution");
					String author = multi.getParameter("author");
					
					PaperDao dao = new PaperDao();
					dao.write(name, image, download, field, country, institution,author);
				} catch (Exception e) {
					e.printStackTrace();
				}
	}
}
