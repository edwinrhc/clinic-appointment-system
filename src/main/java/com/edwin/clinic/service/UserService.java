package com.edwin.clinic.service;

import com.edwin.clinic.dto.user.LoginDTO;
import com.edwin.clinic.dto.user.UserDTO;
import com.edwin.clinic.dto.user.UserListDTO;
import com.edwin.clinic.dto.user.UserUpdateDTO;
import com.edwin.clinic.wrapper.UserWrapper;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface UserService {

//    ResponseEntity<String> signUp(Map<String,String> requestMap);
    ResponseEntity<String> signUp(UserDTO userDTO);

//    ResponseEntity<String> login(Map<String,String> requestMap);
    ResponseEntity<String> login(LoginDTO loginDTO);

    ResponseEntity<List<UserListDTO>> listUsers();

    ResponseEntity<List<UserWrapper>> getAllUser();

    ResponseEntity<String> updateUser(Map<String,String> requestMap);

    ResponseEntity<String> updateUserProfile(UserUpdateDTO userUpdateDTO);

    ResponseEntity<String> updateStatus(Map<String,String> requestMap);

    ResponseEntity<String> forgotPassword(Map<String, String> requestMap);
    ResponseEntity<String> resetPassword(Map<String, String> requestMap);





//    ResponseEntity<String>update(Map<String,String> requestMap);
//    ResponseEntity<String> checKToken();
//
//    ResponseEntity<String> changePassword(Map<String, String> requestMap);
//

}

