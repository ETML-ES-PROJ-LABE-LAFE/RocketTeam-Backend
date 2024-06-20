package ch.etmles.auction.Exceptions;

public class NotificationErrorException extends RuntimeException {
    public NotificationErrorException(String message) {
        super(message);
    }
}
