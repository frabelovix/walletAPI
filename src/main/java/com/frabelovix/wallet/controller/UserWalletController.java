package com.frabelovix.wallet.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frabelovix.wallet.dto.UserWalletDTO;
import com.frabelovix.wallet.entitty.User;
import com.frabelovix.wallet.entitty.UserWallet;
import com.frabelovix.wallet.entitty.Wallet;
import com.frabelovix.wallet.response.Response;
import com.frabelovix.wallet.service.UserWalletService;

@RestController
@RequestMapping("user-wallet")
public class UserWalletController {
	
	@Autowired
	private UserWalletService service;

	@PostMapping
	public ResponseEntity<Response<UserWalletDTO>> create(@Valid @RequestBody UserWalletDTO dto, BindingResult result) {
		Response<UserWalletDTO> response = new Response<UserWalletDTO>();

		if (result.hasErrors()) {
			result.getAllErrors().forEach(r -> response.getErrors().add(r.getDefaultMessage()));

			return ResponseEntity.badRequest().body(response);
		}

		UserWallet uw = service.save(this.convertDTOToEntity(dto));

		response.setData(this.convertEntityToDTO(uw));

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	private UserWallet convertDTOToEntity(UserWalletDTO dto) {
		UserWallet uw = new UserWallet();
		User u = new User();
		u.setId(dto.getUsers());
		Wallet w = new Wallet();
		w.setId(dto.getWallet());
		uw.setUsers(u);
		uw.setWallet(w);

		return uw;
	}

	private UserWalletDTO convertEntityToDTO(UserWallet uw) {
		UserWalletDTO dto = new UserWalletDTO();
		dto.setId(uw.getId());
		dto.setUsers(uw.getUsers().getId());
		dto.setWallet(uw.getWallet().getId());

		return dto;
	}	
	
	

}
