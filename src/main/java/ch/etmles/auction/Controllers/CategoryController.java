package ch.etmles.auction.Controllers;

import ch.etmles.auction.Entities.Category;
import ch.etmles.auction.Repositories.CategoryRepository;
import ch.etmles.auction.Exceptions.CategoryErrorException.CategoryNotFoundException;
import ch.etmles.auction.Exceptions.CategoryErrorException.CategoryAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryRepository repository;

    @Autowired
    public CategoryController(CategoryRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Category> getAllCategories() {
        return repository.findAll();
    }

    @PostMapping
    public Category addCategory(@RequestBody Category category) {
        if (repository.existsById(category.getId())) {
            throw new CategoryAlreadyExistsException(category.getId());
        }
        return repository.save(category);
    }

    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));
    }
}
