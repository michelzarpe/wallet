package com.wallet.dto;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonInclude(value = Include.NON_NULL)
public class WalletItemDTO {
	
	private Long id;
	
	@Pattern(regexp="^(ENTRADA|SAIDA)$", message="Para o tipo somente são aceitos ENTRADA e SAIDA")
	private String type;
	
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date data;
	
	@Length(min = 5, message = "Minimo 5 caracteres")
	@NotEmpty(message = "Descrição não pode ser vazio")
	private String description;
	

	
	@NotNull(message = "Valor não pode ser vazio")
	private BigDecimal value;
	
	@NotNull(message = "Id da Carteira não pode ser vazio")
	private Long wallet;

}
