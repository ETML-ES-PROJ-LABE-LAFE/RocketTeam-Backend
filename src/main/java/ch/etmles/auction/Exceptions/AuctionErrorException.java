package ch.etmles.auction.Exceptions;

public class AuctionErrorException extends RuntimeException {
    public AuctionErrorException(String message) {
        super(message);
    }
}
