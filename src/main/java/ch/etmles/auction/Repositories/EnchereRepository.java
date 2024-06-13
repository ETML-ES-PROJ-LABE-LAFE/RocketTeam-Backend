package ch.etmles.auction.Repositories;

import ch.etmles.auction.Entities.Enchere;
import ch.etmles.auction.Entities.Lot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnchereRepository extends JpaRepository<Enchere, Long> {
    List<Enchere> findAllByCustomerId(Long customerId);
    Enchere findTopByLotOrderByAmountDesc(Lot lot);
}
