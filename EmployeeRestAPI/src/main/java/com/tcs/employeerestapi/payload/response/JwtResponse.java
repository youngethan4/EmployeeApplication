package com.tcs.employeerestapi.payload.response;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private long id;
	private String email;
	private String username;
	private List<String> roles;
	
	public JwtResponse(String token, long id, String email, String username, List<String> roles) {
		super();
		this.token = token;
		this.id = id;
		this.email = email;
		this.username = username;
		this.roles = roles;
	}

}
