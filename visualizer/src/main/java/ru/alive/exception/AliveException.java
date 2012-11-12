package ru.alive.exception;

/**
 * @author pvyazankin
 * @since 08.11.12 11:37
 */
public class AliveException extends RuntimeException {
    public AliveException() {
    }

    public AliveException(String message) {
        super(message);
    }

    public AliveException(String message, Throwable cause) {
        super(message, cause);
    }

    public AliveException(Throwable cause) {
        super(cause);
    }
}
