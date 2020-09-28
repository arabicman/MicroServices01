package eureka.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding; 
import org.springframework.cloud.stream.annotation.StreamListener; 
import org.springframework.cloud.stream.messaging.Sink;

@EnableBinding(Sink.class)
@SpringBootApplication
public class DemoEurekaTollIntakeApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoEurekaTollIntakeApplication.class, args);
	}
//	@StreamListener(Sink.INPUT) 
//	public void log(String msg) { 
//		System.out.println(msg);
//	}
	@StreamListener(target = Sink.INPUT, condition = "headers['speed'] > 40")
	public void logFast(String msg) {
		System.out.println("FAST - " + msg); 
	}
	@StreamListener(target = Sink.INPUT, condition = "headers['speed'] <= 40")
	public void logSlow(String msg) {
		System.out.println("SLOW - " + msg); 
	}

}
