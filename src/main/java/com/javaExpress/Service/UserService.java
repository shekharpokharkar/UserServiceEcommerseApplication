package com.javaExpress.Service;

import java.util.List;
import java.util.Map;

import com.javaExpress.DTO.UserDTO;
import com.javaExpress.DTO.UserResponseDTO;
import com.javaExpress.Entity.User;

public interface UserService {

	public UserDTO saveUser(UserDTO user);

	public UserDTO findUserById(Integer userId);

	public List<UserDTO> findAllUserDTO();

	public UserDTO updateUser(Integer UserId, UserDTO user);

	public List<UserDTO> findUserByUserName(String userNae);

	public void deleteUserById(Integer UserID);

	public UserDTO partialUpdateUser(Integer id, Map<String, Object> values);

	public List<UserResponseDTO> findUserByFirstNameandLastName();

	public List<UserDTO> findAllUser();
	
	public List<UserDTO> findAllUserByOrder();
}
