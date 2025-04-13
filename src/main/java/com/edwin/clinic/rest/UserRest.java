package com.edwin.clinic.rest;

import com.edwin.clinic.wrapper.UserWrapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    public ResponseEntity<String> signUp(@RequestBody(required = true)Map<String,String> requestMap);

    @PostMapping(path="/login")
    public ResponseEntity<String> login(@RequestBody(required = true) Map<String,String> requestMap);

    @GetMapping("/get")
    public ResponseEntity<List<UserWrapper>> getAllUser();

    @PostMapping(path="/update")
    public ResponseEntity<String> update(@RequestBody(required = true) Map<String,String> requestMap);



}
