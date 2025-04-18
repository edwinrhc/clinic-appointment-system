package com.edwin.clinic.rest;

import com.edwin.clinic.dto.user.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping(path ="/user")
public interface UserRest {

    @Operation(summary = "Registrar nuevo usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario registrado con éxito"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos o email ya registrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PostMapping(path = "/signup")
    public ResponseEntity<String> signUp(@RequestBody @Valid UserDTO userDto);

    @PostMapping(path="/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginDTO loginDTO);

    @GetMapping("/get")
    public ResponseEntity<List<UserListDTO>> getAllUser();

    @PutMapping("/update-profile")
    public ResponseEntity<String> updateProfile(@RequestBody @Valid UserUpdateDTO dto);

    @PutMapping("/status")
    public ResponseEntity<String> updateStatus(@RequestBody @Valid UserStatusDTO dto);


    @PostMapping(path="/forgotPassword")
    public ResponseEntity<String> forgotPassword(@RequestBody Map<String,String> requestMap);

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody(required = true) Map<String,String> requestMap);


}
