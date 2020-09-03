package com.wallet.Service;

import java.util.Optional;

import com.wallet.dto.UserWalletDTO;
import com.wallet.entity.UserWallet;

public interface UserWalletService {

	public UserWallet save(UserWallet uw);
	public UserWallet convertDTOToEntity(UserWalletDTO userWalletDTO);
	public Optional<UserWallet> findBuUsersIdAndWalletId(Long users, Long Wallet);
}
