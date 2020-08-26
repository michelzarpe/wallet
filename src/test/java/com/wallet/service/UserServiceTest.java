package com.wallet.service;

import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.wallet.Service.UsersService;
import com.wallet.entity.Users;
import com.wallet.repository.UsersRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class UserServiceTest {
	
	//simular resposta
	@MockBean
	UsersRepository repository;
	
	@Autowired
	UsersService service;
	
	//resposta mocada, falar para o mokito o que nos esperamos que ele retorne
	@Before
	public void setUp() {
		BDDMockito.given(repository.findByEmailEquals(Mockito.anyString())).willReturn(Optional.of(new Users()));
	}
	
	@Test
	public void testFindByEmail() {
		Optional<Users> user = service.findByEmailEquals("email@test.com");
		assertTrue(user.isPresent());
	}
	

}
