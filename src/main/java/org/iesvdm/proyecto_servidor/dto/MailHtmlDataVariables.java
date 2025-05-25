package org.iesvdm.proyecto_servidor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MailHtmlDataVariables {
    private String welcome;
    private String description;
    private String link;
    private String token;
}
