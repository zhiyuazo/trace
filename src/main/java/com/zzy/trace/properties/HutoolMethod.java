package com.zzy.trace.properties;

import java.io.IOException;
import java.util.Properties;

import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.io.resource.NoResourceException;
import cn.hutool.core.lang.Console;

public class HutoolMethod {
	public static void main(String[] args) throws NoResourceException, IOException {
		ClassPathResource resource = new ClassPathResource("application.properties");
		Properties properties = new Properties();
		properties.load(resource.getStream());
		Console.log("Properties: {}", properties);
		
	}
}
