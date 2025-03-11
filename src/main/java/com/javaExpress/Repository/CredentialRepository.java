package com.javaExpress.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaExpress.Entity.Credential;

public interface CredentialRepository extends JpaRepository<Credential, Integer>{

}
