package com.frabelovix.wallet.dto;


import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class UserDTO {
	private Long id;
	@Length(min=3, max=50, message="A nome deve ter no mínimo 3 e no máximo 50 caracteres")
	private String name;
	@Length(min=6, message="A senha deve ter no mínimo 6 caracteres")
	private String password;
	@Email(message="Email inválido")
	private String email;
	

}
