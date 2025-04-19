package com.edwin.clinic.serviceImpl;

import com.edwin.clinic.service.StripeService;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.stereotype.Service;

@Service
public class StripeServiceImpl implements StripeService {

    @Override
    public PaymentIntent createPaymentIntent(Double amount, String currency) throws StripeException {
        PaymentIntentCreateParams params =
                PaymentIntentCreateParams.builder()
                        .setAmount((long)(amount*100))
                        .setCurrency(currency)
                        .build();
        return PaymentIntent.create(params);
    }
}
