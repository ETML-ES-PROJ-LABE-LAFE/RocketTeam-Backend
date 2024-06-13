package ch.etmles.auction.Controllers;

import ch.etmles.auction.Entities.Customer;
import ch.etmles.auction.Repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    }

    @GetMapping("/{id}")
    public Customer getUserById(@PathVariable Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateUserBalance(@PathVariable Long id, @RequestBody Customer updatedCustomer) {
        return customerRepository.findById(id).map(customer -> {
            customer.setBalance(updatedCustomer.getBalance());
            customerRepository.save(customer);
            return ResponseEntity.ok(customer);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
