package br.com.one_community.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "br.com.one_community")
@EnableJpaRepositories(basePackages = "br.com.one_community.repositories")
@EntityScan(basePackages = "br.com.one_community.entities.user")  // Pacote onde as entidades estão localizadas
public class OneCommunityApplication {

	public static void main(String[] args) {
		SpringApplication.run(OneCommunityApplication.class, args);
	}
}
