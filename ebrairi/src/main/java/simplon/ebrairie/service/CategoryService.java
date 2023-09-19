package simplon.ebrairie.service;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import simplon.ebrairie.domain.Category;
import simplon.ebrairie.model.CategoryDTO;
import simplon.ebrairie.repos.CategoryRepository;
import simplon.ebrairie.util.NotFoundException;


@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(final CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryDTO> findAll() {
        final List<Category> categorys = categoryRepository.findAll(Sort.by("idstyle"));
        return categorys.stream()
                .map(category -> mapToDTO(category, new CategoryDTO()))
                .toList();
    }

    public CategoryDTO get(final Integer idstyle) {
        return categoryRepository.findById(idstyle)
                .map(category -> mapToDTO(category, new CategoryDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Integer create(final CategoryDTO categoryDTO) {
        final Category category = new Category();
        mapToEntity(categoryDTO, category);
        return categoryRepository.save(category).getIdstyle();
    }

    public void update(final Integer idstyle, final CategoryDTO categoryDTO) {
        final Category category = categoryRepository.findById(idstyle)
                .orElseThrow(NotFoundException::new);
        mapToEntity(categoryDTO, category);
        categoryRepository.save(category);
    }

    public void delete(final Integer idstyle) {
        categoryRepository.deleteById(idstyle);
    }

    private CategoryDTO mapToDTO(final Category category, final CategoryDTO categoryDTO) {
        categoryDTO.setIdstyle(category.getIdstyle());
        categoryDTO.setCategory(category.getCategory());
        return categoryDTO;
    }

    private Category mapToEntity(final CategoryDTO categoryDTO, final Category category) {
        category.setCategory(categoryDTO.getCategory());
        return category;
    }

}
