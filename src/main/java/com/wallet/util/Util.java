package com.wallet.util;

import java.util.Optional;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.wallet.Service.UsersService;
import com.wallet.entity.Users;

//spring passa manipular como Bean
@Component
public class Util {
	private static UsersService staticService;
		
	public Util(UsersService service) {
		this.staticService = service;
	}	
	
	public static Long getAuthenticatedUserId() {
		try {
			Optional<Users> user = staticService.findByEmailEquals(SecurityContextHolder.getContext().getAuthentication().getName());
			if(user.isPresent()) {
				return user.get().getId();
			}else {
				return null;
			}
		}catch (Exception e) {
			return null;
		}
	}	
}
