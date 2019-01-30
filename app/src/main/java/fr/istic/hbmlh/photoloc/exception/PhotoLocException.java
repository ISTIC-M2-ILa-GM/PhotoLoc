package fr.istic.hbmlh.photoloc.exception;

public class PhotoLocException extends RuntimeException {

    public static final String MESSAGE = "%s: %s";

    public PhotoLocException(String message) {
        super(message);
    }

    public PhotoLocException(String message, Throwable cause) {
        super(message, cause);
    }
}
