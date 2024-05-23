package ch.etmles.auction.Repositories;

import ch.etmles.auction.Entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
