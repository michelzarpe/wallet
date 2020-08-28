package com.wallet.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wallet.Service.WalletService;
import com.wallet.entity.Wallet;
import com.wallet.repository.WalletRepository;

@Service
public class WalletServiceImp implements WalletService{

	@Autowired
	private WalletRepository repository;
	
	@Override
	public Wallet save(Wallet w) {
		return repository.save(w);
	}
}
