package ru.nsu.demidov.substring;

/**
 * Custom exception class for substring search errors.
 */

public class SubStringFinderException extends Exception {

    /**
     * Constructs a new exception with the specified detail message.
     */

    public SubStringFinderException(String message) {
        super(message);
    }

    /**
     * Constructs a new exception with the specified detail message and cause.
     */

    public SubStringFinderException(String message, Throwable cause) {
        super(message, cause);
    }
}