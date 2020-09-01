package com.wallet.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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
import com.wallet.util.enums.RoleEnum;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UserControllerTest {
	
	public static final Long ID = 1L;
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
				.content(getJsonPayLoad(ID, PASSWORD, NAME, EMAIL))
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isCreated())
		.andExpect(jsonPath("$.data.id").value(ID))
		.andExpect(jsonPath("$.data.email").value(EMAIL))
		.andExpect(jsonPath("$.data.name").value(NAME))
		//.andExpect(jsonPath("$.data.password").value(PASSWORD));
		.andExpect(jsonPath("$.data.password").doesNotExist());
	}
	
	
	@Test
	public void testSaveInvalidUsers() throws Exception {
		mvc.perform(MockMvcRequestBuilders.post(URL)
				.content(getJsonPayLoad(ID, PASSWORD, NAME, "email"))
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.errors[0]").value("Email inválido"));
	}
	
	public Users getMockUser() {
		Users u = new Users(ID, PASSWORD, NAME, EMAIL,RoleEnum.ROLE_ADMIN);
		return u;
	}
	
	public String getJsonPayLoad(Long id, String password, String name, String email) throws JsonProcessingException {
		UsersDTO dto = new UsersDTO(id, password, name, email,RoleEnum.ROLE_ADMIN.toString());
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(dto);
	}
	
}
