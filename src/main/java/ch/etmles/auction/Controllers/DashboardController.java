package ch.etmles.auction.Controllers;

import ch.etmles.auction.Entities.Lot;
import ch.etmles.auction.Entities.Customer;
import ch.etmles.auction.Entities.Notification;
import ch.etmles.auction.Repositories.LotRepository;
import ch.etmles.auction.Repositories.CustomerRepository;
import ch.etmles.auction.Repositories.NotificationRepository;
import ch.etmles.auction.config.IdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    private final LotRepository lotRepository;
    private final CustomerRepository customerRepository;
    private final NotificationRepository notificationRepository;

    @Autowired
    public DashboardController(LotRepository lotRepository, CustomerRepository customerRepository, NotificationRepository notificationRepository) {
        this.lotRepository = lotRepository;
        this.customerRepository = customerRepository;
        this.notificationRepository = notificationRepository;
    }

    @GetMapping("/lotsForSale/{customerId}")
    public List<Lot> getLotsForSale(@PathVariable Long customerId) {
        return lotRepository.findByStatusAndCustomer_Id("active", customerId);
    }

    @GetMapping("/lotsSold/{customerId}")
    public List<Lot> getLotsSold(@PathVariable Long customerId) {
        return lotRepository.findByStatusAndHighestBidder_Id("paid", customerId);
    }

    @GetMapping("/lotsAffected/{customerId}")
    public List<Lot> getLotsAffected(@PathVariable Long customerId) {
        return lotRepository.findByStatusAndHighestBidder_Id("awaiting payment", customerId);
    }

    @GetMapping("/lotsBid/{customerId}")
    public List<Lot> getLotsBid(@PathVariable Long customerId) {
        return lotRepository.findByHighestBidder_IdAndStatus(customerId, "active");
    }

    @GetMapping("/lotsOwned/{customerId}")
    public List<Lot> getLotsOwned(@PathVariable Long customerId) {
        return lotRepository.findByCustomer_Id(customerId);
    }

    @GetMapping("/lotsVendues/{customerId}")
    public List<Lot> getLotsVendues(@PathVariable Long customerId) {
        return lotRepository.findByStatusAndCustomer_Id("paid", customerId);
    }

    @PutMapping("/{encodedId}/confirmPayment")
    public ResponseEntity<String> confirmPayment(@PathVariable String encodedId, @RequestParam Long userId) {
        Long lotId = IdUtil.decodeId(encodedId);
        Optional<Lot> lotOpt = lotRepository.findById(lotId);
        Optional<Customer> buyerOpt = customerRepository.findById(userId);

        if (lotOpt.isEmpty() || buyerOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Lot or user not found");
        }

        Lot lot = lotOpt.get();
        Customer buyer = buyerOpt.get();

        if (!"awaiting payment".equals(lot.getStatus()) || buyer.getBalance().compareTo(lot.getHighestBid()) < 0) {
            return ResponseEntity.badRequest().body("Invalid transaction");
        }

        Customer seller = lot.getCustomer();
        seller.setBalance(seller.getBalance().add(lot.getHighestBid()));
        buyer.setBalance(buyer.getBalance().subtract(lot.getHighestBid()));
        lot.setStatus("paid");

        customerRepository.save(buyer);
        customerRepository.save(seller);
        lotRepository.save(lot);

        sendNotificationToSeller(lot, seller);

        return ResponseEntity.ok("Payment confirmed");
    }

    private void sendNotificationToSeller(Lot lot, Customer seller) {
        Notification notification = new Notification();
        notification.setMessage("Le lot " + lot.getDescription() + " a été payé. Montant: " + lot.getHighestBid() + " €");
        notification.setUser(seller); // Le vendeur
        notification.setLot(lot);
        notification.setBidAmount(lot.getHighestBid());
        notificationRepository.save(notification);
    }
}
