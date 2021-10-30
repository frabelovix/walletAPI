package com.frabelovix.wallet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frabelovix.wallet.dto.UserDTO;
import com.frabelovix.wallet.entitty.User;
import com.frabelovix.wallet.response.Response;
import com.frabelovix.wallet.service.UserService;

@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
	UserService service;

	@PostMapping
	public ResponseEntity<Response<UserDTO>> create(@Validated @RequestBody UserDTO dto, BindingResult result) {

		Response<UserDTO> response = new Response<UserDTO>();

		User u = service.save(this.convertDtoToEntity(dto));
		
		response.setData(this.convertEntityToDto(u));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(response);

	}

	private User convertDtoToEntity(UserDTO dto) {
		User u = new User();
		u.setName(dto.getName());
		u.setPassword(dto.getPassword());
		u.setEmail(dto.getEmail());

		return u;
	}
	
	private UserDTO convertEntityToDto(User u) {
		UserDTO dto = new UserDTO();
		dto.setName(u.getName());
		dto.setPassword(u.getPassword());
		dto.setEmail(u.getEmail());

		return dto;
	}


}
