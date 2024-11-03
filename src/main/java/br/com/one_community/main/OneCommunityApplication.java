package br.com.one_community.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication(scanBasePackages = "br.com.one_community")
@EnableJpaRepositories(basePackages = "br.com.one_community.repositories")
@EntityScan(basePackages = {
		"br.com.one_community.entities.user",
		"br.com.one_community.entities.question"
})
@EnableSpringDataWebSupport(
		pageSerializationMode = EnableSpringDataWebSupport
				.PageSerializationMode.VIA_DTO)
public class OneCommunityApplication {

	public static void main(String[] args) {
		SpringApplication.run(OneCommunityApplication.class, args);
	}
}
