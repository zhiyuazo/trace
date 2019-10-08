package testIO;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

public class testTxt2Json {
	public static void main(String[] args) throws IOException, InterruptedException {
		
		byte[] all =null ;
		if(true){
			File f = new File("C:\\Users\\PCadmin\\Desktop\\Info\\receive_byte.txt");
			FileInputStream  fis = new FileInputStream(f);
			all = new byte[(int)f.length()];
			try {
				fis.read(all);
			} catch (IOException e) {
				e.printStackTrace();
			}
			fis.close();
		}
//		String json_rec = new String(data);
		String json_rec = new String(all);
		//*****************to SYQ-fusion-Result-object*************************
		JSONObject jfull = JSONUtil.parseObj(json_rec); 
		JSONObject j605 = jfull.getJSONObject("b605_id");
		JSONObject jtask = jfull.getJSONObject("task_id");
		JSONObject jtdid = jfull.getJSONObject("td_id");
		System.out.println(jfull);
		father syqfusionres = JSONUtil.toBean(jfull, father.class);
		
	}

}
