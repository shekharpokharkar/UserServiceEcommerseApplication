package com.javaExpress.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.javaExpress.DTO.UserResponseDTO;
import com.javaExpress.Entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	public List<User> findByCredentialUserName(String userName);

	@Query(nativeQuery = false, value = "select new com.javaExpress.DTO.UserResponseDTO (u.firstName,u.lastName) from User u")
	public List<UserResponseDTO> findByFirstNameAndLastName();
}
