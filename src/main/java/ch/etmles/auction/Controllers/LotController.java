package ch.etmles.auction.Controllers;

import ch.etmles.auction.Entities.Lot;
import ch.etmles.auction.Entities.Auction;
import ch.etmles.auction.Repositories.LotRepository;
import ch.etmles.auction.Repositories.EnchereRepository;
import ch.etmles.auction.Repositories.CustomerRepository;
import ch.etmles.auction.Services.NotificationService;
import ch.etmles.auction.config.IdUtil;
import ch.etmles.auction.Exceptions.LotErrorException.InvalidLotException;
import ch.etmles.auction.Exceptions.LotErrorException.LotNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/lots")
public class LotController {

    private final LotRepository lotRepository;
    private final EnchereRepository enchereRepository;
    private final CustomerRepository customerRepository;
    private final NotificationService notificationService;

    @Autowired
    public LotController(LotRepository lotRepository, EnchereRepository enchereRepository, CustomerRepository customerRepository, NotificationService notificationService) {
        this.lotRepository = lotRepository;
        this.enchereRepository = enchereRepository;
        this.customerRepository = customerRepository;
        this.notificationService = notificationService;
    }

    @GetMapping
    public List<Lot> getAllLots() {
        return lotRepository.findAll();
    }

    @GetMapping("/customer/{customerId}")
    public List<Lot> getLotsByCustomer(@PathVariable Long customerId) {
        return lotRepository.findByCustomer_Id(customerId);
    }

    @GetMapping("/categories/{categoryId}/lots")
    public List<Lot> getLotsByCategory(@PathVariable Long categoryId, @RequestParam(required = false) Long excludeCustomerId) {
        if (excludeCustomerId != null) {
            return lotRepository.findByCategory_Id(categoryId).stream()
                    .filter(lot -> !lot.getCustomer().getId().equals(excludeCustomerId))
                    .toList();
        } else {
            return lotRepository.findByCategory_Id(categoryId);
        }
    }

    @GetMapping("/status/{status}")
    public List<Lot> getLotsByStatus(@PathVariable String status) {
        return lotRepository.findByStatus(status);
    }

    @GetMapping("/customer/{customerId}/status/{status}")
    public List<Lot> getLotsByCustomerAndStatus(@PathVariable Long customerId, @PathVariable String status) {
        return lotRepository.findByStatusAndCustomer_Id(status, customerId);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public Lot addLot(@RequestBody Lot lot) {
        if (lot.getDescription() == null || lot.getDescription().isEmpty() ||
                lot.getCategory() == null || lot.getInitialPrice() <= 0) {
            throw new InvalidLotException("Invalid lot details");
        }
        return lotRepository.save(lot);
    }

    @GetMapping("/{encodedId}")
    public Lot getLotById(@PathVariable String encodedId) {
        Long id = IdUtil.decodeId(encodedId);
        return lotRepository.findById(id)
                .orElseThrow(() -> new LotNotFoundException(id));
    }

    @PutMapping("/{encodedId}")
    public Lot updateLot(@RequestBody Lot newLot, @PathVariable String encodedId) {
        Long id = IdUtil.decodeId(encodedId);
        return lotRepository.findById(id)
                .map(lot -> {
                    lot.setDescription(newLot.getDescription());
                    lot.setCategory(newLot.getCategory());
                    lot.setInitialPrice(newLot.getInitialPrice());
                    lot.setHighestBid(newLot.getHighestBid());
                    return lotRepository.save(lot);
                })
                .orElseGet(() -> {
                    newLot.setId(id);
                    return lotRepository.save(newLot);
                });
    }

    @DeleteMapping("/{encodedId}")
    public void deleteLot(@PathVariable String encodedId) {
        Long id = IdUtil.decodeId(encodedId);
        Lot lot = lotRepository.findById(id)
                .orElseThrow(() -> new LotNotFoundException(id));
        lotRepository.deleteById(id);
    }

    @PutMapping("/{encodedId}/endAuction")
    public Lot endAuction(@PathVariable String encodedId) {
        Long id = IdUtil.decodeId(encodedId);
        return lotRepository.findById(id)
                .map(lot -> {
                    Optional<Auction> highestBid = enchereRepository.findTopByLotIdOrderByAmountDesc(id);

                    if (highestBid.isPresent()) {
                        lot.setHighestBidder(highestBid.get().getCustomer());
                        lot.setStatus("awaiting payment");

                        // Send notification to the highest bidder
                        String buyerMessage = "Félicitations ! Vous avez remporté l'enchère pour le lot: " + lot.getDescription() + " avec un montant de " + highestBid.get().getAmount() + " €.";
                        notificationService.addNotification(buyerMessage, highestBid.get().getCustomer(), lot, highestBid.get().getAmount());

                        // Send notification to the seller
                        String sellerMessage = "Votre lot " + lot.getDescription() + " a trouvé un acheteur. Montant de l'enchère: " + highestBid.get().getAmount() + " €.";
                        notificationService.addNotification(sellerMessage, lot.getCustomer(), lot, highestBid.get().getAmount());
                    } else {
                        lot.setStatus("inactive");
                        lot.setHighestBidder(null);

                        // Send notification to the seller
                        String sellerMessage = "Votre lot " + lot.getDescription() + " n'a pas trouvé d'acheteur.";
                        notificationService.addNotification(sellerMessage, lot.getCustomer(), lot, null);
                    }

                    return lotRepository.save(lot);
                })
                .orElseThrow(() -> new LotNotFoundException(id));
    }

    @GetMapping("/bid/{customerId}")
    public List<Lot> getLotsBidByCustomer(@PathVariable Long customerId) {
        return lotRepository.findByHighestBidder_Id(customerId);
    }
}
