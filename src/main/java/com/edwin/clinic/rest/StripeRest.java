package com.edwin.clinic.rest;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/stripe")
public interface StripeRest {

    @PostMapping("/create-payment-intent")
    ResponseEntity<String> createPaymentIntent(@RequestParam Double amount);
}
