package com.javaExpress.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.transform.ToListResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaExpress.DTO.AddressDTO;
import com.javaExpress.Entity.Address;
import com.javaExpress.Entity.User;
import com.javaExpress.ExceptionHandling.UserNotFoundException;
import com.javaExpress.Repository.AddressRepository;
import com.javaExpress.Repository.UserRepository;
import com.javaExpress.Util.AddreesMappingHelper;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public List<AddressDTO> saveAddress(Integer userId, List<AddressDTO> dtos) {

		User user = userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundException("User Not Found Exception"));
		List<AddressDTO> listDTO = new ArrayList<>();
		Address save = null;
		for (AddressDTO add : dtos) {
			Address address = AddreesMappingHelper.map(add);
			address.setUser(user);
			save = addressRepository.save(address);
			AddressDTO addressDTO = AddreesMappingHelper.map(save);
			listDTO.add(addressDTO);
		}

		return listDTO;
	}

	@Override
	public List<AddressDTO> AllAddressByUsingUserID(Integer userId) {

		User user = userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundException("User Not Found Exception"));

		List<AddressDTO> collect = addressRepository.findByUserUserId(userId).stream().map(AddreesMappingHelper::map)
				.collect(Collectors.toList());

		return collect;
	}

	@Override
	public AddressDTO updateAddress(Integer userId, Integer addressId, AddressDTO dtos) {

		List<Address> collect = addressRepository.findByUserUserId(userId).stream().collect(Collectors.toList());

		for (Address add : collect) {
			if (add.getAddressId() == addressId) {

				add.setCity(dtos.getCity());
				add.setFullAddress(dtos.getFullAddress());
				add.setPostalCode(dtos.getPostalCode());

				Address address2 = addressRepository.save(add);
				AddressDTO addressDTO = AddreesMappingHelper.map(address2);
				return addressDTO;
			}
		}
		return null;
	}

	@Override
	public String deleteAddressByUsingAddressID(Integer userId, Integer addressId) {

		List<Address> collect = addressRepository.findByUserUserId(userId).stream().collect(Collectors.toList());

		collect.stream().filter(add->add.getAddressId() == addressId).forEach(addressRepository::delete);
		
		return "Address Deleted";
	}

}
