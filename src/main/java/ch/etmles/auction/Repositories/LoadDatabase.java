package ch.etmles.auction.Repositories;

import ch.etmles.auction.Entities.Category;
import ch.etmles.auction.Entities.Enchere;
import ch.etmles.auction.Entities.Lot;
import ch.etmles.auction.Entities.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Value("${s3.bucket.url}")
    private String s3BucketUrl;

    @Bean
    CommandLineRunner initDatabase(CategoryRepository categoryRepository, LotRepository lotRepository, CustomerRepository customerRepository, EnchereRepository enchereRepository) {
        return args -> {
            //Création des users
            /*User Admin = userRepository.save(new User("Cpte_Admin", "admin@etml-es.ch"));*/
            Customer Identify1 = customerRepository.save(new Customer("Nicolas", "nicolas@etml-es.ch", new BigDecimal("1000.0")));
            Customer Identify2 = customerRepository.save(new Customer("Evan", "evan@etml-es.ch", new BigDecimal("800.0")));
            Customer Identify3 = customerRepository.save(new Customer("Bruno", "bruno@etml-es.ch", new BigDecimal("3000.0")));

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
            Lot lot1 = lotRepository.save(new Lot("Pull Gucci",s3BucketUrl + "pull_gucci.jpg", subCategory1, 100.0, 150.0, "active", Identify1));
            Lot lot2 = lotRepository.save(new Lot("T-Shirt Ralph Lauren", s3BucketUrl + "t-shirt_ralphlauren.jpg", subCategory1, 200.0, 250.0, "active", Identify2));
            Lot lot3 = lotRepository.save(new Lot("Pantalon Jack&Jones", s3BucketUrl + "pantalon_jackandjones.jpg", subCategory2, 300.0, 350.0, "active", Identify1));
            Lot lot4 = lotRepository.save(new Lot("Lit Cars", s3BucketUrl + "lit_cars.jpg", subCategory3, 400.0, 450.0, "active", Identify2));
            Lot lot5 = lotRepository.save(new Lot("Canapé en cuir", s3BucketUrl + "canape_cuir.jpg", subCategory4, 500.0, 550.0, "active", Identify1));
            Lot lot6 = lotRepository.save(new Lot("Table basse moderne", s3BucketUrl + "table_bassemoderne.jpg", subCategory4, 600.0, 650.0, "active", Identify2));
            Lot lot7 = lotRepository.save(new Lot("Samsung 4K TV", s3BucketUrl + "Samsung_tele.jpg", subCategory5, 700.0, 750.0, "active", Identify1));
            Lot lot8 = lotRepository.save(new Lot("iPhone 12", s3BucketUrl + "iphone12.jpg", subCategory6, 800.0, 850.0, "active", Identify2));
            Lot lot9 = lotRepository.save(new Lot("Asus Zenbook Pro", s3BucketUrl + "Asus_Zenbook.jpg", subCategory7, 900.0, 950.0, "active", Identify3));
            Lot lot10 = lotRepository.save(new Lot("Chaise de bureau", s3BucketUrl + "chaise_bureau.jpg", subCategory4, 1000.0, 1050.0, "active", Identify2));
            Lot lot11 = lotRepository.save(new Lot("Sony 65 TV", s3BucketUrl + "sony_65.jpg", subCategory5, 1100.0, 1150.0, "active", Identify1));
            Lot lot12 = lotRepository.save(new Lot("HP Envy x360", s3BucketUrl + "HP_Envy.jpg", subCategory7, 1200.0, 1250.0, "active", Identify2));
            Lot lot13 = lotRepository.save(new Lot("Manteau en laine", s3BucketUrl + "manteau_en_laine.jpg", subCategory1, 1300.0, 1350.0, "active", Identify1));
            Lot lot14 = lotRepository.save(new Lot("Jeans Levi's", s3BucketUrl + "jeans_levis.jpg", subCategory2, 1400.0, 1450.0, "active", Identify2));
            Lot lot15 = lotRepository.save(new Lot("Lit enfant", s3BucketUrl + "lit_enfant.jpg", subCategory3, 1500.0, 1550.0, "active", Identify3));
            Lot lot16 = lotRepository.save(new Lot("Câble HDMI", s3BucketUrl + "cable_hdmi.jpg", subCategory8, 10.0, 15.0, "active", Identify1));
            Lot lot17 = lotRepository.save(new Lot("Chargeur USB-C", s3BucketUrl + "USB_C.jpg", subCategory8, 5.0, 10.0, "active", Identify3));
            Lot lot18 = lotRepository.save(new Lot("Canon EOS 80D", s3BucketUrl + "canon_eos.jpg", subCategory9, 900.0, 950.0, "active", Identify1));
            Lot lot19 = lotRepository.save(new Lot("Nikon D750", s3BucketUrl + "nikon_d750.jpg", subCategory9, 1000.0, 1050.0, "active", Identify2));
            Lot lot20 = lotRepository.save(new Lot("Étui pour iPhone", s3BucketUrl + "etui_telephone.jpg", subCategory8, 15.0, 20.0, "active", Identify1));
            Lot lot21 = lotRepository.save(new Lot("Objectif Sigma 35mm", s3BucketUrl + "objectif_sigma.jpg", subCategory9, 600.0, 650.0, "active", Identify3));
            Lot lot22 = lotRepository.save(new Lot("Sacoche pour appareil photo", s3BucketUrl + "sacoche_appareil_photo.jpg", subCategory8, 25.0, 30.0, "active", Identify1));
            Lot lot23 = lotRepository.save(new Lot("Trépied Manfrotto", s3BucketUrl + "trepied_manfrotto.jpg", subCategory9, 150.0, 200.0, "active", Identify3));
            Lot lot24 = lotRepository.save(new Lot("Écouteurs sans fil", s3BucketUrl + "ecouteur_sansfil.jpg", subCategory8, 50.0, 60.0, "active", Identify1));
            Lot lot25 = lotRepository.save(new Lot("Stabilisateur DJI Ronin-S", s3BucketUrl + "stabilisateur_ronin.jpg", subCategory9, 400.0, 450.0, "active", Identify2));
            Lot lot26 = lotRepository.save(new Lot("GoPro Hero 9", s3BucketUrl + "gopro_9.jpg",subCategory9, 350.0, 400.0, "active", Identify3));
            Lot lot27 = lotRepository.save(new Lot("Étui pour GoPro", s3BucketUrl + "etui_gopro.jpg", subCategory8, 20.0, 25.0, "active", Identify2));
            Lot lot28 = lotRepository.save(new Lot("Écouteurs Apple AirPods", s3BucketUrl + "AirPods.jpg", subCategory8, 150.0, 180.0, "active", Identify3));
            Lot lot29 = lotRepository.save(new Lot("Carte mémoire SanDisk", s3BucketUrl + "memorycard_sandisk.jpg", subCategory8, 30.0, 35.0, "active", Identify2));
            Lot lot30 = lotRepository.save(new Lot("Gimbal pour smartphone", s3BucketUrl + "gimbal_smartphone.jpg", subCategory9, 100.0, 120.0, "active", Identify1));
            Lot lot31 = lotRepository.save(new Lot("Souris sans fil Logitech", s3BucketUrl + "souris_logitech.jpg", subCategory8, 25.0, 30.0, "active", Identify1));
            Lot lot32 = lotRepository.save(new Lot("Clé USB 64 Go", s3BucketUrl + "usb_64.jpg", subCategory8, 20.0, 25.0, "active", Identify3));
            Lot lot33 = lotRepository.save(new Lot("Batterie externe Anker 10000 mAh", s3BucketUrl + "batterie_anker.jpg", subCategory8, 35.0, 40.0, "active", Identify1));
            Lot lot34 = lotRepository.save(new Lot("Câble Lightning Apple", s3BucketUrl + "lightning_apple.jpg", subCategory8, 15.0, 20.0, "active", Identify2));

            enchereRepository.save(new Enchere(new BigDecimal("150.0"), LocalDateTime.now(), lot1, Identify2));
            enchereRepository.save(new Enchere(new BigDecimal("200.0"), LocalDateTime.now(), lot1, Identify3));
            enchereRepository.save(new Enchere(new BigDecimal("250.0"), LocalDateTime.now().minusDays(1), lot1, Identify2));

            enchereRepository.save(new Enchere(new BigDecimal("220.0"), LocalDateTime.now(), lot2, Identify1));
            enchereRepository.save(new Enchere(new BigDecimal("230.0"), LocalDateTime.now().minusDays(1), lot2, Identify3));
            enchereRepository.save(new Enchere(new BigDecimal("240.0"), LocalDateTime.now().minusDays(2), lot2, Identify1));

            enchereRepository.save(new Enchere(new BigDecimal("330.0"), LocalDateTime.now(), lot3, Identify3));
            enchereRepository.save(new Enchere(new BigDecimal("340.0"), LocalDateTime.now().minusDays(1), lot3, Identify2));
            enchereRepository.save(new Enchere(new BigDecimal("350.0"), LocalDateTime.now().minusDays(2), lot3, Identify1));

            enchereRepository.save(new Enchere(new BigDecimal("430.0"), LocalDateTime.now(), lot4, Identify1));
            enchereRepository.save(new Enchere(new BigDecimal("440.0"), LocalDateTime.now().minusDays(1), lot4, Identify2));
            enchereRepository.save(new Enchere(new BigDecimal("450.0"), LocalDateTime.now().minusDays(2), lot4, Identify3));

            enchereRepository.save(new Enchere(new BigDecimal("530.0"), LocalDateTime.now(), lot5, Identify3));
            enchereRepository.save(new Enchere(new BigDecimal("540.0"), LocalDateTime.now().minusDays(1), lot5, Identify2));
            enchereRepository.save(new Enchere(new BigDecimal("550.0"), LocalDateTime.now().minusDays(2), lot5, Identify1));

            enchereRepository.save(new Enchere(new BigDecimal("630.0"), LocalDateTime.now(), lot6, Identify1));
            enchereRepository.save(new Enchere(new BigDecimal("640.0"), LocalDateTime.now().minusDays(1), lot6, Identify3));
            enchereRepository.save(new Enchere(new BigDecimal("650.0"), LocalDateTime.now().minusDays(2), lot6, Identify2));

            enchereRepository.save(new Enchere(new BigDecimal("730.0"), LocalDateTime.now(), lot7, Identify2));
            enchereRepository.save(new Enchere(new BigDecimal("740.0"), LocalDateTime.now().minusDays(1), lot7, Identify1));
            enchereRepository.save(new Enchere(new BigDecimal("750.0"), LocalDateTime.now().minusDays(2), lot7, Identify3));

            enchereRepository.save(new Enchere(new BigDecimal("830.0"), LocalDateTime.now(), lot8, Identify3));
            enchereRepository.save(new Enchere(new BigDecimal("840.0"), LocalDateTime.now().minusDays(1), lot8, Identify2));
            enchereRepository.save(new Enchere(new BigDecimal("850.0"), LocalDateTime.now().minusDays(2), lot8, Identify1));

            enchereRepository.save(new Enchere(new BigDecimal("930.0"), LocalDateTime.now(), lot9, Identify1));
            enchereRepository.save(new Enchere(new BigDecimal("940.0"), LocalDateTime.now().minusDays(1), lot9, Identify3));
            enchereRepository.save(new Enchere(new BigDecimal("950.0"), LocalDateTime.now().minusDays(2), lot9, Identify2));

            enchereRepository.save(new Enchere(new BigDecimal("1030.0"), LocalDateTime.now(), lot10, Identify3));
            enchereRepository.save(new Enchere(new BigDecimal("1040.0"), LocalDateTime.now().minusDays(1), lot10, Identify1));
            enchereRepository.save(new Enchere(new BigDecimal("1050.0"), LocalDateTime.now().minusDays(2), lot10, Identify2));

            enchereRepository.save(new Enchere(new BigDecimal("1130.0"), LocalDateTime.now(), lot11, Identify2));
            enchereRepository.save(new Enchere(new BigDecimal("1140.0"), LocalDateTime.now().minusDays(1), lot11, Identify3));
            enchereRepository.save(new Enchere(new BigDecimal("1150.0"), LocalDateTime.now().minusDays(2), lot11, Identify1));

            enchereRepository.save(new Enchere(new BigDecimal("1230.0"), LocalDateTime.now(), lot12, Identify1));
            enchereRepository.save(new Enchere(new BigDecimal("1240.0"), LocalDateTime.now().minusDays(1), lot12, Identify2));
            enchereRepository.save(new Enchere(new BigDecimal("1250.0"), LocalDateTime.now().minusDays(2), lot12, Identify3));

            enchereRepository.save(new Enchere(new BigDecimal("1330.0"), LocalDateTime.now(), lot13, Identify2));
            enchereRepository.save(new Enchere(new BigDecimal("1340.0"), LocalDateTime.now().minusDays(1), lot13, Identify3));
            enchereRepository.save(new Enchere(new BigDecimal("1350.0"), LocalDateTime.now().minusDays(2), lot13, Identify1));

            enchereRepository.save(new Enchere(new BigDecimal("1430.0"), LocalDateTime.now(), lot14, Identify3));
            enchereRepository.save(new Enchere(new BigDecimal("1440.0"), LocalDateTime.now().minusDays(1), lot14, Identify1));
            enchereRepository.save(new Enchere(new BigDecimal("1450.0"), LocalDateTime.now().minusDays(2), lot14, Identify2));

            enchereRepository.save(new Enchere(new BigDecimal("1530.0"), LocalDateTime.now(), lot15, Identify1));
            enchereRepository.save(new Enchere(new BigDecimal("1540.0"), LocalDateTime.now().minusDays(1), lot15, Identify2));
            enchereRepository.save(new Enchere(new BigDecimal("1550.0"), LocalDateTime.now().minusDays(2), lot15, Identify3));

            enchereRepository.save(new Enchere(new BigDecimal("13.0"), LocalDateTime.now(), lot16, Identify2));
            enchereRepository.save(new Enchere(new BigDecimal("14.0"), LocalDateTime.now().minusDays(1), lot16, Identify1));
            enchereRepository.save(new Enchere(new BigDecimal("15.0"), LocalDateTime.now().minusDays(2), lot16, Identify3));

            enchereRepository.save(new Enchere(new BigDecimal("8.0"), LocalDateTime.now(), lot17, Identify1));
            enchereRepository.save(new Enchere(new BigDecimal("9.0"), LocalDateTime.now().minusDays(1), lot17, Identify3));
            enchereRepository.save(new Enchere(new BigDecimal("10.0"), LocalDateTime.now().minusDays(2), lot17, Identify2));

            enchereRepository.save(new Enchere(new BigDecimal("930.0"), LocalDateTime.now(), lot18, Identify3));
            enchereRepository.save(new Enchere(new BigDecimal("940.0"), LocalDateTime.now().minusDays(1), lot18, Identify1));
            enchereRepository.save(new Enchere(new BigDecimal("950.0"), LocalDateTime.now().minusDays(2), lot18, Identify2));

            enchereRepository.save(new Enchere(new BigDecimal("1030.0"), LocalDateTime.now(), lot19, Identify1));
            enchereRepository.save(new Enchere(new BigDecimal("1040.0"), LocalDateTime.now().minusDays(1), lot19, Identify2));
            enchereRepository.save(new Enchere(new BigDecimal("1050.0"), LocalDateTime.now().minusDays(2), lot19, Identify3));

            enchereRepository.save(new Enchere(new BigDecimal("18.0"), LocalDateTime.now(), lot20, Identify3));
            enchereRepository.save(new Enchere(new BigDecimal("19.0"), LocalDateTime.now().minusDays(1), lot20, Identify1));
            enchereRepository.save(new Enchere(new BigDecimal("20.0"), LocalDateTime.now().minusDays(2), lot20, Identify2));

            enchereRepository.save(new Enchere(new BigDecimal("630.0"), LocalDateTime.now(), lot21, Identify2));
            enchereRepository.save(new Enchere(new BigDecimal("640.0"), LocalDateTime.now().minusDays(1), lot21, Identify3));
            enchereRepository.save(new Enchere(new BigDecimal("650.0"), LocalDateTime.now().minusDays(2), lot21, Identify1));

            enchereRepository.save(new Enchere(new BigDecimal("28.0"), LocalDateTime.now(), lot22, Identify1));
            enchereRepository.save(new Enchere(new BigDecimal("29.0"), LocalDateTime.now().minusDays(1), lot22, Identify2));
            enchereRepository.save(new Enchere(new BigDecimal("30.0"), LocalDateTime.now().minusDays(2), lot22, Identify3));

            enchereRepository.save(new Enchere(new BigDecimal("180.0"), LocalDateTime.now(), lot23, Identify2));
            enchereRepository.save(new Enchere(new BigDecimal("190.0"), LocalDateTime.now().minusDays(1), lot23, Identify3));
            enchereRepository.save(new Enchere(new BigDecimal("200.0"), LocalDateTime.now().minusDays(2), lot23, Identify1));

            enchereRepository.save(new Enchere(new BigDecimal("55.0"), LocalDateTime.now(), lot24, Identify3));
            enchereRepository.save(new Enchere(new BigDecimal("56.0"), LocalDateTime.now().minusDays(1), lot24, Identify1));
            enchereRepository.save(new Enchere(new BigDecimal("60.0"), LocalDateTime.now().minusDays(2), lot24, Identify2));

            enchereRepository.save(new Enchere(new BigDecimal("430.0"), LocalDateTime.now(), lot25, Identify1));
            enchereRepository.save(new Enchere(new BigDecimal("440.0"), LocalDateTime.now().minusDays(1), lot25, Identify2));
            enchereRepository.save(new Enchere(new BigDecimal("450.0"), LocalDateTime.now().minusDays(2), lot25, Identify3));

            enchereRepository.save(new Enchere(new BigDecimal("380.0"), LocalDateTime.now(), lot26, Identify2));
            enchereRepository.save(new Enchere(new BigDecimal("390.0"), LocalDateTime.now().minusDays(1), lot26, Identify3));
            enchereRepository.save(new Enchere(new BigDecimal("400.0"), LocalDateTime.now().minusDays(2), lot26, Identify1));

            enchereRepository.save(new Enchere(new BigDecimal("23.0"), LocalDateTime.now(), lot27, Identify3));
            enchereRepository.save(new Enchere(new BigDecimal("24.0"), LocalDateTime.now().minusDays(1), lot27, Identify1));
            enchereRepository.save(new Enchere(new BigDecimal("25.0"), LocalDateTime.now().minusDays(2), lot27, Identify2));

            enchereRepository.save(new Enchere(new BigDecimal("170.0"), LocalDateTime.now(), lot28, Identify1));
            enchereRepository.save(new Enchere(new BigDecimal("175.0"), LocalDateTime.now().minusDays(1), lot28, Identify2));
            enchereRepository.save(new Enchere(new BigDecimal("180.0"), LocalDateTime.now().minusDays(2), lot28, Identify3));

            enchereRepository.save(new Enchere(new BigDecimal("32.0"), LocalDateTime.now(), lot29, Identify3));
            enchereRepository.save(new Enchere(new BigDecimal("33.0"), LocalDateTime.now().minusDays(1), lot29, Identify2));
            enchereRepository.save(new Enchere(new BigDecimal("35.0"), LocalDateTime.now().minusDays(2), lot29, Identify1));

            enchereRepository.save(new Enchere(new BigDecimal("110.0"), LocalDateTime.now(), lot30, Identify2));
            enchereRepository.save(new Enchere(new BigDecimal("115.0"), LocalDateTime.now().minusDays(1), lot30, Identify1));
            enchereRepository.save(new Enchere(new BigDecimal("120.0"), LocalDateTime.now().minusDays(2), lot30, Identify3));

            enchereRepository.save(new Enchere(new BigDecimal("28.0"), LocalDateTime.now(), lot31, Identify3));
            enchereRepository.save(new Enchere(new BigDecimal("29.0"), LocalDateTime.now().minusDays(1), lot31, Identify1));
            enchereRepository.save(new Enchere(new BigDecimal("30.0"), LocalDateTime.now().minusDays(2), lot31, Identify2));

            enchereRepository.save(new Enchere(new BigDecimal("23.0"), LocalDateTime.now(), lot32, Identify1));
            enchereRepository.save(new Enchere(new BigDecimal("24.0"), LocalDateTime.now().minusDays(1), lot32, Identify2));
            enchereRepository.save(new Enchere(new BigDecimal("25.0"), LocalDateTime.now().minusDays(2), lot32, Identify3));

            enchereRepository.save(new Enchere(new BigDecimal("38.0"), LocalDateTime.now(), lot33, Identify2));
            enchereRepository.save(new Enchere(new BigDecimal("39.0"), LocalDateTime.now().minusDays(1), lot33, Identify3));
            enchereRepository.save(new Enchere(new BigDecimal("40.0"), LocalDateTime.now().minusDays(2), lot33, Identify1));

            enchereRepository.save(new Enchere(new BigDecimal("18.0"), LocalDateTime.now(), lot34, Identify3));
            enchereRepository.save(new Enchere(new BigDecimal("19.0"), LocalDateTime.now().minusDays(1), lot34, Identify2));
            enchereRepository.save(new Enchere(new BigDecimal("20.0"), LocalDateTime.now().minusDays(2), lot34, Identify1));
        };
    }
}
