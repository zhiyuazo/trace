package javaio;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.Clock;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

public class WebImportExport {
    public boolean upload(HttpServletRequest req, @RequestParam("tr_upload") MultipartFile MultiFile) throws IllegalStateException, IOException {
		String uploadFilePath = "D:\\ProcessServerSide\\UserImport";
		java.time.LocalDateTime ldt = java.time.LocalDateTime.now();
		String ldt_str  = ldt.toString();
		File dstFile =  new File(uploadFilePath + "\\"+ldt_str +MultiFile.getOriginalFilename());
		if(! dstFile.getParentFile().exists()) {
			dstFile.getParentFile().mkdirs();
		}
    	try {
			MultiFile.transferTo(dstFile);
			String originalFileName = MultiFile.getOriginalFilename();//都将进行文件保存....保存完成后再进行读取
			//仅包含规则和rule(不区分大小写)的文件进行数据库操作
    	}catch (Exception e) {
			return false;
		}
		return false;
    }   
    
    public void export(HttpServletRequest req,HttpServletResponse res ) throws IOException, IllegalArgumentException, IllegalAccessException {
    	    String request_file  = req.getParameter("file");
    		String exportFilePath = "D:\\ProcessServerSide\\ServerGenerate";
    		String targetFileName = "export_file_viaJAVA.csv";
    		File TargetFile = new File(exportFilePath + "//"  +targetFileName);
    		//准备文件.....
			res.setContentType("application/force-download");
			res.addHeader("Content-Disposition", "attachment;fileName=" + targetFileName);
			//写入Tatget文件
			try (FileInputStream fis = new FileInputStream(TargetFile);
				 BufferedInputStream bis = new BufferedInputStream(fis);
				 OutputStream outputStream = res.getOutputStream()) {
    				
				byte[] buffer4target = new byte[1024];
    			int i = bis.read(buffer4target);
    			while(i!=-1) {
    				outputStream.write(buffer4target,0,i);
    				i =  bis.read(buffer4target);
    			}
			}catch(Exception e) {
				e.printStackTrace();
			}
    	
		}
}
