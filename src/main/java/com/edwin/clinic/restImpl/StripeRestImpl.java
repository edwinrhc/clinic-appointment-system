package com.edwin.clinic.restImpl;

import com.edwin.clinic.dto.appointment.AppointmentPaymentDTO;
import com.edwin.clinic.rest.PaymentRest;
import com.edwin.clinic.rest.StripeRest;
import com.edwin.clinic.service.StripeService;
import com.stripe.model.PaymentIntent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StripeRestImpl implements StripeRest {

    @Autowired
    private StripeService  stripeService;

    @Override
    public ResponseEntity<String> createPaymentIntent(@RequestParam Double amount) {
        try{
            PaymentIntent paymentIntent = stripeService.createPaymentIntent(amount,"usd");
            return ResponseEntity.ok(paymentIntent.getClientSecret());
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Stripe error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
