package ch.etmles.payroll.Repositories;

import ch.etmles.payroll.Entities.Lot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LotRepository extends JpaRepository<Lot, Long> {
    // Déclaration de la méthode findBySubcategoryId
    List<Lot> findByCategory_Id(Long categoryId);
}
