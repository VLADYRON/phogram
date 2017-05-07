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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
/**
 * Created by gavin on 2017. 5. 7..
 */
@RestController
@RequestMapping(value = PGConstants.REST_USER)
public class UserRestApi {

    private static final Logger log = LoggerFactory.getLogger(UserRestApi.class);

    @Autowired
    private UserService userService;
    @Autowired
    private JwtTokenHandler jwtTokenHandler;

    @RequestMapping(method= RequestMethod.GET)
    public ResponseEntity<?> findByUsername(HttpServletRequest request) throws Exception{
        try {
            Optional<UserDTO> parseFromReq = jwtTokenHandler.getUserDTO(request);
            if(parseFromReq.isPresent()){
                return new ResponseEntity<>(parseFromReq.get(), HttpStatus.OK);
            }else{
                return new ResponseEntity<>("Can't find user", HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Sorry. Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method= RequestMethod.PUT)
    public ResponseEntity<?> update(@RequestBody UserDTO userDTO) throws Exception{
        try {
            if(userService.update(userDTO)){
                return new ResponseEntity<>("Success update!", HttpStatus.OK);
            }else{
                return new ResponseEntity<>("Failed update!", HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Sorry. Server Error!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method= RequestMethod.DELETE)
    public ResponseEntity<?> deleteByUserIndex(HttpServletRequest request) throws Exception{
        try {
            Optional<UserDTO> parseFromReq = jwtTokenHandler.getUserDTO(request);
            if(userService.delete(parseFromReq.get().getUsername())){
                return new ResponseEntity<>("Success delete!", HttpStatus.OK);
            }else{
                return new ResponseEntity<>("Failed delete!", HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Sorry. Server Error!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
