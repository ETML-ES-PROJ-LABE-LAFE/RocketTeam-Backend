package ch.etmles.auction.Repositories;

import ch.etmles.auction.Entities.Auction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnchereRepository extends JpaRepository<Auction, Long> {
    Optional<Auction> findTopByLotIdOrderByAmountDesc(Long lotId);
    List<Auction> findAllByCustomerId(Long customerId);
}
