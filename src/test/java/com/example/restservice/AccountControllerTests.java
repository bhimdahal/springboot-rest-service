/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *	  https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.restservice;

import com.example.restservice.models.Accounts;
import com.example.restservice.services.AccountsService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.ResourceAccessException;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.util.AssertionErrors.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AccountControllerTests {

	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private AccountsService accountsService;

	@Test
	public void whengetallaccounts_thenreturn_jsonarray() throws Exception {

		Accounts test1 = new Accounts(1,"test1","password1", "test1@gmail.com",
				new Timestamp(new Date().getTime()), new Timestamp(new Date().getTime()));
		Accounts test2 = new Accounts(2,"test2","password12", "test2@gmail.com",
				new Timestamp(new Date().getTime()), new Timestamp(new Date().getTime()));
		Accounts test3 = new Accounts(3,"test3","password13", "test3@gmail.com",
				new Timestamp(new Date().getTime()), new Timestamp(new Date().getTime()));

		List<Accounts> accountsList = new ArrayList<>();
		accountsList.add(test1);
		accountsList.add(test2);
		accountsList.add(test3);

		Mockito.when(accountsService.findAll())
				.thenReturn(accountsList);

		this.mockMvc.perform(get("/api/accounts"))
      			.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(3)))
				.andExpect(jsonPath("$[1].username").value("test2"));
	}

	@Test
	public void whengetallaccounts_thenreturn_error() throws Exception {

//		Mockito.when(accountsService.findAll())
//				.thenThrow(new RuntimeException("empty data"));

//		this.mockMvc.perform(get("/api/accounts", "Bad Request"))
//				.andExpect(status().isBadRequest());
				//.andExpect(result -> assertTrue(result.getResolvedException() instanceof ResourceAccessException));
				//.andExpect(jsonPath("$", hasSize(0)));
				//.andExpect(Exception.class instanceof RuntimeException);
	}



}
