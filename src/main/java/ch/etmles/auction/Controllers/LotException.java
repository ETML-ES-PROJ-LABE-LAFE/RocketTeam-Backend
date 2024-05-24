package ch.etmles.auction.Controllers;

public class LotException {

    public static class LotNotFoundException extends RuntimeException {
        public LotNotFoundException(Long id) {
            super("Could not find lot " + id);
        }
    }

    public static class InvalidLotException extends RuntimeException {
        public InvalidLotException(String message) {
            super(message);
        }
    }
}
