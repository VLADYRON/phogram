package com.phogram.controller.user;

import com.phogram.dto.UserDTO;
import com.phogram.security.JwtTokenHandler;
import com.phogram.service.UserService;
import com.phogram.utils.PGConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import java.util.Map;
import java.util.Optional;

/**
 * Created by gavin on 2017. 5. 5..
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private JwtTokenHandler jwtTokenHandler;

    @RequestMapping(value="login", method= RequestMethod.POST)
    public ResponseEntity<String> login(@RequestBody Map<String, String> json) throws ServletException {
        if(json.get("username") == null || json.get("password") == null) {
            return new ResponseEntity<>("Please fill in username and password", HttpStatus.BAD_REQUEST);
        }

        String userName = json.get(PGConstants.USERNAME);
        String password = json.get(PGConstants.PASSWORD);

        Optional<UserDTO> user = Optional.empty();
        try {
            user = userService.findByUsername(userName);
            if(!user.isPresent()) {
                return new ResponseEntity<>("User name not found.",HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return new ResponseEntity<>("Server Error.",HttpStatus.INTERNAL_SERVER_ERROR);
        }

        String pwd = user.get().getPassword();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (!encoder.matches(password, pwd)) {
            log.error("Invalid login. Please check your username and password");
            throw new ServletException("Invalid login. Please check your username and password");
        }
        return new ResponseEntity<>(jwtTokenHandler.createTokenForUser(user.get()),HttpStatus.OK);
    }

    @RequestMapping(value="/register", method = RequestMethod.POST)
    public ResponseEntity<String> registerUser(@RequestBody UserDTO userDTO) throws Exception{
        if(userService.create(userDTO)){
            return new ResponseEntity<>("Success register!!", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Failed register!!",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
