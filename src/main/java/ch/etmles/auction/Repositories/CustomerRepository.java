package ch.etmles.auction.Repositories;

import ch.etmles.auction.Entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}

