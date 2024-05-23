package ch.etmles.auction.Repositories;

import ch.etmles.auction.Entities.Category;
import ch.etmles.auction.Entities.Lot;
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
            Category mainCategory1 = categoryRepository.save(new Category("Vêtements", null));
            Category mainCategory2 = categoryRepository.save(new Category("Meubles", null));

            Category subCategory1 = categoryRepository.save(new Category("Hauts", mainCategory1));
            Category subCategory2 = categoryRepository.save(new Category("Bas", mainCategory1));
            Category subCategory3 = categoryRepository.save(new Category("Chambre", mainCategory2));

            // Création et sauvegarde des lots avec leurs sous-catégories
            Lot lot1 = lotRepository.save(new Lot("Pull Gucci", subCategory1, 100.0, 150.0));
            Lot lot2 = lotRepository.save(new Lot("T-Shirt Ralph Lauren", subCategory1, 200.0, 250.0));
            Lot lot3 = lotRepository.save(new Lot("Pantalon Jack&Jones", subCategory2, 300.0, 350.0));
            Lot lot4 = lotRepository.save(new Lot("Lit Cars", subCategory3, 400.0, 450.0));

            // Vous pouvez ajouter plus de catégories, sous-catégories et lots ici
        };
    }
}
