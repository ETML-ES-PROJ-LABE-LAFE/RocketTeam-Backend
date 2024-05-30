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
            Category mainCategory3 = categoryRepository.save(new Category("Électronique", null));

            Category subCategory1 = categoryRepository.save(new Category("Hauts", mainCategory1));
            Category subCategory2 = categoryRepository.save(new Category("Bas", mainCategory1));
            Category subCategory3 = categoryRepository.save(new Category("Chambre", mainCategory2));
            Category subCategory4 = categoryRepository.save(new Category("Salon", mainCategory2));
            Category subCategory5 = categoryRepository.save(new Category("Téléviseurs", mainCategory3)); // Sous-catégorie pour l'électronique
            Category subCategory6 = categoryRepository.save(new Category("Smartphones", mainCategory3)); // Sous-catégorie pour l'électronique
            Category subCategory7 = categoryRepository.save(new Category("Ordinateurs", mainCategory3)); // Sous-catégorie pour l'électronique
            Category subCategory8 = categoryRepository.save(new Category("Accessoires", mainCategory3));
            Category subCategory9 = categoryRepository.save(new Category("Appareils photo", mainCategory3));
            // Création et sauvegarde des lots avec leurs sous-catégories
            Lot lot1 = lotRepository.save(new Lot("Pull Gucci", "../RocketTeam-Frontend/src/assets/img_lot/pull_gucci.jpg", subCategory1, 100.0, 150.0, true, Identify1));
            Lot lot2 = lotRepository.save(new Lot("T-Shirt Ralph Lauren", "../RocketTeam-Frontend/src/assets/img_lot/t-shirt_ralphlauren.jpg", subCategory1, 200.0, 250.0, true, Identify2));
            Lot lot3 = lotRepository.save(new Lot("Pantalon Jack&Jones", "../RocketTeam-Frontend/src/assets/img_lot/pantalon_jackandjones.jpg", subCategory2, 300.0, 350.0, true, Identify1));
            Lot lot4 = lotRepository.save(new Lot("Lit Cars", "../RocketTeam-Frontend/src/assets/img_lot/lit_cars.jpg", subCategory3, 400.0, 450.0, true, Identify2));
            Lot lot5 = lotRepository.save(new Lot("Canapé en cuir", "../RocketTeam-Frontend/src/assets/img_lot/canape_cuir.jpg", subCategory4, 500.0, 550.0, true, Identify1));
            Lot lot6 = lotRepository.save(new Lot("Table basse moderne", "../RocketTeam-Frontend/src/assets/img_lot/table_bassemoderne.jpg", subCategory4, 600.0, 650.0, true, Identify2));
            Lot lot7 = lotRepository.save(new Lot("Samsung 4K TV", "../RocketTeam-Frontend/src/assets/img_lot/Samsung_tele.jpg", subCategory5, 700.0, 750.0, true, Identify1));
            Lot lot8 = lotRepository.save(new Lot("iPhone 12", "../RocketTeam-Frontend/src/assets/img_lot/iphone12.jpg", subCategory6, 800.0, 850.0, true, Identify2));
            Lot lot9 = lotRepository.save(new Lot("Asus Zenbook Pro", "../RocketTeam-Frontend/src/assets/img_lot/Asus_Zenbook.jpg", subCategory7, 900.0, 950.0, true, Identify1));
            Lot lot10 = lotRepository.save(new Lot("Chaise de bureau", "../RocketTeam-Frontend/src/assets/img_lot/chaise_bureau.jpg", subCategory4, 1000.0, 1050.0, true, Identify2));
            Lot lot11 = lotRepository.save(new Lot("Sony 65 TV", "../RocketTeam-Frontend/src/assets/img_lot/sony_65.jpg", subCategory5, 1100.0, 1150.0, true, Identify1));
            Lot lot12 = lotRepository.save(new Lot("HP Envy x360", "../RocketTeam-Frontend/src/assets/img_lot/HP_Envy.jpg", subCategory7, 1200.0, 1250.0, true, Identify2));
            Lot lot13 = lotRepository.save(new Lot("Manteau en laine", "../RocketTeam-Frontend/src/assets/img_lot/manteau_en_laine.jpg", subCategory1, 1300.0, 1350.0, true, Identify1));
            Lot lot14 = lotRepository.save(new Lot("Jeans Levi's", "../RocketTeam-Frontend/src/assets/img_lot/jeans_levis.jpg", subCategory2, 1400.0, 1450.0, true, Identify2));
            Lot lot15 = lotRepository.save(new Lot("Lit enfant", "../RocketTeam-Frontend/src/assets/img_lot/lit_enfant.jpg", subCategory3, 1500.0, 1550.0, true, Identify1));
            Lot lot16 = lotRepository.save(new Lot("Câble HDMI", "../RocketTeam-Frontend/src/assets/img_lot/cable_hdmi.jpg", subCategory8, 10.0, 15.0, true, Identify1));
            Lot lot17 = lotRepository.save(new Lot("Chargeur USB-C", "../RocketTeam-Frontend/src/assets/img_lot/USB_C.jpg", subCategory8, 5.0, 10.0, true, Identify2));
            Lot lot18 = lotRepository.save(new Lot("Canon EOS 80D", "../RocketTeam-Frontend/src/assets/img_lot/canon_eos.jpg", subCategory9, 900.0, 950.0, true, Identify1));
            Lot lot19 = lotRepository.save(new Lot("Nikon D750", "../RocketTeam-Frontend/src/assets/img_lot/nikon_d750.jpg", subCategory9, 1000.0, 1050.0, true, Identify2));
            Lot lot20 = lotRepository.save(new Lot("Étui pour iPhone", "../RocketTeam-Frontend/src/assets/img_lot/etui_telephone.jpg", subCategory8, 15.0, 20.0, true, Identify1));
            Lot lot21 = lotRepository.save(new Lot("Objectif Sigma 35mm", "../RocketTeam-Frontend/src/assets/img_lot/objectif_sigma.jpg", subCategory9, 600.0, 650.0, true, Identify2));
            Lot lot22 = lotRepository.save(new Lot("Sacoche pour appareil photo", "../RocketTeam-Frontend/src/assets/img_lot/sacoche_appareil_photo.jpg", subCategory8, 25.0, 30.0, true, Identify1));
            Lot lot23 = lotRepository.save(new Lot("Trépied Manfrotto", "../RocketTeam-Frontend/src/assets/img_lot/trepied_manfrotto.jpg", subCategory9, 150.0, 200.0, true, Identify2));
            Lot lot24 = lotRepository.save(new Lot("Écouteurs sans fil", "../RocketTeam-Frontend/src/assets/img_lot/ecouteur_sansfil.jpg", subCategory8, 50.0, 60.0, true, Identify1));
            Lot lot25 = lotRepository.save(new Lot("Stabilisateur DJI Ronin-S", "../RocketTeam-Frontend/src/assets/img_lot/stabilisateur_ronin.jpg", subCategory9, 400.0, 450.0, true, Identify2));
            Lot lot26 = lotRepository.save(new Lot("GoPro Hero 9", "../RocketTeam-Frontend/src/assets/img_lot/gopro_9.jpg",subCategory9, 350.0, 400.0, true, Identify1));
            Lot lot27 = lotRepository.save(new Lot("Étui pour GoPro", "../RocketTeam-Frontend/src/assets/img_lot/etui_gopro.jpg", subCategory8, 20.0, 25.0, true, Identify2));
            Lot lot28 = lotRepository.save(new Lot("Écouteurs Apple AirPods", "../RocketTeam-Frontend/src/assets/img_lot/AirPods.jpg", subCategory8, 150.0, 180.0, true, Identify1));
            Lot lot29 = lotRepository.save(new Lot("Carte mémoire SanDisk", "../RocketTeam-Frontend/src/assets/img_lot/memorycard_sandisk.jpg", subCategory8, 30.0, 35.0, true, Identify2));
            Lot lot30 = lotRepository.save(new Lot("Gimbal pour smartphone", "../RocketTeam-Frontend/src/assets/img_lot/gimbal_smartphone.jpg", subCategory9, 100.0, 120.0, true, Identify1));
            Lot lot31 = lotRepository.save(new Lot("Souris sans fil Logitech", "../RocketTeam-Frontend/src/assets/img_lot/souris_logitech.jpg", subCategory8, 25.0, 30.0, true, Identify1));
            Lot lot32 = lotRepository.save(new Lot("Clé USB 64 Go", "../RocketTeam-Frontend/src/assets/img_lot/usb_64.jpg", subCategory8, 20.0, 25.0, true, Identify2));
            Lot lot33 = lotRepository.save(new Lot("Batterie externe Anker 10000 mAh", "../RocketTeam-Frontend/src/assets/img_lot/batterie_anker.jpg", subCategory8, 35.0, 40.0, true, Identify1));
            Lot lot34 = lotRepository.save(new Lot("Câble Lightning Apple", "../RocketTeam-Frontend/src/assets/img_lot/lightning_apple.jpg", subCategory8, 15.0, 20.0, true, Identify2));
        };
    }
}
