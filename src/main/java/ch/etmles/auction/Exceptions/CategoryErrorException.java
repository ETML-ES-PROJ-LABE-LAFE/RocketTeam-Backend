package ch.etmles.auction.Exceptions;

public class CategoryErrorException extends RuntimeException {
    public CategoryErrorException(String message) {
        super(message);
    }

    public static class CategoryNotFoundException extends CategoryErrorException {
        public CategoryNotFoundException(Long id) {
            super("Could not find category " + id);
        }
    }

    public static class CategoryAlreadyExistsException extends CategoryErrorException {
        public CategoryAlreadyExistsException(Long id) {
            super("Category with id " + id + " already exists");
        }
    }
}
