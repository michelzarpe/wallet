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

import com.wallet.Service.WalletService;
import com.wallet.dto.WalletDTO;
import com.wallet.entity.Wallet;
import com.wallet.response.Response;

@RestController
@RequestMapping("/wallet")
public class WalletController {
	@Autowired
	public WalletService service;
	
	@PostMapping
	public ResponseEntity<Response<WalletDTO>> create(@Valid @RequestBody WalletDTO walletDTO, BindingResult result){
		Response<WalletDTO> response = new Response<WalletDTO>();
		if(result.hasErrors()) {
			result.getAllErrors().forEach(e -> response.getErrors().add(e.getDefaultMessage()));
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
		
		Wallet wallet = service.save(this.convertDTOToEntity(walletDTO));
		response.setData(this.convertEntityToDTO(wallet));
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	private Wallet convertDTOToEntity(WalletDTO waletDTO) {
		return new Wallet(waletDTO.getId(), waletDTO.getName(), waletDTO.getValue());
	}
	
	private WalletDTO convertEntityToDTO(Wallet wallet) {
		return new WalletDTO(wallet.getId(), wallet.getName(), wallet.getValue());
	}
}
