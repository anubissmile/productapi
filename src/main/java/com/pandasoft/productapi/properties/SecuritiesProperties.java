package com.pandasoft.productapi.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import io.jsonwebtoken.impl.TextCodec;
import lombok.Data;

@Data
@ConfigurationProperties(prefix = "securities")
public class SecuritiesProperties {

	public final String SALT = "PandaSoftProductAPI";
	
	public final int STRENGTH = 7;
	
	public final int TOKEN_USAGE_DURATION_MIN = 60;
	
	public final String JWT_SIGNING_KEY = TextCodec.BASE64.encode("TG9yZW0gaXBzdW0gRG9sYXIgc2l0IGFtZXQ=");
			
}
