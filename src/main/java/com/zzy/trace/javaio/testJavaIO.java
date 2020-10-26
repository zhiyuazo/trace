package com.zzy.trace.javaio;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Serializable;
import java.text.ParseException;
import java.time.LocalDateTime;

public class testJavaIO {
	public static void main(String[] args) throws InterruptedException, ParseException, IOException {
		//---------------------------------------------------------------------
		//---------字节(Byte)流 适合处理图像，视频，二进制，音频，也能处理文本，啥都能...----------
		//---------------------------------------------------------------------
		//读Byte //没有编码问题，直接读就好....
		File file_byte  =  new File("D:\\test.txt");
		FileInputStream  fis = new FileInputStream(file_byte);
		byte[] byteall =  new byte[(int)file_byte.length()];
		fis.read(byteall);
		String input_gbk = new String(byteall,"GBK");
		String input_utf = new String(input_gbk.getBytes(),"UTF8");
		System.out.println(input_utf);
		//写Byte
		FileOutputStream fos = new FileOutputStream(new File("D:\\test_w_byte.txt"));
		fos.write(input_utf.getBytes());
		fis.close();
		fos.close();
		
		//---------------------------------------------------------------------
		//--------------字符(char)流，适合处理文本...---------------------------------------------------
		//---------------------------------------------------------------------
		//写Char,char 一般是两个byte 根据不同编码，所包含的byte不一样
		//如utf-8   ASCII码用1byte，中文用3byte表示...
		//-----FileReader/FileWriter....本质是inputStreamReader/outputStreamWriter,只是采用了系统默认编码(不能另行指定，需要inputStreamReader/outputStreamWriter)...
		File file_char = new File("D:\\test.txt");
		char[] file_charall = new char[(int) file_char.length()];
        try (FileReader fr = new FileReader(file_char)) {
        	System.out.println("FileReader encoding is : "  +  fr.getEncoding() ) ;
            fr.read(file_charall);
            StringBuffer sb = new StringBuffer();
            for (char c : file_charall) {
				sb.append( String.valueOf(c));
			}
            System.out.println(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        try (FileWriter fw = new FileWriter("D:\\test_filewrite_char.txt")) {
        	System.out.println("FileWriter encoding is : "  +  fw.getEncoding() ) ;
        	fw.write(file_charall);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        //上面读取的是乱码，因为系统设置了UTF-8编码，但是Winodws上的文件一般为GBK编码，这样FileReader/FileWriter不能指定编码的弊端出现...
		//-----InputStreamReader/OutputStreamWriter....是byte和char的桥梁，将byte流转化为char流
        char[] stream_charall = new char[(int) file_char.length()]; 
        InputStreamReader  isr = new InputStreamReader (new FileInputStream(file_char),"GBK");
    	System.out.println("InputStreamReader encoding is : "  +  isr.getEncoding() ) ;
        isr.read(stream_charall);
        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("D:\\test_streamwrite_char.txt"), "GBK");
    	System.out.println("OutputStreamWriter encoding is : "  +  osw.getEncoding() ) ;
        osw.write(stream_charall);
        osw.close();
		
		//---------------------------------------------------------------------
		//--------------缓存(buffered)流，避免字节流和字符流频繁读写磁盘IO-----------------
		//---------------------------------------------------------------------
    	File file_buffer_byte = new File("D:\\test.txt");
    	byte[]  buffer_byte  =  new byte[1024]; 
    	FileInputStream  fis4b = new FileInputStream(file_buffer_byte);
    	BufferedInputStream bis = new BufferedInputStream(fis4b); //默认读取8192byte...
    	
    	FileOutputStream  fos4b = new FileOutputStream("D:\\test_bufferwrite_byte.txt");
    	BufferedOutputStream bos = new BufferedOutputStream(fos4b); //默认读取8192byte...
    	
    	int howmany = bis.read(buffer_byte);
    	while(howmany!= -1) {
    		bos.write(buffer_byte);
    		howmany = bis.read(buffer_byte);
    	}
    	bos.close();bis.close();fos.close();fis.close();
    	
    	File file_buffer_char = new File("D:\\test.txt");
    	char[]  buffer_char  =  new char[1024]; 
    	InputStreamReader  isr4b = new InputStreamReader(new FileInputStream(file_buffer_char),"GBK");
    	BufferedReader br = new BufferedReader(isr4b); //默认读取8192byte...
    	
    	OutputStreamWriter  osw4b = new OutputStreamWriter(new FileOutputStream("D:\\test_bufferwrite_char.txt"),"GBK");
    	BufferedWriter bw = new BufferedWriter(osw4b); //默认读取8192byte...
    	
    	int howmany_char = br.read(buffer_char);
    	while(howmany_char!= -1) {
    		bw.write(buffer_char);
    		howmany_char = br.read(buffer_char);
    	}
    	bw.close();br.close();isr4b.close();osw4b.close();
        
    	
		//---------------------------------------------------------------------
		//--------------数据(data)流，-----------------
		//---------------------------------------------------------------------
        File file_data =new File("D:\\test_DataStream_rw.txt");
        try (
                FileOutputStream fos4d  = new FileOutputStream(file_data);
                DataOutputStream dos =new DataOutputStream(fos4d); //读入和写出的文件都必须由Date[]Stream类操作
        ){
        	System.out.println("Write a boolean:"); dos.writeBoolean(false);
        	System.out.println("Write a int:"); 	dos.writeInt(99);
        	System.out.println("Write a String:");	dos.writeUTF("CHINA is a great conutry");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (
                FileInputStream fis4d  = new FileInputStream(file_data);
                DataInputStream dis =new DataInputStream(fis4d);
        ){
            boolean b= dis.readBoolean();	System.out.println("读取到布尔值:"+b);
            int i = dis.readInt();		  	System.out.println("读取到整数:"+i);
            String str = dis.readUTF();		System.out.println("读取到字符串:"+str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    	
		//---------------------------------------------------------------------
		//--------------对象(Object)流，-----------------
		//---------------------------------------------------------------------
        File file_object =new File("D:\\test_ObjectStream_rw.txt");
        try (
                FileOutputStream fos4o  = new FileOutputStream(file_data);
                ObjectOutputStream oos =new ObjectOutputStream(fos4o);//为什么没有文件产生???
		){
        	Lucky lucky = new Lucky();
        	lucky.name = "firstlucky";
        	lucky.label = "luckyResult";
        	lucky.ldt   = LocalDateTime.now();
        	lucky.money = 100*10000;
        	oos.writeObject(lucky);
        }catch (Exception e) {
        	e.printStackTrace();
		}
        
        try (
                FileInputStream fis4o  = new FileInputStream(file_data);
                ObjectInputStream ois =new ObjectInputStream(fis4o);
		){
        	Lucky  res =  (Lucky)ois.readObject();
        	System.out.println(res.name);
        	System.out.println(res.label);
        	System.out.println(res.ldt);
        	System.out.println(res.money);
        	System.out.println(res.position);
        }catch (Exception e) {
        	e.printStackTrace();
		}
        
		//---------------------------------------------------------------------
		//--------------缓存输出(Print)流，[输出到文件...覆盖旧文件]-----------------
		//---------------------------------------------------------------------
        File file_print  = new File("D:\\test_PrintWriter_w.txt");
        try (
                FileWriter fw4p  = new FileWriter(file_print);
                PrintWriter pw =new PrintWriter(fw4p); //必须建立在一个基础流上,不管是字符流 还是字节流
		){
        	pw.println("hate english");
        	pw.println("I am a chinese");
        	pw.print(" YES");
        	pw.print(89.65);
        	char[] res = new char[] {'A','D','U'};
        	pw.println(res);
        	Lucky lucky = new Lucky();
        	lucky.name = "firstlucky";
        	lucky.label = "luckyResult";
        	lucky.ldt   = LocalDateTime.now();
        	lucky.money = 100*10000;
        	pw.println(lucky); //写入对象的toString()方法返回
        }catch (Exception e) {
        	e.printStackTrace();
		}
		
	}
}
class Lucky implements Serializable{
	String name ; 
	LocalDateTime  ldt ;
	int money ;
	String position;
	String label ;
}
