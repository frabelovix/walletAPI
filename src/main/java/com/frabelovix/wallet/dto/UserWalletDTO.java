package com.frabelovix.wallet.dto;

import javax.validation.constraints.NotNull;

import com.frabelovix.wallet.entitty.User;
import com.frabelovix.wallet.entitty.Wallet;

import lombok.Data;

@Data
public class UserWalletDTO {
	
	private Long id;
	@NotNull(message="Informe o usuário")
	private User users;
	@NotNull(message="Informe a carteira")
	private Wallet wallet;	

}
