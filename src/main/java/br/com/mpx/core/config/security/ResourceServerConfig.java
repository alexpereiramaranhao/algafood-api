package br.com.mpx.core.config.security;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

public class ResourceServerConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.anyRequest()
			.authenticated()
			.and()
			.oauth2ResourceServer().jwt();				
	}
	
	@Bean
	public JwtDecoder jwtDecorer() {
	  
	  SecretKey secret = new SecretKeySpec("hrljebfasnfb23rlkjfansdjfnasdmnfjahlkjhewhjfasdlkjfhjh2k3jhkwjhlakjfha".getBytes(), "HmacSHA256");
	  
	  return NimbusJwtDecoder.withSecretKey(secret).build();
	}
	
	

}
