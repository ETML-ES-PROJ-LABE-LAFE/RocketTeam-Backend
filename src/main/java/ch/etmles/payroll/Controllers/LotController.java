package ch.etmles.payroll.Controllers;

import ch.etmles.payroll.Entities.Lot;
import ch.etmles.payroll.Repositories.LotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lots")
@CrossOrigin(origins = "http://localhost:8081") // Autoriser les requêtes depuis l'URL frontend
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
    @GetMapping("/bySubcategory/{subcategoryId}")
    public List<Lot> getLotsBySubcategory(@PathVariable Long subcategoryId) {
        // Récupérer les lots par sous-catégorie en utilisant la méthode findBySubcategoryId du repository
        return lotRepository.findByCategory_Id(subcategoryId);
    }

    @DeleteMapping("/{id}")
    public void deleteLot(@PathVariable Long id) {
        lotRepository.deleteById(id);
    }
}
