package com.wallet.dto;


import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonInclude(value = Include.NON_NULL)
public class WalletDTO {
	
	private Long id;
	@NotNull(message = "Nome não pode ser nullo")
	@Length(min = 3, message = "3 até 50 caracteres")
	private String name;
	@NotNull(message = "Valores não podem ser nulos")
	private BigDecimal value;

}
