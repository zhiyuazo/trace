package controllerSet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.footprint.controllerSet","loginAuth"})
public class App {
	  public static void main(String[] args) {
	        SpringApplication.run(App.class, args);
	  }
}
