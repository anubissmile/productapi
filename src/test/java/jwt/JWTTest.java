package jwt;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import org.junit.jupiter.api.Test;

import com.pandasoft.productapi.helper.AuthHelper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SigningKeyResolver;
import io.jsonwebtoken.SigningKeyResolverAdapter;
import io.jsonwebtoken.impl.TextCodec;

public class JWTTest {
	
	@Test
	public void generate() {
		String token = Jwts.builder()
				.setIssuer("Wesarut")
				.setIssuedAt(new Date())
				.setSubject("Authorization.")
				.setId(AuthHelper.bcrypt(7, new Date().toString() + "Jti Product api"))
				.claim("username", "anubissmile")
				.signWith(
					SignatureAlgorithm.HS256, 
					TextCodec.BASE64.encode("TG9yZW0gaXBzdW0gRG9sYXIgc2l0IGFtZXQ="))
				.compact();
		
		System.out.println(String.format("JWT Generate : %s", token));
		
	}
	
	public void decode() {
		
		String jwt = "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJXZXNhcnV0IiwiaWF0IjoxNTkwMjE2NDE4LCJzdWIiOiJBdXRob3JpemF0aW9uLiIsImp0aSI6IiQyYSQwNyRpWUVyM1F2STR5ckRaMS5YbGpCQVV1U0YwZGRqYWI4NWJnNmlTRS9pZ0ZIT3d5U09EcUl6cSIsInVzZXJuYW1lIjoiYW51Ymlzc21pbGUifQ.QbgKGd1UrYzleVpKEujXXXZoNv7u_m7TtdOHmtS5xYU";
		
		Jws<Claims> jws = Jwts.parser()
				.setSigningKey(TextCodec.BASE64.encode("TG9yZW0gaXBzdW0gRG9sYXIgc2l0IGFtZXQ=ddd"))
		        .parseClaimsJws(jwt);
		
		System.out.println(jws);
		
	}
	

}
