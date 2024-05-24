package ch.etmles.auction.Repositories;

import ch.etmles.auction.Entities.Lot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LotRepository extends JpaRepository<Lot, Long> {
    // Déclaration de la méthode findBySubcategoryId
    List<Lot> findByCategory_Id(Long categoryId);
    List<Lot> findByCustomer_Id(Long customerId);
}
