package ch.etmles.auction.Controllers;

public class LotNotFoundException extends RuntimeException{

    LotNotFoundException(Long id){
        super("Could not find employee " + id);
    }
}
