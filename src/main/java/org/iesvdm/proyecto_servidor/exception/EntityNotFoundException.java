package org.iesvdm.proyecto_servidor.exception;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(Long id, Class<?> entityType) {
        super(String.format("Not found Entity '%s' with Id: %d", entityType.getSimpleName(), id));
    }
}
