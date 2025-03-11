package com.javaExpress.Util;

import org.springframework.beans.BeanUtils;

import com.javaExpress.DTO.AddressDTO;
import com.javaExpress.DTO.UserDTO;
import com.javaExpress.Entity.Address;

public interface AddreesMappingHelper {

	public static Address map(AddressDTO dto) {

		Address address = new Address();
		BeanUtils.copyProperties(dto, address);

		return address;

	}

	public static AddressDTO map(Address address) {

		AddressDTO addressDTO = new AddressDTO();
		BeanUtils.copyProperties(address, addressDTO);

		if (address.getUser() != null) {
			UserDTO userDTO = new UserDTO();
			BeanUtils.copyProperties(address.getUser(), userDTO);
			addressDTO.setUserdto(userDTO);
		}

		return addressDTO;

	}
}
