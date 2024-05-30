package ch.etmles.auction.Controllers;

import ch.etmles.auction.Entities.Lot;
import ch.etmles.auction.Repositories.LotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lots")
public class LotController {

    private final LotRepository lotRepository;

    @Autowired
    public LotController(LotRepository lotRepository) {
        this.lotRepository = lotRepository;
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



    @GetMapping("/{id}")
    public Lot getLotById(@PathVariable Long id) {
        return lotRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Lot not found"));
    }

    @PutMapping("/{id}")
    public Lot updateLot(@RequestBody Lot newLot, @PathVariable Long id) {
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

    @DeleteMapping("/{id}")
    public void deleteLot(@PathVariable Long id) {
        lotRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Lot not found"));
        lotRepository.deleteById(id);
    }

    @PutMapping("/{id}/endAuction")
    public Lot endAuction(@PathVariable Long id) {
        return lotRepository.findById(id)
                .map(lot -> {
                    lot.setActive(false); // Mark the lot as inactive
                    return lotRepository.save(lot);
                })
                .orElseThrow(() -> new IllegalArgumentException("Lot not found"));
    }
}