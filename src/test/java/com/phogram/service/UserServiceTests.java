package com.phogram.service;

import com.phogram.dto.UserDTO;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTests {

	@Autowired
	private UserService userService;

	@Test
	@Ignore
	public void createUser(){
		String firstName = "Gavin";
		String lastName = "Kim";
		String username = "Gavin";
		String email = "test@test.com";
		String phone = "010-1234-5678";
		String password = "test";
		String gravataurl = "http://1.gravatar.com/avatar/8635ec3bc374565ad881afc0f3faa919";

		UserDTO userDTO = new UserDTO(firstName,lastName,username,password,email,phone,gravataurl);
		try {
			userService.create(userDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void duplicateCheckTest(){
		try {
			Assert.isTrue(!userService.duplicateCheck("1test@test.com"),"Fail");
			Assert.isTrue(userService.duplicateCheck("test@test.com"),"Ok");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
