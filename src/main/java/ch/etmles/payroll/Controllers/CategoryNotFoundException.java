package ch.etmles.payroll.Controllers;

public class CategoryNotFoundException extends RuntimeException{

    CategoryNotFoundException(Long id){
        super("Could not find employee " + id);
    }
}
