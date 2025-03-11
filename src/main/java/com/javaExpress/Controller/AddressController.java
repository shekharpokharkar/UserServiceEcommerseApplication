package com.javaExpress.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaExpress.DTO.AddressDTO;
import com.javaExpress.Service.AddressService;

@RestController
@RequestMapping("/api/v1")
public class AddressController {

	@Autowired
	public AddressService service;

	@PostMapping("/address/{addressId}")
	public ResponseEntity<List<AddressDTO>> saveAddress(@PathVariable("addressId") Integer id,
			@RequestBody List<AddressDTO> dtos) {
		List<AddressDTO> addressDTO = service.saveAddress(id, dtos);

		return new ResponseEntity<List<AddressDTO>>(addressDTO, HttpStatus.CREATED);
	}

	@PatchMapping("/address/{userId}/{addressId}")
	public ResponseEntity<AddressDTO> updatePartialAddress(@PathVariable("userId") Integer userId,
			@PathVariable("addressId") Integer addressId, @RequestBody AddressDTO dtos) {
		AddressDTO addressDTO = service.updateAddress(userId, addressId, dtos);

		return new ResponseEntity<AddressDTO>(addressDTO, HttpStatus.CREATED);
	}

	@GetMapping("/address/{userID}")
	public ResponseEntity<List<AddressDTO>> getAllAddress(@PathVariable("userID") Integer userId) {

		List<AddressDTO> addressDTO = service.AllAddressByUsingUserID(userId);

		return new ResponseEntity<List<AddressDTO>>(addressDTO, HttpStatus.CREATED);
	}

	@DeleteMapping("/address/{userID}/{addressId}")
	public ResponseEntity<String> deleteAddressByUsingAddressId(@PathVariable("userID") Integer userId,
			@PathVariable("addressId") Integer addressId) {

		String result = service.deleteAddressByUsingAddressID(userId, addressId);

		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

}
