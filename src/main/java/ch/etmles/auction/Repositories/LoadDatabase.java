package ch.etmles.auction.Repositories;

import ch.etmles.auction.Entities.Category;
import ch.etmles.auction.Entities.Lot;
import ch.etmles.auction.Entities.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(CategoryRepository categoryRepository, LotRepository lotRepository, CustomerRepository customerRepository) {
        return args -> {
            //Création des users
            /*User Admin = userRepository.save(new User("Cpte_Admin", "admin@etml-es.ch"));*/
            Customer Identify1 = customerRepository.save(new Customer("Cpte_Identifie", "identifie@etml-es.ch"));
            Customer Identify2 = customerRepository.save(new Customer("Cpte_Identifie2", "identifie@etml-es.ch"));
            /*User NoIdentify = userRepository.save(new User("Cpte_NonIdentifié", "nonidentifie@etml-es.ch"));*/

            // Création et sauvegarde des catégories
            Category mainCategory1 = categoryRepository.save(new Category("Vêtements", null));
            Category mainCategory2 = categoryRepository.save(new Category("Meubles", null));

            Category subCategory1 = categoryRepository.save(new Category("Hauts", mainCategory1));
            Category subCategory2 = categoryRepository.save(new Category("Bas", mainCategory1));
            Category subCategory3 = categoryRepository.save(new Category("Chambre", mainCategory2));

            // Création et sauvegarde des lots avec leurs sous-catégories
            Lot lot1 = lotRepository.save(new Lot("Pull Gucci", subCategory1, 100.0, 150.0, true, Identify1));
            Lot lot2 = lotRepository.save(new Lot("T-Shirt Ralph Lauren", subCategory1, 200.0, 250.0, true, Identify2));
            Lot lot3 = lotRepository.save(new Lot("Pantalon Jack&Jones", subCategory2, 300.0, 350.0, true, Identify1));
            Lot lot4 = lotRepository.save(new Lot("Lit Cars", subCategory3, 400.0, 450.0, true, Identify2));

        };
    }
}
