package com.javaExpress.Util;

import org.springframework.beans.BeanUtils;

import com.javaExpress.DTO.CredentialDTO;
import com.javaExpress.DTO.UserDTO;
import com.javaExpress.Entity.Credential;
import com.javaExpress.Entity.User;

public interface UserMappingHelper {

	public static User map(UserDTO dto) {

		User user = new User();
		BeanUtils.copyProperties(dto, user);

		if (dto.getCredentialdto() != null) {
			
			Credential credential = new Credential();
			
			BeanUtils.copyProperties(dto.getCredentialdto(), credential);
			user.setCredential(credential);
		}

		return user;

	}

	public static UserDTO map(User user) {
		
		if(user == null)
		{
			return null;
		}

		UserDTO dto = new UserDTO();
		BeanUtils.copyProperties(user, dto);

		if (user.getCredential() != null) {
			CredentialDTO credentialdto = new CredentialDTO();
			BeanUtils.copyProperties(user.getCredential(), credentialdto);
			dto.setCredentialdto(credentialdto);
		}

		return dto;

	}
}
