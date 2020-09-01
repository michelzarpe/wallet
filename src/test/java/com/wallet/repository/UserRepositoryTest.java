package com.wallet.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.wallet.entity.Users;
import com.wallet.util.enums.RoleEnum;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class UserRepositoryTest {

	private static final String EMAIL = "michel@test.com";
	@Autowired
    UsersRepository repository;
	
	@Before
	public void setUp() {
		Users u = new Users(null, "Michel", "123456", EMAIL,RoleEnum.ROLE_ADMIN);
		repository.save(u);
	}
	
	@Test
	public void testSave() {
		Users u = new Users(null, "password", "name", "email",RoleEnum.ROLE_ADMIN);
		Users response = repository.save(u);
		assertNotNull(response);
	}
	
	@Test
	public void testFindByEmail() {
		Optional<Users> u = repository.findByEmailEquals(EMAIL);
		assertTrue(u.isPresent());
		assertEquals(u.get().getEmail(), EMAIL);
	}
	
	
	@After
	public void tearDown() {
		repository.deleteAll();
	}
	
	
}
