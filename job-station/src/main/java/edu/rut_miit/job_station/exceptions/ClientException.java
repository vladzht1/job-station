package edu.rut_miit.job_station.exceptions;

public class ClientException extends RuntimeException {

    public static class NotFoundException extends RuntimeException {
        public NotFoundException(String message) {
            super(message);
        }

        public NotFoundException(Exception exception) {
            super(exception);
        }
    }

    public static class InvalidInputException extends RuntimeException {
        public InvalidInputException(String message) {
            super(message);
        }

        public InvalidInputException(Exception exception) {
            super(exception);
        }
    }

    public static class InvalidStateException extends RuntimeException {
        public InvalidStateException(String message) {
            super(message);
        }

        public InvalidStateException(Exception exception) {
            super(exception);
        }
    }

    public static class NotAllowedException extends RuntimeException {
        public NotAllowedException(String message) {
            super(message);
        }

        public NotAllowedException(Exception exception) {
            super(exception);
        }
    }
}
