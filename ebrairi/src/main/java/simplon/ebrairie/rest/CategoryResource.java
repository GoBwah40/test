package simplon.ebrairie.rest;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import simplon.ebrairie.model.CategoryDTO;
import simplon.ebrairie.service.CategoryService;


@RestController
@RequestMapping(value = "/api/categorys", produces = MediaType.APPLICATION_JSON_VALUE)
public class CategoryResource {

    private final CategoryService categoryService;

    public CategoryResource(final CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategorys() {
        return ResponseEntity.ok(categoryService.findAll());
    }

    @GetMapping("/{idstyle}")
    public ResponseEntity<CategoryDTO> getCategory(
            @PathVariable(name = "idstyle") final Integer idstyle) {
        return ResponseEntity.ok(categoryService.get(idstyle));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Integer> createCategory(
            @RequestBody @Valid final CategoryDTO categoryDTO) {
        final Integer createdIdstyle = categoryService.create(categoryDTO);
        return new ResponseEntity<>(createdIdstyle, HttpStatus.CREATED);
    }

    @PutMapping("/{idstyle}")
    public ResponseEntity<Integer> updateCategory(
            @PathVariable(name = "idstyle") final Integer idstyle,
            @RequestBody @Valid final CategoryDTO categoryDTO) {
        categoryService.update(idstyle, categoryDTO);
        return ResponseEntity.ok(idstyle);
    }

    @DeleteMapping("/{idstyle}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteCategory(
            @PathVariable(name = "idstyle") final Integer idstyle) {
        categoryService.delete(idstyle);
        return ResponseEntity.noContent().build();
    }

}
