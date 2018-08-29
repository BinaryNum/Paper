package com.javalec.paper.command;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;


public class Download implements Command {

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		HttpServletResponse response = (HttpServletResponse)map.get("response");
		String filename = request.getParameter("download");
try {
        filename = encodeFileNameForDownload(filename);

        response.setContentType("application/octet-stream;charset=euc-kr");
        
        OutputStream os = response.getOutputStream();
        
        // context 루트에 있는 img폴더의 경로를 가져옴.
        String path = request.getRealPath("resources\\download\\country");

        File file = new File(path,filename);
       
         // 헤더에 파일이름 용량을 설정
        response.setHeader("Content-Disposition","attachment;filename="+file.getName()+";");
        response.setHeader("Content-Length",""+file.length());

        FileInputStream fis = new FileInputStream(file);

        int readCount = 0;
        byte[] buf = new byte[1024];

        while((readCount = fis.read(buf)) !=-1){
             os.write(buf,0,readCount);
        }
            fis.close();
            os.close();
}catch(Exception e) {e.printStackTrace();}
	}
	public String encodeFileNameForDownload(String str) throws UnsupportedEncodingException{ 

		return new String(str.getBytes("EUC-KR"),"ISO8859_1");

		}
}