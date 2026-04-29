package br.com.barberflow.api.services.exception;

public class DomainException extends RuntimeException {

    public DomainException(String msg) {
        super(msg);
    }
}