package org.iesvdm.proyecto_servidor.model.record.paypal;

public record PaypalResponse (
        String href,
        String orderId
) {}
