package com.springboot.blog.springbootblogrestapi.paylod;

import lombok.Data;

@Data
public class LoginDto {
	private String usernameOrEmail;
	private String password;

}
