package ch.etmles.payroll.Repositories;

import ch.etmles.payroll.Entities.Employee;
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
    CommandLineRunner initDatabase(EmployeeRepository employeerepository,LotRepository lotRepository, CategoryRepository categoryRepository){
        return args->{
            log.info("Preloading " + employeerepository.save(new Employee("Bilbo Baggins", "burglar")));
            log.info("Preloading " + employeerepository.save(new Employee("Frodo Baggins", "thief")));

            log.info("Preloading " + lotRepository.save(new Lot("Lot description 1", "Category 1", 100.0, 150.0)));
            log.info("Preloading " + lotRepository.save(new Lot("Lot description 2", "Category 2", 200.0, 250.0)));

            log.info("Preloading " + categoryRepository.save(new Category("Category 1")));
            log.info("Preloading " + categoryRepository.save(new Category("Category 2")));
        };
    }
}
