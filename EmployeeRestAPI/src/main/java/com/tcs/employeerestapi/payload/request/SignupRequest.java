package com.tcs.employeerestapi.payload.request;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequest {

	@NotBlank
	@Size(max = 20)
	private String username;

	@NotBlank
	@Size(max = 50)
	//@Pattern(regexp="")
	@Email
	private String email;

	@NotBlank
	@Size(min = 6, max = 40)
	private String password;

	private Set<String> roles;
	
}
