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

import com.wallet.Service.UsersService;
import com.wallet.dto.UsersDTO;
import com.wallet.entity.Users;
import com.wallet.response.Response;

@RestController
@RequestMapping("/users")
public class UsersController {
	@Autowired
	public UsersService service;
	
	@PostMapping
	public ResponseEntity<Response<UsersDTO>> create(@Valid @RequestBody UsersDTO usersDto, BindingResult result){
		Response<UsersDTO> response = new Response<UsersDTO>();
		if(result.hasErrors()) {
			result.getAllErrors().forEach(e -> response.getErrors().add(e.getDefaultMessage()));
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
		
		Users user = service.save(this.convertDTOToEntity(usersDto));
		response.setData(this.convertEntityToDTO(user));
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	private Users convertDTOToEntity(UsersDTO userDTO) {
		return new Users(userDTO.getId(), userDTO.getPassword(), userDTO.getName(), userDTO.getEmail());
	}
	
	private UsersDTO convertEntityToDTO(Users user) {
		return new UsersDTO(user.getId(), user.getPassword(), user.getName(), user.getEmail());
	}
}
