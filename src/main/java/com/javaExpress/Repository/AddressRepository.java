package com.javaExpress.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaExpress.Entity.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {

	public List<Address> findByUserUserId(Integer userId);

}
