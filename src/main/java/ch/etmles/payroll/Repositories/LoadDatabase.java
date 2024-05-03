package ch.etmles.payroll.Repositories;

import ch.etmles.payroll.Entities.Category;
import ch.etmles.payroll.Entities.Lot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(CategoryRepository categoryRepository, LotRepository lotRepository) {
        return args -> {
            // Créer et sauvegarder des employés


            // Créer et sauvegarder des catégories
            Category mainCategory1 = categoryRepository.save(new Category("Main Category 1", null));
            Category subCategory1 = categoryRepository.save(new Category("Subcategory 1", mainCategory1));
            Category mainCategory2 = categoryRepository.save(new Category("Main Category 2", null));

            // Créer et sauvegarder des lots avec leurs sous-catégories
            Lot lot1 = new Lot("Lot description 1", subCategory1, 100.0, 150.0);
            lotRepository.save(lot1);
            // Ajouter d'autres lots ici avec leurs sous-catégories
        };
    }
}
