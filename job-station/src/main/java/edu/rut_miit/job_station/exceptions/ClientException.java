package edu.rut_miit.job_station.exceptions;

public class ClientException extends RuntimeException {
    public ClientException(String message) {
        super(message);
    }

    public ClientException(Exception base) {
        super(base);
    }

    public static class NotFoundException extends ClientException {
        public NotFoundException() {
            super("Resource not found");
        }

        public NotFoundException(String message) {
            super(message);
        }

        public NotFoundException(Exception exception) {
            super(exception);
        }
    }

    public static class InvalidInputException extends ClientException {
        public InvalidInputException() {
            super("Validation error: invalid input");
        }

        public InvalidInputException(String message) {
            super(message);
        }

        public InvalidInputException(Exception exception) {
            super(exception);
        }
    }

    public static class InvalidStateException extends ClientException {
        public InvalidStateException() {
            super("Invalid state");
        }

        public InvalidStateException(String message) {
            super(message);
        }

        public InvalidStateException(Exception exception) {
            super(exception);
        }
    }

    public static class NotAllowedException extends ClientException {
        public NotAllowedException() {
            super("Action not allowed");
        }

        public NotAllowedException(String message) {
            super(message);
        }

        public NotAllowedException(Exception exception) {
            super(exception);
        }
    }

    public static class ConflictException extends ClientException {
        public ConflictException() {
            super("Conflict");
        }

        public ConflictException(String message) {
            super(message);
        }

        public ConflictException(Exception exception) {
            super(exception);
        }
    }
}
