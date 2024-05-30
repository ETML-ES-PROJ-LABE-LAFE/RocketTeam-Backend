package ch.etmles.auction.Controllers;

import ch.etmles.auction.Entities.Enchere;
import ch.etmles.auction.Entities.Lot;
import ch.etmles.auction.Repositories.EnchereRepository;
import ch.etmles.auction.Repositories.LotRepository;
import ch.etmles.auction.config.IdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/encheres")
public class EnchereController {

    private final EnchereRepository enchereRepository;
    private final LotRepository lotRepository;

    @Autowired
    public EnchereController(EnchereRepository enchereRepository, LotRepository lotRepository) {
        this.enchereRepository = enchereRepository;
        this.lotRepository = lotRepository;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Enchere> placeEnchere(@RequestBody Enchere enchere) {
        Optional<Lot> lotOptional = lotRepository.findById(enchere.getLot().getId());

        if (lotOptional.isPresent()) {
            Lot lot = lotOptional.get();
            if (enchere.getAmount() > lot.getHighestBid()) {
                lot.setHighestBid(enchere.getAmount());
                lotRepository.save(lot); // Sauvegarde du lot mis à jour
                Enchere savedEnchere = enchereRepository.save(enchere); // Sauvegarde de l'enchère
                return ResponseEntity.ok(savedEnchere);
            } else {
                return ResponseEntity.badRequest().build(); // Offre trop basse
            }
        } else {
            return ResponseEntity.notFound().build(); // Lot non trouvé
        }
    }
}
