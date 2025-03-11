package com.javaExpress.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressDTO {

	private Integer addressId;
	private String fullAddress;
	private String postalCode;
	private String city;
	@JsonProperty("user")
	private UserDTO userdto;

}
