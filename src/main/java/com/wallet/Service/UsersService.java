package com.wallet.Service;

import java.util.Optional;

import com.wallet.entity.Users;

public interface UsersService {

	public Users save(Users u);
	public Optional<Users> findByEmailEquals(String email);

}
