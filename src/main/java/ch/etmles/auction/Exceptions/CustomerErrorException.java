package ch.etmles.auction.Exceptions;

public class CustomerErrorException extends RuntimeException {
    public CustomerErrorException(String message) {
        super(message);
    }

    public static class CustomerNotFoundException extends CustomerErrorException {
        public CustomerNotFoundException(Long id) {
            super("Could not find customer " + id);
        }
    }

    public static class CustomerAlreadyExistsException extends CustomerErrorException {
        public CustomerAlreadyExistsException(Long id) {
            super("Customer with id " + id + " already exists");
        }
    }
}
