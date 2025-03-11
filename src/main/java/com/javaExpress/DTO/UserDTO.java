package com.javaExpress.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

	private Integer userId;
	@Size(min = 5,max = 15,message = "Username should be greater than 5 character")
	@NotNull
	@NotEmpty
	private String userName;
	private String firstName;
	private String lastName;
	@Email(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")
	private String email;
	private String phone;

	@JsonProperty("credential")
	@JsonInclude(value = Include.NON_NULL)
	private CredentialDTO credentialdto;

	
}
