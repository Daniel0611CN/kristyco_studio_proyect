package org.iesvdm.proyecto_servidor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MailHtmlDataVariables {

    private String title;

    private String bienvenida;

    private String descripcion;

    private String link;

    private String token;

}
