package com.javaExpress.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaExpress.DTO.UserDTO;
import com.javaExpress.DTO.UserResponseDTO;
import com.javaExpress.ExceptionHandling.UserNotFoundException;
import com.javaExpress.Service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/users")
@Slf4j
@Tag(description = "To All Oparation Related To User ", name = "UserController")
public class UserController {

	@Autowired
	private UserService service;

	@Operation(summary = "ADD_USER", description = "This is used To Add user in database")
	@PostMapping("/")
	public ResponseEntity<UserDTO> saveUser(
			@Valid @NotNull(message = "Input Must Not Be Null") @RequestBody UserDTO dto) {
		log.info("UserController::saveUser");
		UserDTO userDTO = null;
		userDTO = service.saveUser(dto);

		// return new ResponseEntity<UserDTO>(userDTO, HttpStatus.CREATED);
		//
		// return ResponseEntity.ok(userDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(userDTO);
	}

	@GetMapping("/")
	public ResponseEntity<List<UserDTO>> getAllUser() {
		log.info("UserController::getAllUser");

		List<UserDTO> allUserDTO = service.findAllUserDTO();

		return ResponseEntity.status(HttpStatus.OK).body(allUserDTO);
	}

	@GetMapping(value = "/{userID}", params = "userId")
	public ResponseEntity<UserDTO> getUserById(@PathVariable("userID") Integer id) {
		log.info("UserController::getUserById {}", id);

		UserDTO allUserDTO = service.findUserById(id);

		return ResponseEntity.status(HttpStatus.OK).body(allUserDTO);
	}

	@GetMapping(value = "/studentRows")
	public ResponseEntity<List<UserResponseDTO>> getUserByFirstNameANDLastName() {
		log.info("UserController::getUserByFirstNameANDLastName");

		List<UserResponseDTO> userByFirstNameandLastName = service.findUserByFirstNameandLastName();

		return ResponseEntity.status(HttpStatus.OK).body(userByFirstNameandLastName);
	}

	@GetMapping(value = "/{userName}", params = "userName")
	public ResponseEntity<List<UserDTO>> getUserByUserName(@PathVariable("userName") String userName) {
		log.info("UserController::getUserByUserName {}", userName);

		List<UserDTO> userByUserName = service.findUserByUserName(userName);

		return ResponseEntity.status(HttpStatus.OK).body(userByUserName);
	}

	@DeleteMapping("/{userID}")
	public ResponseEntity<?> deleteUserById(@PathVariable("userID") Integer id) {
		log.info("UserController::deleteUserById {}", id);

		try {
			service.deleteUserById(id);
		} catch (Exception ex) {
			throw new UserNotFoundException("User Not Found Exception");
		}

		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
	}

	@PutMapping("/{userID}")
	public ResponseEntity<UserDTO> updateUserById(@PathVariable("userID") Integer id, @RequestBody UserDTO dto) {
		log.info("UserController::updateUserById {}", id);

		UserDTO updateUser = service.updateUser(id, dto);

		return ResponseEntity.status(HttpStatus.ACCEPTED).body(updateUser);
	}

	@PatchMapping("/{userID}")
	public ResponseEntity<UserDTO> partialUpdateUserById(@PathVariable("userID") Integer id,
			@RequestBody Map<String, Object> values) {

		log.info("UserController::partialUpdateUserById {}", id);

		UserDTO updateUser = service.partialUpdateUser(id, values);

		return ResponseEntity.status(HttpStatus.ACCEPTED).body(updateUser);
	}

	@GetMapping(value = "/studentPage")
	public ResponseEntity<List<UserDTO>> getUserByUserNameUsingPageRequest() {
		log.info("UserController::getUserByUserNameUsingPageRequest");

		// List<UserDTO> userByUserName = service.findAllUser();
		List<UserDTO> allUserByOrder = service.findAllUserByOrder();
		return ResponseEntity.status(HttpStatus.OK).body(allUserByOrder);
	}

}
