package com.edwin.clinic.service;

import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

public interface StripeService {

    PaymentIntent createPaymentIntent(Double amount,String currency) throws StripeException;

}
