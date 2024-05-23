<!-- EnchereController -->
        package ch.etmles.payroll.Controllers;

import ch.etmles.payroll.Entities.Enchere;
import ch.etmles.payroll.Repositories.EnchereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/encheres")
@CrossOrigin(origins = "http://localhost:8081")
public class EnchereController {

    private final EnchereRepository repository;

    @Autowired
    public EnchereController(EnchereRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Enchere> getAllEncheres() {
        return repository.findAll();
    }

    @PostMapping
    public Enchere addEnchere(@RequestBody Enchere enchere) {
        return repository.save(enchere);
    }

    @GetMapping("/{id}")
    public Enchere getEnchereById(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EnchereNotFoundException(id));
    }
}
