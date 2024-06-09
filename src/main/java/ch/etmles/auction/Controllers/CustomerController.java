package ch.etmles.auction.Controllers;

import ch.etmles.auction.Entities.Customer;
import ch.etmles.auction.Repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping
    public List<Customer> getAllUsers() {
        return customerRepository.findAll();
    }

    @PostMapping
    public Customer addUser(@RequestBody Customer user) {
        return customerRepository.save(user);
        //TODO if the user already exists... (unique constraint)
    }

    @GetMapping("/{id}")
    public Customer getUserById(@PathVariable Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }
}
