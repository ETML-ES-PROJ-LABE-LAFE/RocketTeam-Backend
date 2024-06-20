package ch.etmles.auction.Controllers;

public class EnchereNotFoundException extends RuntimeException {

    EnchereNotFoundException(Long id) {
        super("Could not find enchere " + id);
    }
}
