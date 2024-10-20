package br.com.one_community.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "br.com.one_community")
public class OneCommunityApplication {

	public static void main(String[] args) {
		SpringApplication.run(OneCommunityApplication.class, args);
	}
}
