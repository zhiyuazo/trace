package javaio;

import java.util.List;
import java.util.Map;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;

public class ReadExcel {
	public static void main(String[] args) {
		ExcelReader reader = ExcelUtil.getReader("C:\\Users\\Wing\\Desktop\\1212.xls");
		
		//List读取
		List<List<Object>> readAll_list = reader.read();
		for (List<Object> row :readAll_list ) {
			System.out.println(row.toString());
//			for (Object colum : row) {
//				System.out.println(colum.toString());
//			}
		}
		//Map读取
		List<Map<String,Object>> readAll_map = reader.readAll();
		for (Map<String,Object> row :readAll_map ) {
			System.out.println(row.toString());
//			for (Object colum : row) {
//				System.out.println(colum.toString());
//			}
		}
		
	}

}
