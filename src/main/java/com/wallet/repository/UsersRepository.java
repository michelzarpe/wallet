package com.wallet.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.wallet.entity.Users;

public interface UsersRepository extends JpaRepository<Users, Long> {
	@Transactional(readOnly = true)
	Optional<Users> findByEmailEquals(String email);
}
