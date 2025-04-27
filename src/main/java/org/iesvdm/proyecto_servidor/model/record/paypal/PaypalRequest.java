package org.iesvdm.proyecto_servidor.model.record.paypal;

import java.math.BigDecimal;

public record PaypalRequest (
        String method,
        BigDecimal amount,
        String currency,
        String description
) {}
