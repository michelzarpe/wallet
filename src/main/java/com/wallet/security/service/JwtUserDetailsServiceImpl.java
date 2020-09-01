package com.wallet.security.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.wallet.Service.UsersService;
import com.wallet.entity.Users;
import com.wallet.security.JwtUserFactory;



@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsersService userService;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<Users> user = userService.findByEmailEquals(email);

		if (user.isPresent()) {
			return JwtUserFactory.create(user.get());
		}

		throw new UsernameNotFoundException("Email não encontrado.");
	}

}

/*
 * 
 * para saber se o usuário está na base
 * 
 * 
 */