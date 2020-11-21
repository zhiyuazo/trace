package com.zzy.trace.log4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zzy.trace.blockQueue.DataHub;

public class test {
	public static Logger logger =  LoggerFactory.getLogger(test.class);
	public static void main(String[] args) {
		test.logger.info("???");
	}
}
