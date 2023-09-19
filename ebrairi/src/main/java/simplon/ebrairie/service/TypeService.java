package simplon.ebrairie.service;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import simplon.ebrairie.domain.Category;
import simplon.ebrairie.domain.Type;
import simplon.ebrairie.model.TypeDTO;
import simplon.ebrairie.repos.CategoryRepository;
import simplon.ebrairie.repos.TypeRepository;
import simplon.ebrairie.util.NotFoundException;


@Service
public class TypeService {

    private final TypeRepository typeRepository;
    private final CategoryRepository categoryRepository;

    public TypeService(final TypeRepository typeRepository,
            final CategoryRepository categoryRepository) {
        this.typeRepository = typeRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<TypeDTO> findAll() {
        final List<Type> types = typeRepository.findAll(Sort.by("idType"));
        return types.stream()
                .map(type -> mapToDTO(type, new TypeDTO()))
                .toList();
    }

    public TypeDTO get(final Integer idType) {
        return typeRepository.findById(idType)
                .map(type -> mapToDTO(type, new TypeDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Integer create(final TypeDTO typeDTO) {
        final Type type = new Type();
        mapToEntity(typeDTO, type);
        return typeRepository.save(type).getIdType();
    }

    public void update(final Integer idType, final TypeDTO typeDTO) {
        final Type type = typeRepository.findById(idType)
                .orElseThrow(NotFoundException::new);
        mapToEntity(typeDTO, type);
        typeRepository.save(type);
    }

    public void delete(final Integer idType) {
        typeRepository.deleteById(idType);
    }

    private TypeDTO mapToDTO(final Type type, final TypeDTO typeDTO) {
        typeDTO.setIdType(type.getIdType());
        typeDTO.setTypeLabel(type.getTypeLabel());
        typeDTO.setCategoryIdstyle(type.getCategoryIdstyle() == null ? null : type.getCategoryIdstyle().getIdstyle());
        return typeDTO;
    }

    private Type mapToEntity(final TypeDTO typeDTO, final Type type) {
        type.setTypeLabel(typeDTO.getTypeLabel());
        final Category categoryIdstyle = typeDTO.getCategoryIdstyle() == null ? null : categoryRepository.findById(typeDTO.getCategoryIdstyle())
                .orElseThrow(() -> new NotFoundException("categoryIdstyle not found"));
        type.setCategoryIdstyle(categoryIdstyle);
        return type;
    }

    public boolean typeLabelExists(final String typeLabel) {
        return typeRepository.existsByTypeLabelIgnoreCase(typeLabel);
    }

}
