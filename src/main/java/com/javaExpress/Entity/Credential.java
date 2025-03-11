package com.javaExpress.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.javaExpress.Enum.RoleBasedAuthority;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Credential")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Credential {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer credentialId;
	
	@Column(unique = true)
	private String userName;
	
	private String password;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "role")
	private RoleBasedAuthority roleBasedAuthority;
	
	private Boolean isEnabled;
	private Boolean isAccountNotExpired;
	private Boolean isAccountNonLocked;
	private Boolean isCredentialNonExpired;

	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name ="userId",nullable = false)
	private User user;

}
