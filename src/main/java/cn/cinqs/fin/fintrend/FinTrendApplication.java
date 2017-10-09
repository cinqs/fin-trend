package cn.cinqs.fin.fintrend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FinTrendApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinTrendApplication.class, args);
	}
}
