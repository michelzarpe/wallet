package com.wallet.Service;

import com.wallet.dto.UserWalletDTO;
import com.wallet.entity.UserWallet;

public interface UserWalletService {

	public UserWallet save(UserWallet uw);
	public UserWallet convertDTOToEntity(UserWalletDTO userWalletDTO);
}
