package com.wallet.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wallet.Service.UserWalletService;
import com.wallet.dto.UserWalletDTO;
import com.wallet.entity.UserWallet;
import com.wallet.repository.UserWalletRepository;
import com.wallet.repository.UsersRepository;
import com.wallet.repository.WalletRepository;

@Service
public class WalletItemServiceImp implements UserWalletService{

	@Autowired
	private UserWalletRepository repository;
	
	@Autowired
	private UsersRepository usersRepository;
	
	@Autowired
	private WalletRepository walletRepository;
	
	@Override
	public UserWallet save(UserWallet uw) {
		return repository.save(uw);
	}

	@Override
	public UserWallet convertDTOToEntity(UserWalletDTO userWalletDTO) {
		return new UserWallet(userWalletDTO.getId(), usersRepository.findById(userWalletDTO.getUsers()).get(), walletRepository.findById(userWalletDTO.getWallet()).get());
	}
	

}
