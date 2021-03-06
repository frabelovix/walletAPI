package com.frabelovix.wallet.entitty;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.frabelovix.wallet.util.enums.RoleEnum;
import com.frabelovix.wallet.util.enums.TypeEnum;

import lombok.Data;

@Entity
@Data
@Table(name="users")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String password;
	private String email;
	//@NotNull
	@Transient
	@Enumerated(EnumType.STRING)
	private RoleEnum role;

	public RoleEnum getRole() {
		
		return RoleEnum.ROLE_ADMIN;
	}

}
