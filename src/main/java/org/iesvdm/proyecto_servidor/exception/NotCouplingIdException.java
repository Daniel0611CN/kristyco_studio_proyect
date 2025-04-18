package org.iesvdm.proyecto_servidor.exception;

public class NotCouplingIdException extends RuntimeException {
    public NotCouplingIdException(Long id1, Long id2, Class<?> entityType) {
        super(String.format("Not coupling id1 = %d with id2 = %d for entity '%s'.", id1, id2, entityType.getSimpleName()));
    }
}
