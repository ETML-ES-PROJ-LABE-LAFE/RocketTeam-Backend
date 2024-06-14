package ch.etmles.auction.Controllers;

import ch.etmles.auction.Entities.Customer;
import ch.etmles.auction.Entities.Enchere;
import ch.etmles.auction.Entities.Lot;
import ch.etmles.auction.Services.NotificationService;
import ch.etmles.auction.Repositories.EnchereRepository;
import ch.etmles.auction.Repositories.LotRepository;
import ch.etmles.auction.Repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Optional;

@RestController
@RequestMapping("/encheres")
public class EnchereController {

    private final EnchereRepository enchereRepository;
    private final LotRepository lotRepository;
    private final CustomerRepository customerRepository;
    private final NotificationService notificationService;

    @Autowired
    public EnchereController(EnchereRepository enchereRepository, LotRepository lotRepository, CustomerRepository customerRepository, NotificationService notificationService) {
        this.enchereRepository = enchereRepository;
        this.lotRepository = lotRepository;
        this.customerRepository = customerRepository;
        this.notificationService = notificationService;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> placeEnchere(@RequestBody Enchere enchere) {
        Optional<Lot> lotOptional = lotRepository.findById(enchere.getLot().getId());
        Optional<Customer> customerOptional = customerRepository.findById(enchere.getCustomer().getId());

        if (lotOptional.isPresent() && customerOptional.isPresent()) {
            Lot lot = lotOptional.get();
            Customer customer = customerOptional.get();

            BigDecimal totalBidAmount = enchereRepository.findAllByCustomerId(customer.getId()).stream()
                    .map(Enchere::getAmount)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            BigDecimal availableBalance = customer.getBalance().subtract(totalBidAmount.add(customer.getReservedBalance()));

            if (enchere.getAmount().compareTo(lot.getHighestBid()) <= 0) {
                return ResponseEntity.badRequest().body(new EnchereErrorException("Votre offre doit être supérieure à l'offre la plus élevée."));
            } else if (totalBidAmount.add(enchere.getAmount()).compareTo(customer.getBalance()) > 0) {
                return ResponseEntity.badRequest().body(new EnchereErrorException("Votre offre dépasse votre solde disponible."));
            } else {
                if (lot.getHighestBidder() != null && !lot.getHighestBidder().getId().equals(customer.getId())) {
                    Customer previousHighestBidder = lot.getHighestBidder();
                    previousHighestBidder.setReservedBalance(previousHighestBidder.getReservedBalance().subtract(lot.getHighestBid()));
                    customerRepository.save(previousHighestBidder);

                    // Add notification for the previous highest bidder
                    String message = "Vous avez été surenchéri sur le lot: " + lot.getDescription();
                    notificationService.addNotification(message, previousHighestBidder, lot, enchere.getAmount());
                }

                lot.setHighestBid(enchere.getAmount());
                lot.setHighestBidder(customer);
                lotRepository.save(lot);

                customer.setReservedBalance(customer.getReservedBalance().add(enchere.getAmount()));
                customerRepository.save(customer);

                Enchere savedEnchere = enchereRepository.save(enchere);
                return ResponseEntity.ok(savedEnchere);
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/total/{customerId}")
    public ResponseEntity<BigDecimal> getTotalBidAmountByCustomer(@PathVariable Long customerId) {
        BigDecimal totalBidAmount = enchereRepository.findAllByCustomerId(customerId)
                .stream()
                .map(Enchere::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return ResponseEntity.ok(totalBidAmount);
    }

    @GetMapping("/release/{customerId}/{lotId}")
    public ResponseEntity<?> releaseReservedBalance(@PathVariable Long customerId, @PathVariable Long lotId) {
        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        Optional<Lot> lotOptional = lotRepository.findById(lotId);

        if (customerOptional.isPresent() && lotOptional.isPresent()) {
            Customer customer = customerOptional.get();
            Lot lot = lotOptional.get();

            if (lot.getHighestBidder() != null && !lot.getHighestBidder().getId().equals(customerId)) {
                BigDecimal reservedAmount = enchereRepository.findAllByCustomerId(customerId).stream()
                        .filter(e -> e.getLot().getId().equals(lotId))
                        .map(Enchere::getAmount)
                        .reduce(BigDecimal.ZERO, BigDecimal::add);
                customer.setReservedBalance(customer.getReservedBalance().subtract(reservedAmount));
                customerRepository.save(customer);
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.badRequest().body(new EnchereErrorException("Impossible de libérer le solde réservé car l'enchère est toujours la plus haute."));
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
