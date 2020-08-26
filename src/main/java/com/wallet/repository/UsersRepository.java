package com.wallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wallet.entity.Users;

public interface UsersRepository extends JpaRepository<Users, Long> {

}
