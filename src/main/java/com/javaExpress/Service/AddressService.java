package com.javaExpress.Service;

import java.util.List;

import com.javaExpress.DTO.AddressDTO;

public interface AddressService {
	
	public List<AddressDTO> saveAddress(Integer userId,List<AddressDTO>dtos);

	public List<AddressDTO> AllAddressByUsingUserID(Integer userId);

	public AddressDTO updateAddress(Integer userId, Integer addressId, AddressDTO dtos);

	public String deleteAddressByUsingAddressID(Integer userId, Integer addressId);

}
