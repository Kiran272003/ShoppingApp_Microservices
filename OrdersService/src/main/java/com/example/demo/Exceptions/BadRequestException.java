package com.example.demo.Exceptions;

/**
 * Custom exception for handling bad requests (HTTP 400).
 */
public class BadRequestException extends RuntimeException {

    // Constructor accepting an error message
    public BadRequestException(String message) {
        super(message);
    }
}
