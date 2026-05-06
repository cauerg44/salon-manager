package br.com.beautycore.api.services.exception;

public class DomainException extends RuntimeException {

    public DomainException(String message) {
        super(message);
    }
}
