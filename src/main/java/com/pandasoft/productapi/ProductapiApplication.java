package com.pandasoft.productapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.pandasoft.productapi.properties.SecuritiesProperties;

@SpringBootApplication
@EnableConfigurationProperties({
	SecuritiesProperties.class
})
public class ProductapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductapiApplication.class, args);
	}

}
