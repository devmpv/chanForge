package com.devmpv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * ChanForge application
 * 
 * @author pmuravyov
 */
@SpringBootApplication
@EnableWebMvc
@EnableTransactionManagement
public class ChanForge {

	public static void main(String[] args) {
		SpringApplication.run(ChanForge.class, args);
	}
}
