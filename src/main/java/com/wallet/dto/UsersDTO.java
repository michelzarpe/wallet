package com.wallet.dto;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonInclude(value = Include.NON_NULL)
public class UsersDTO {
	
	private Long id;
	@NotNull
	@Length(min = 3, max=12, message = "6 até 12 caracteres")
	private String password;
	@Length(min = 3, max=50, message = "3 até 50 caracteres")
	private String name;
	@Email(message = "Email inválido")
	private String email;
}