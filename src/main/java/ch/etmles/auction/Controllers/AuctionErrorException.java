package ch.etmles.auction.Controllers;

public class EnchereErrorException extends RuntimeException {
    public EnchereErrorException(String message) {
        super(message);
    }
}
