package com.example.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.example.shop.services.PayPalClient;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/paypal")
public class PayPalController {
    private final PayPalClient payPalClient;
    @Autowired
    PayPalController(PayPalClient payPalClient){
        this.payPalClient = payPalClient;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(value = "/make/payment")
    @PreAuthorize("hasRole('USER')")
    public Map<String, Object> makePayment(@RequestParam("sum") String sum){
        return payPalClient.createPayment(sum);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(value = "/complete/payment")
    @PreAuthorize("hasRole('USER')")
    public Map<String, Object> completePayment(HttpServletRequest request, @RequestParam("paymentId") String paymentId, @RequestParam("payerId") String payerId){
        return payPalClient.completePayment(request);
    }
}
