package ch.etmles.auction.Controllers;

import ch.etmles.auction.Entities.Lot;
import ch.etmles.auction.Repositories.LotRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping
    public Lot addLot(@RequestBody Lot lot) {
        return lotRepository.save(lot);
    }

    @GetMapping("/{id}")
    public Lot getLotById(@PathVariable Long id) {
        return lotRepository.findById(id)
                .orElseThrow(() -> new LotNotFoundException(id));
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

    // Endpoint pour récupérer les lots par sous-catégorie
    @GetMapping("/categories/{subcategoryId}/lots")
    public List<Lot> getLotsBySubcategory(@PathVariable Long subcategoryId) {
        return lotRepository.findByCategory_Id(subcategoryId);
    }

    @DeleteMapping("/{id}")
    public void deleteLot(@PathVariable Long id) {
        lotRepository.deleteById(id);
    }


    @PutMapping("/{id}/placeBid")
    public Lot placeBid(@PathVariable("id") Long id, @RequestParam("bidAmount") double bidAmount) {
        return lotRepository.findById(id).map(lot -> {
            if (bidAmount > lot.getHighestBid()) {
                lot.setHighestBid(bidAmount);
                return lotRepository.save(lot);
            } else {
                throw new IllegalArgumentException("Bid amount must be higher than current highest bid");
            }
        }).orElseThrow(() -> new LotNotFoundException(id));
    }


    @DeleteMapping("/{id}/remove")
    public void removeLot(@PathVariable Long id) {
        lotRepository.deleteById(id);
    }

    @GetMapping("/customer/{customerId}")
    public List<Lot> getLotsByUser(@PathVariable Long customerId) {
        return lotRepository.findByCustomer_Id(customerId);
    }
}
