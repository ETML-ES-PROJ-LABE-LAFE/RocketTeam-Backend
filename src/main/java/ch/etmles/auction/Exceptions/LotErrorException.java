package ch.etmles.auction.Exceptions;

public class LotErrorException extends RuntimeException {
    public LotErrorException(String message) {
        super(message);
    }

    public static class LotNotFoundException extends LotErrorException {
        public LotNotFoundException(Long id) {
            super("Could not find lot " + id);
        }
    }

    public static class InvalidLotException extends LotErrorException {
        public InvalidLotException(String message) {
            super(message);
        }
    }
}
