package com.edwin.clinic.serviceImpl;

import com.edwin.clinic.constants.ClinicConstants;
import com.edwin.clinic.entity.User;
import com.edwin.clinic.jwt.CustomerUsersDetailsService;
import com.edwin.clinic.jwt.JwtFilter;
import com.edwin.clinic.repository.UserRepository;
import com.edwin.clinic.service.UserService;
import com.edwin.clinic.utils.ClinicUtils;
import com.edwin.clinic.utils.JwtUtil;
import com.edwin.clinic.wrapper.UserWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    CustomerUsersDetailsService customerUsersDetailsService;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    JwtFilter jwtFilter;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {

        log.info("Inside signup {}",requestMap);
        try{
        if(validateSignUpMap(requestMap)){
            User user = userRepository.findByEmail(requestMap.get("email"));
            if(Objects.isNull(user)){
                userRepository.save(getUserFromMap(requestMap));
                return ClinicUtils.getResponseEntity("Successfully Registered", HttpStatus.OK);
            }else{
                return ClinicUtils.getResponseEntity("Email already exits", HttpStatus.BAD_REQUEST);
            }
        }else{
            return ClinicUtils.getResponseEntity(ClinicConstants.INVALID_DATA,HttpStatus.BAD_REQUEST);
        }
        }catch(Exception ex){
            ex.printStackTrace();;
        }

        return ClinicUtils.getResponseEntity(ClinicConstants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> login(Map<String, String> requestMap) {
        log.info("Inside login");
        try{
            Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(requestMap.get("email"),requestMap.get("password")));

            if(auth.isAuthenticated()){
                if(customerUsersDetailsService.getUserDetail().getStatus().equalsIgnoreCase("true")){
                    return new ResponseEntity<String>("{\"token\":\""+
                            jwtUtil.generateToken(customerUsersDetailsService.getUserDetail().getEmail(),
                                    customerUsersDetailsService.getUserDetail().getRole())+"\"}",HttpStatus.OK);
                }else{
                    return new ResponseEntity<String>("{\"message\":\""+"Wait for admin approval."+"\"}",HttpStatus.BAD_REQUEST);
                }
            }

        }catch (Exception ex){
            log.error("{}",ex);
        }

        return new ResponseEntity<String>("{\"message\":\""+"Bad Credentials."+"\"}",HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<List<UserWrapper>> getAllUser() {
        try{
            if(jwtFilter.isAdmin()){
                return new ResponseEntity<>(userRepository.getAllUser(),HttpStatus.OK);
            }else{
                return new ResponseEntity<>(new ArrayList<>(),HttpStatus.UNAUTHORIZED);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }

        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> updateUser(Map<String,String> requestMap) {
        try {
            if(jwtFilter.isAdmin()){
                Optional<User> optional = userRepository.findById(Integer.parseInt(requestMap.get("id")));
                if(!optional.isEmpty()){
                    userRepository.updateUserInfo(
                            requestMap.get("name"),
                            requestMap.get("contactNumber"),
                            Integer.parseInt(requestMap.get("id"))
                            );
                    List<String> requiredFields = List.of("id","name","contactNumber");
                    List<String> missingFields = new ArrayList<>();

                    for(String field : requiredFields){
                        if(!requestMap.containsKey(field) || requestMap.get(field).isBlank()) {
                            missingFields.add(field);
                        }
                    }

                    if (!missingFields.isEmpty()) {

                        String mensaje ="The fllowing fields are missing: " + String.join(", ", missingFields);
                        log.warn(mensaje,HttpStatus.BAD_REQUEST);
                        return ClinicUtils.getResponseEntity(mensaje, HttpStatus.BAD_REQUEST);
                    }

                    return ClinicUtils.getResponseEntity("User Status Updated Successfully",HttpStatus.OK) ;
                }else{
                    return ClinicUtils.getResponseEntity("User id doesn't not exist", HttpStatus.OK);
                }
            }else{
                return ClinicUtils.getResponseEntity(ClinicConstants.UNAUTHORIZED_ACCESS,HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ClinicUtils.getResponseEntity(ClinicConstants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private boolean validateSignUpMap(Map<String,String> requestMap){
        if(requestMap.containsKey("name") && requestMap.containsKey("contactNumber")
                && requestMap.containsKey("email") && requestMap.containsKey("password"))
        {
            return true;
        }
        return  false;

    }

    private User getUserFromMap(Map<String,String> requestMap){
        User user = new User();
        user.setName(requestMap.get("name"));
        user.setContactNumber(requestMap.get("contactNumber"));
        user.setEmail(requestMap.get("email"));
        user.setPassword(passwordEncoder.encode(requestMap.get("password")));
        user.setStatus("false");
        user.setRole("user");
        return user;
    }



}
