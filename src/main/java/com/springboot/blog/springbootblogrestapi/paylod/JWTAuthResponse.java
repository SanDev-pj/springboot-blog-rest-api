package com.springboot.blog.springbootblogrestapi.paylod;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JWTAuthResponse {
	private String accessToken;
	private String tokenType = "Bearer";
	public JWTAuthResponse(String accessToken) {
		super();
		this.accessToken = accessToken;
	}
	
	

}
