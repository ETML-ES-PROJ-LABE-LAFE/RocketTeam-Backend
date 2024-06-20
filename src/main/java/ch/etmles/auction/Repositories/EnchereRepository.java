package ch.etmles.auction.Repositories;

import ch.etmles.auction.Entities.Enchere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnchereRepository extends JpaRepository<Enchere, Long> {
    Optional<Enchere> findTopByLotIdOrderByAmountDesc(Long lotId);
    List<Enchere> findAllByCustomerId(Long customerId);
}
