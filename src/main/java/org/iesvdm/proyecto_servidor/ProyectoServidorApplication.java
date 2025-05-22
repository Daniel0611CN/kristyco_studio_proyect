package org.iesvdm.proyecto_servidor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProyectoServidorApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProyectoServidorApplication.class, args);
    }

//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/greeting-javaconfig").allowedOrigins("http://localhost:9000");
//            }
//        };
//    }

}
