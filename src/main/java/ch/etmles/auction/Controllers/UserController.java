package ch.etmles.auction.Controllers;

import ch.etmles.auction.Entities.Customer;
import ch.etmles.auction.Repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final CustomerRepository customerRepository;

    @Autowired
    public UserController(CustomerRepository customerRepository) {
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
    public Customer updateUser(@RequestBody Customer newUser, @PathVariable Long id) {
        return customerRepository.findById(id)
                .map(user -> {
                    user.setCustomername(newUser.getCustomername());
                    user.setEmail(newUser.getEmail());
                    return customerRepository.save(user);
                })
                .orElseGet(() -> {
                    newUser.setId(id);
                    return customerRepository.save(newUser);
                });
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        customerRepository.deleteById(id);
    }
}
