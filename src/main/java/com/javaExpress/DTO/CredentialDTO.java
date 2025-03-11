package com.javaExpress.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.javaExpress.Enum.RoleBasedAuthority;

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
public class CredentialDTO {
	
	private Integer credentialId;

	private String userName;

	private String password;

	private RoleBasedAuthority roleBasedAuthority;

	private Boolean isEnabled;
	private Boolean isAccountNotExpired;
	private Boolean isAccountNonLocked;
	private Boolean isCredentialNonExpired;

	@JsonProperty("user")
	private UserDTO userDTO;
}
