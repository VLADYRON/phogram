package com.phogram.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.phogram.domain.UserModel;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.StringUtils;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Optional;

/**
 * Created by gavin on 2017. 5. 5..
 */
@Data
public class UserDTO{

    @Size(min = 4,max = 50)
    private final String username;

    @Size(min=4,max = 50)
    private final String firstName;

    @Size(min=4,max = 50)
    private final String lastName;

    @Size(min=6,max = 512)
    private final String password;

    @Size(max = 100)
    @Pattern(regexp = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$")
    private final String email;

    //01012345678
    //010-1234-4567
    //010)1234-5678
    //@Pattern(regexp = "\\d{11}|(?:\\d{3}-){4}\\d{4}|\\(\\d{3}\\)\\d{4}-?\\d{4}")
    @Size(max = 13)
    @Pattern(regexp = "(?:\\d{3}-){4}\\d{4}") // 010-1234-5678
    private final String phone;

    private final String gravataUrl;

    public UserDTO(
            @JsonProperty("firstName")String firstName,
            @JsonProperty("lastName")String lastName,
            @JsonProperty("username")String username,
            @JsonProperty("password") String password,
            @JsonProperty("email") String email,
            @JsonProperty("phone") String phone,
            @JsonProperty("gravataUrl") String gravataUrl) {
        this.firstName =firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.gravataUrl = gravataUrl;
    }
//    public Optional<String> getUsername(){
//        return Optional.ofNullable(username);
//    }
//    public Optional<String> getEmail(){
//        return Optional.ofNullable(email);
//    }
//    public Optional<String> getLastName(){
//        return Optional.ofNullable(lastName);
//    }
//    public Optional<String> getfirstNme(){
//        return Optional.ofNullable(firstName);
//    }
//    public Optional<String> getPhone(){
//        return Optional.ofNullable(phone);
//    }
//    public Optional<String> getEncodedPassword() {
//        return Optional.ofNullable(password).map(p -> new BCryptPasswordEncoder().encode(p));
//    }
    public UserModel toUserModel() {
        UserModel user = new UserModel();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUsername(username);
        user.setPhone(StringUtils.isEmpty(phone) ? "":phone);
        user.setGravataUrl(StringUtils.isEmpty(gravataUrl) ? "https://www.gravatar.com/avatar/00000000000000000000000000000000":gravataUrl);
        user.setPassword(new BCryptPasswordEncoder().encode(password));
        user.setEmail(email);
        user.setCreateAt(new Date());
        user.setEnable(0);//0->사용중 1-> 회원탈퇴)
        return user;
    }
}