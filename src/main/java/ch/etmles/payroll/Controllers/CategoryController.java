package ch.etmles.payroll.Controllers;

import ch.etmles.payroll.Entities.Category;
import ch.etmles.payroll.Repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@CrossOrigin(origins = "http://localhost:8081") // Autoriser les requêtes depuis l'URL frontend
public class CategoryController {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @PostMapping
    public Category addCategory(@RequestBody Category category) {
        return categoryRepository.save(category);
    }

    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));
    }

    @PutMapping("/{id}")
    public Category updateCategory(@RequestBody Category newCategory, @PathVariable Long id) {
        return categoryRepository.findById(id)
                .map(category -> {
                    category.setName(newCategory.getName());
                    return categoryRepository.save(category);
                })
                .orElseGet(() -> {
                    newCategory.setId(id);
                    return categoryRepository.save(newCategory);
                });
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id) {
        categoryRepository.deleteById(id);
    }
}
