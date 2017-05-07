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
	public void createUserTest(){
		String firstName = "Test1";
		String lastName = "Test1";
		String username = "Test1";
		String email = "test1@test.com";
		String phone = "010-1234-5678";
		String password = "test1";
		String gravataurl = "http://1.gravatar.com/avatar/8635ec3bc374565ad881afc0f3faa919";

		UserDTO userDTO = new UserDTO(firstName,lastName,username,password,email,phone,gravataurl);
		try {
			Assert.isTrue(userService.create(userDTO),"Ok");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	@Ignore
	public void findByUsernameOrEmailOrPhone() {
		try {
			System.out.println("XX: "+userService.findByUsernameOrEmailOrPhone("test1@test.com").get());
			Assert.isTrue(userService.findByUsernameOrEmailOrPhone("test1@test.com").isPresent(),"Ok");
			Assert.isTrue(!userService.findByUsernameOrEmailOrPhone("1test1@test.com").isPresent(),"Fail");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
