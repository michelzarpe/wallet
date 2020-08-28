package com.wallet.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wallet.Service.UsersService;
import com.wallet.dto.UsersDTO;
import com.wallet.entity.Users;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UserControllerTest {
	
	public static final String PASSWORD = "123456";
	public static final String NAME = "Michel Zarpelon";
	public static final String EMAIL = "michel@michel.com";
	public static final String URL = "/users";
	
	@MockBean
	UsersService usersService;
	
	@Autowired
	MockMvc mvc;
	
	
	@Test
	public void testSave() throws Exception {
		BDDMockito.given(usersService.save(Mockito.any(Users.class))).willReturn(getMockUser());
		
		mvc.perform(MockMvcRequestBuilders.post(URL)
				.content(getJsonPayLoad())
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isCreated());
	}
	
	public Users getMockUser() {
		Users u = new Users(null, PASSWORD, NAME, EMAIL);
		return u;
	}
	
	public String getJsonPayLoad() throws JsonProcessingException {
		UsersDTO dto = new UsersDTO(null, PASSWORD, NAME, EMAIL);
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(dto);
	}
	
}
