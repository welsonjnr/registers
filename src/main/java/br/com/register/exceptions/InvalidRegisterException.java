package br.com.register.exceptions;

public class InvalidRegisterException extends RuntimeException {
    public InvalidRegisterException(String msg) {
        super(msg);
    }
}
