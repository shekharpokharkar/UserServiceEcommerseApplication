package com.javaExpress.Service;

import java.lang.reflect.Field;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import com.javaExpress.DTO.UserDTO;
import com.javaExpress.DTO.UserResponseDTO;
import com.javaExpress.Entity.Credential;
import com.javaExpress.Entity.User;
import com.javaExpress.Repository.UserRepository;
import com.javaExpress.Util.UserMappingHelper;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repository;

	@Override
	public UserDTO saveUser(UserDTO dto) {

		User user = UserMappingHelper.map(dto);

		User save = repository.save(user);

		UserDTO userDTO = UserMappingHelper.map(save);

		return userDTO;
	}

	@Override
	public UserDTO findUserById(Integer userId) {

		return repository.findById(userId).stream().map(UserMappingHelper::map).findFirst()
				.orElseThrow(() -> new RuntimeException("User Not Found Exception"));

	}

	@Override
	public List<UserDTO> findAllUserDTO() {

		return repository.findAll().stream().sorted(Comparator.comparing(User::getUserName)).map(UserMappingHelper::map)
				.toList();
	}

	@Override
	public UserDTO updateUser(Integer UserId, UserDTO userDTO) {

		User existingUser = repository.findById(UserId).stream().findFirst()
				.orElseThrow(() -> new RuntimeException("User Not Found"));

		existingUser.setEmail(userDTO.getEmail());
		existingUser.setFirstName(userDTO.getFirstName());
		existingUser.setLastName(userDTO.getLastName());
		existingUser.setPhone(userDTO.getPhone());
		existingUser.setUserName(userDTO.getUserName());

		if (userDTO.getCredentialdto() != null) {

			Credential credential = existingUser.getCredential();

			credential.setPassword(userDTO.getCredentialdto().getPassword());
			credential.setUserName(userDTO.getCredentialdto().getUserName());
			credential.setRoleBasedAuthority(userDTO.getCredentialdto().getRoleBasedAuthority());
			credential.setIsAccountNonLocked(userDTO.getCredentialdto().getIsAccountNonLocked());
			credential.setIsAccountNotExpired(userDTO.getCredentialdto().getIsAccountNotExpired());
			credential.setIsCredentialNonExpired(userDTO.getCredentialdto().getIsCredentialNonExpired());
			credential.setIsEnabled(userDTO.getCredentialdto().getIsEnabled());

			credential.setUser(existingUser);

		}

		User save = repository.save(existingUser);

		return UserMappingHelper.map(save);

	}

	@Override
	public List<UserDTO> findUserByUserName(String userName) {

		return repository.findByCredentialUserName(userName).stream().map(UserMappingHelper::map).toList();

	}

	@Override
	public void deleteUserById(Integer UserID) {

		User user = repository.findById(UserID).stream().findFirst()
				.orElseThrow(() -> new RuntimeException("User Not Found"));

		repository.delete(user);
	}

	@Override
	public UserDTO partialUpdateUser(Integer id, Map<String, Object> values) {

		User existingUser = repository.findById(id).orElseThrow(() -> new RuntimeException("User Not Found"));

		values.forEach((key, value) -> {

			if ("credential".equals(key) && value instanceof Map) {

				if (existingUser.getCredential() == null) {
					existingUser.setCredential(new Credential());

				}
				updateCredential(existingUser.getCredential(), (Map<String, Object>) value);
			} else {
				Field field = ReflectionUtils.findField(User.class, key);
				if (field != null) { // Ensure field exists before updating
					field.setAccessible(true);
					ReflectionUtils.setField(field, existingUser, value);
				}
			}

		});

		User save = repository.save(existingUser);
		return UserMappingHelper.map(save);

	}

	private void updateCredential(Credential credential, Map<String, Object> values) {

		values.forEach((key, value) -> {

			Field field = ReflectionUtils.findField(Credential.class, key);
			if (field != null) {
				field.setAccessible(true);
				ReflectionUtils.setField(field, credential, value);
			}

		});

	}

	@Override
	public List<UserResponseDTO> findUserByFirstNameandLastName() {

		List<UserResponseDTO> byFirstNameAndLastName = repository.findByFirstNameAndLastName();

		return byFirstNameAndLastName;
	}

	@Override
	public List<UserDTO> findAllUser() {

		Page<User> all = repository.findAll(PageRequest.of(0, 4, Sort.by("userName")));
		List<User> user = all.getContent();

		List<UserDTO> list = user.stream().map(UserMappingHelper::map).toList();

		return list;
	}

	public List<UserDTO> findAllUserByOrder() {

		Page<User> all = repository.findAll(PageRequest.of(0, 4, Sort.by("userName").descending()));

		PageRequest.of(0, 5, Sort.by(Sort.Order.asc("email"), Sort.Order.desc("userName")));

		Page<User> all2 = repository
				.findAll(PageRequest.of(0, 5, Sort.by(Sort.Order.asc("email"), Sort.Order.desc("userName"))));

		List<User> user = all2.getContent();

		List<UserDTO> list = user.stream().map(UserMappingHelper::map).toList();

		return list;
	}

}
