package br.com.mercadoapp.mercadoapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableDiscoveryClient
public class MercadoappApplication {

	public static void main(String[] args) {
		SpringApplication.run(MercadoappApplication.class, args);
	}

}
