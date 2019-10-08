package testIO;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import cn.hutool.json.JSONUtil;

public class testFileIo {
	public static void main(String[] args) {
		String json_rec = JSONUtil.parseObj(new father()).toJSONString(4);
		byte[] data = json_rec.getBytes();
		//byte to a file
        File fb = new File("C:\\Users\\iecas\\Desktop\\28Info\\receive_byte.txt");
		try {
            FileOutputStream fos = new FileOutputStream(fb,true);
            fos.write(data);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
 
		//string to a file
		File fs = new File("C:\\Users\\iecas\\Desktop\\28Info\\receive_string.txt");
        try (FileWriter fw = new FileWriter(fs,true);PrintWriter pw = new PrintWriter(fw);) {
            pw.println(json_rec);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}

class father {
	String name = "father";
	int age = 60;
	char alias = 'M';
	boolean died = false ;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public char getAlias() {
		return alias;
	}
	public void setAlias(char alias) {
		this.alias = alias;
	}
	public boolean isDied() {
		return died;
	}
	public void setDied(boolean died) {
		this.died = died;
	}
	public long getBirthday() {
		return birthday;
	}
	public void setBirthday(long birthday) {
		this.birthday = birthday;
	}
	long birthday = 123456789;
}