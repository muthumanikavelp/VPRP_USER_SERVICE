package com.vprp.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = { "com.vprp.user", "com.vprp.captcha", "com.vprp.report"})
public class VprpUserApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(VprpUserApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(VprpUserApplication.class);
	}

}
