package br.com.one_community.entities;

public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }
}