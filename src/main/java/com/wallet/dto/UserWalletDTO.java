package com.wallet.dto;


import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.wallet.entity.UserWallet;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonInclude(value = Include.NON_NULL)
public class UserWalletDTO {
	
	private Long id;
	@NotNull(message = "Necessário informar id User")
	private Long users;
	@NotNull(message = "Necessário informar id Wallet")
	private Long wallet;
	
	public UserWalletDTO(UserWallet userWallet) {
		this.id=userWallet.getId();
		this.users=userWallet.getUsers().getId();
		this.wallet=userWallet.getWallet().getId();
	}
}
