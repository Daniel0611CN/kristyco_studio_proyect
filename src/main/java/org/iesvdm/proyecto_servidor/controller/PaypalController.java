package org.iesvdm.proyecto_servidor.controller;

import org.iesvdm.proyecto_servidor.model.record.paypal.PaypalResponse;
import org.iesvdm.proyecto_servidor.model.record.paypal.PaypalRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.iesvdm.proyecto_servidor.service.PaypalService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin(origins = "https://kristyco-studio.vercel.app")
public class PaypalController {
    // Cambiar endpoint;
    private final PaypalService paypalService;

    @PostMapping("/payment/create")
    public PaypalResponse createPayment(@RequestBody PaypalRequest paypalRequest) throws JsonProcessingException {

        String cancelUrl = "https://kristyco-studio.vercel.app/payment/cancel";
        String successUrl = "https://kristyco-studio.vercel.app/payment/success";

//        String cancelUrl = "http://localhost:4200/payment/cancel";
//        String successUrl = "http://localhost:4200/payment/success";

        return paypalService.createOrder("CAPTURE",
                paypalRequest.currency(),
                paypalRequest.amount().toPlainString(),
                successUrl, cancelUrl);

    }

    @GetMapping("/payment/success")
    public ResponseEntity<String> paymentSuccess(@RequestParam("orderId") String orderId) throws JsonProcessingException {

        return this.paypalService.showOrderDetails(orderId);

    }

}

