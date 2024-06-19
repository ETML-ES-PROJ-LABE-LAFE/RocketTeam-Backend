package ch.etmles.auction.Repositories;

import ch.etmles.auction.Entities.Lot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LotRepository extends JpaRepository<Lot, Long> {
    List<Lot> findByCategory_Id(Long categoryId);
    List<Lot> findByCustomer_Id(Long customerId);
    List<Lot> findByStatus(String status);
    List<Lot> findByStatusAndCustomer_Id(String status, Long customerId);
    List<Lot> findByHighestBidder_Id(Long customerId);
    List<Lot> findByStatusAndHighestBidder_Id(String status, Long highestBidderId);
    List<Lot> findByHighestBidder_IdAndStatusNot(Long highestBidderId, String status);
}
