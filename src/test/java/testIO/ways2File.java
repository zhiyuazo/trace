package testIO;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import cn.hutool.json.JSONUtil;

public class ways2File {
	public static void main(String[] args) {
		byte[] data = new byte[1024];
		String json_rec = new String(data);
		//***********************3ways to File*************************
		boolean flag = false ; 
		if(flag) {
			//string to a file
			File fs1 = new File("C:\\Users\\小杭3\\Desktop\\28Info\\receive_string_beforKJSON.txt");
	        try (FileWriter fw1 = new FileWriter(fs1,true);PrintWriter pw1 = new PrintWriter(fw1);) {
	            pw1.println(json_rec);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
			//byte to a file
			json_rec = JSONUtil.parseObj(json_rec).toStringPretty();
	        File fb = new File("C:\\Users\\小杭3\\Desktop\\28Info\\receive_byte.txt");
			try {
	            FileOutputStream fos = new FileOutputStream(fb,true);
	            fos.write(data);
	            fos.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	 
			//string to a file //This Sequece is disorder,顺序错乱...
			File fs = new File("C:\\Users\\小杭3\\Desktop\\28Info\\receive_string.txt");
	        try (FileWriter fw = new FileWriter(fs,true);PrintWriter pw = new PrintWriter(fw);) {
	            pw.println(json_rec);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
			System.out.println("wirte: OK");// + new String(data));
		}
	}

}
