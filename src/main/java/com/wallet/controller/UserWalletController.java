package com.wallet.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wallet.Service.UserWalletService;
import com.wallet.dto.UserWalletDTO;
import com.wallet.entity.UserWallet;
import com.wallet.response.Response;

@RestController
@RequestMapping("/user-wallet")
public class UserWalletController {
	@Autowired
	public UserWalletService service;
	
	@PostMapping
	public ResponseEntity<Response<UserWalletDTO>> create(@Valid @RequestBody UserWalletDTO userWalletDTO, BindingResult result){
		Response<UserWalletDTO> response = new Response<UserWalletDTO>();
		if(result.hasErrors()) {
			result.getAllErrors().forEach(e -> response.getErrors().add(e.getDefaultMessage()));
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
		
		UserWallet userWallet = service.save(service.convertDTOToEntity(userWalletDTO));
		response.setData(new UserWalletDTO(userWallet));
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
}
