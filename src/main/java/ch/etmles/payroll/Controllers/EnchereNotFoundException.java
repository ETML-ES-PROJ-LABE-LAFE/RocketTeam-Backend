<!-- EnchereNotFoundException -->
        package ch.etmles.payroll.Controllers;

public class EnchereNotFoundException extends RuntimeException {

    EnchereNotFoundException(Long id) {
        super("Could not find enchere " + id);
    }
}
