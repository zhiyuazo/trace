package com.zzy.trace.csv;

import java.io.IOException;
import java.util.List;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.io.resource.NoResourceException;
import cn.hutool.core.lang.Console;
import cn.hutool.core.text.csv.CsvData;
import cn.hutool.core.text.csv.CsvReader;
import cn.hutool.core.text.csv.CsvRow;
import cn.hutool.core.text.csv.CsvUtil;
import cn.hutool.core.text.csv.CsvWriter;
import cn.hutool.core.util.CharsetUtil;

public class csvIO {
	public static void main(String[] args) throws NoResourceException, IOException {
		ClassPathResource csv_file = new ClassPathResource("users.csv");
		CsvReader csv_reader = CsvUtil.getReader();
		CsvData data = csv_reader.read(csv_file.getFile());
		List<CsvRow> csvrows = data.getRows();
		for (CsvRow row : csvrows) {
		    Console.log(row.getRawList());
		    Console.log(row.getOriginalLineNumber());
		}
		
		
//		CsvWriter csv_writer = CsvUtil.getWriter(csv_file.getFile(), CharsetUtil.CHARSET_UTF_8,true);
//		csv_writer.write(
//		    new String[] {"a1", "b1", "c1"}, 
//		    new String[] {"a2", "b2", "c2"}, 
//		    new String[] {"a3", "b3", "c3"}
//		);
		
	}
}
