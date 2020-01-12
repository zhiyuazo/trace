package controllerSet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import websocket.WSdata;


@SpringBootApplication
@ComponentScan(basePackages = {"controllerSet","loginAuth","websocket"})
public class App {
	  public static void main(String[] args) {
			new Thread(new WSdata()).start();
	        SpringApplication.run(App.class, args);
	  }
}
