package com.edwin.clinic.service;

import com.edwin.clinic.wrapper.UserWrapper;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface UserService {

    ResponseEntity<String> signUp(Map<String,String> requestMap);
    ResponseEntity<String> login(Map<String,String> requestMap);
    ResponseEntity<List<UserWrapper>> getAllUser();
    ResponseEntity<String> updateUser(Map<String,String> requestMap);
    ResponseEntity<String> updateStatus(Map<String,String> requestMap);

//    ResponseEntity<String>update(Map<String,String> requestMap);
//    ResponseEntity<String> checKToken();
//
//    ResponseEntity<String> changePassword(Map<String, String> requestMap);
//
//    ResponseEntity<String> forgotPassword(Map<String, String> requestMap);
}

