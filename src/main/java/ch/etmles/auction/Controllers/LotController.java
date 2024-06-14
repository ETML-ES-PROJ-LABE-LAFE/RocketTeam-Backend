package ch.etmles.auction.Controllers;

import ch.etmles.auction.Entities.Lot;
import ch.etmles.auction.Entities.Enchere;
import ch.etmles.auction.Repositories.LotRepository;
import ch.etmles.auction.Repositories.EnchereRepository;
import ch.etmles.auction.config.IdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/lots")
public class LotController {

    private final LotRepository lotRepository;
    private final EnchereRepository enchereRepository;

    @Autowired
    public LotController(LotRepository lotRepository, EnchereRepository enchereRepository) {
        this.lotRepository = lotRepository;
        this.enchereRepository = enchereRepository;
    }

    @GetMapping
    public List<Lot> getAllLots() {
        return lotRepository.findAll();
    }

    @GetMapping("/customer/{customerId}")
    public List<Lot> getLotsByCustomer(@PathVariable Long customerId) {
        return lotRepository.findByCustomer_Id(customerId);
    }

    @GetMapping("/categories/{subcategoryId}/lots")
    public List<Lot> getLotsBySubcategory(@PathVariable Long subcategoryId) {
        return lotRepository.findByCategory_Id(subcategoryId);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public Lot addLot(@RequestBody Lot lot) {
        if (lot.getDescription() == null || lot.getDescription().isEmpty() ||
                lot.getCategory() == null || lot.getInitialPrice() <= 0) {
            throw new IllegalArgumentException("Invalid lot details");
        }
        return lotRepository.save(lot);
    }

    @GetMapping("/{encodedId}")
    public Lot getLotById(@PathVariable String encodedId) {
        Long id = IdUtil.decodeId(encodedId);
        return lotRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Lot not found"));
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
        lotRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Lot not found"));
        lotRepository.deleteById(id);
    }

    @PutMapping("/{encodedId}/endAuction")
    public Lot endAuction(@PathVariable String encodedId) {
        Long id = IdUtil.decodeId(encodedId);
        return lotRepository.findById(id)
                .map(lot -> {
                    // Fetch the highest bid for the lot
                    Optional<Enchere> highestBid = enchereRepository.findTopByLotIdOrderByAmountDesc(id);

                    if (highestBid.isPresent()) {
                        // Set the highest bidder if there was a bid
                        lot.setHighestBidder(highestBid.get().getCustomer());
                        lot.setStatus("AWAITING_PAYMENT"); // Set status to awaiting payment
                    } else {
                        // No bids, mark as inactive
                        lot.setStatus("INACTIVE"); // Set status to inactive
                        lot.setHighestBidder(null);
                    }

                    return lotRepository.save(lot);
                })
                .orElseThrow(() -> new IllegalArgumentException("Lot not found"));
    }

    @GetMapping("/pending")
    public List<Lot> getPendingLots() {
        return lotRepository.findByStatusAndHighestBidderIsNotNull("AWAITING_PAYMENT");
    }

    @GetMapping("/bid/{customerId}")
    public List<Lot> getLotsBidByCustomer(@PathVariable Long customerId) {
        return lotRepository.findByHighestBidder_Id(customerId);
    }
}
