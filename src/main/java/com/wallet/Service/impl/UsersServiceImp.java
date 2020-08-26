package com.wallet.Service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wallet.Service.UsersService;
import com.wallet.entity.Users;
import com.wallet.repository.UsersRepository;

@Service
public class UsersServiceImp implements UsersService{

	@Autowired
	private UsersRepository repository;
	
	@Override
	public Users save(Users u) {
		return repository.save(u);
	}

	@Override
	public Optional<Users> findByEmailEquals(String email) {
		return repository.findByEmailEquals(email);
	}

}
