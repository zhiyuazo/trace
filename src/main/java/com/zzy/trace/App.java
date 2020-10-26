package com.zzy.trace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.zzy.trace.websocket.WSdata;


@SpringBootApplication
@ComponentScan(basePackages = {"controllerSet","loginAuth","websocket"})
public class App {
	  public static void main(String[] args) {
			new Thread(new WSdata()).start();
	        SpringApplication.run(App.class, args);
	  }
}
