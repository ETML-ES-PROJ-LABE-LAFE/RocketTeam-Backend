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
            // Création et sauvegarde des catégories
            Category mainCategory1 = categoryRepository.save(new Category("Main Category 1", null));
            Category mainCategory2 = categoryRepository.save(new Category("Main Category 2", null));

            Category subCategory1 = categoryRepository.save(new Category("Subcategory 1", mainCategory1));
            Category subCategory2 = categoryRepository.save(new Category("Subcategory 2", mainCategory1));
            Category subCategory3 = categoryRepository.save(new Category("Subcategory 3", mainCategory2));

            // Création et sauvegarde des lots avec leurs sous-catégories
            Lot lot1 = lotRepository.save(new Lot("Lot description 1", subCategory1, 100.0, 150.0));
            Lot lot2 = lotRepository.save(new Lot("Lot description 2", subCategory1, 200.0, 250.0));
            Lot lot3 = lotRepository.save(new Lot("Lot description 3", subCategory2, 300.0, 350.0));
            Lot lot4 = lotRepository.save(new Lot("Lot description 4", subCategory3, 400.0, 450.0));

            // Vous pouvez ajouter plus de catégories, sous-catégories et lots ici
        };
    }
}
