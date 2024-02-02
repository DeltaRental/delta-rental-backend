package com.delta.rental.deltarental.controllers;

import com.delta.rental.deltarental.services.dtos.requests.payment.AddPaymentRequest;
import com.delta.rental.deltarental.services.dtos.responses.payment.GetPaymentResponse;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/payments")
@CrossOrigin
public class PaymentController {


    @PostMapping("/create-payment-intent")
    public GetPaymentResponse createPaymentIntent(@RequestBody AddPaymentRequest addPaymentRequest) throws StripeException {

            PaymentIntentCreateParams params =
                    PaymentIntentCreateParams.builder()
                            .setAmount(15 * 100L)
                            .setCurrency("usd")
                            // In the latest version of the API, specifying the `automatic_payment_methods` parameter is optional because Stripe enables its functionality by default.
                            .setAutomaticPaymentMethods(
                                    PaymentIntentCreateParams.AutomaticPaymentMethods
                                            .builder()
                                            .setEnabled(true)
                                            .build()
                            )
                            .build();

            // Create a PaymentIntent with the order amount and currency
            PaymentIntent paymentIntent = PaymentIntent.create(params);

            return new GetPaymentResponse(paymentIntent.getClientSecret());

    }

}
