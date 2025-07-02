package patika.libraryManagementSpring.service.Implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import patika.libraryManagementSpring.dto.request.CategoryRequestDto;
import patika.libraryManagementSpring.dto.response.CategoryResponseDto;
import patika.libraryManagementSpring.entity.Category;
import patika.libraryManagementSpring.exception.ResourceNotFoundException;
import patika.libraryManagementSpring.repository.CategoryRepository;
import patika.libraryManagementSpring.service.Interface.CategoryService;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    private CategoryResponseDto mapToDto(Category c) {
        return CategoryResponseDto.builder()
                .id(c.getId())
                .name(c.getName())
                .description(c.getDescription())
                .build();
    }

    @Override
    public CategoryResponseDto create(CategoryRequestDto dto) {
        Category c = new Category();
        c.setName(dto.getName());
        c.setDescription(dto.getDescription());
        return mapToDto(categoryRepository.save(c));
    }

    @Override
    public java.util.List<CategoryResponseDto> getAll() {
        return categoryRepository.findAll()
                .stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public CategoryResponseDto getById(Long id) {
        Category c = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
        return mapToDto(c);
    }

    @Override
    public CategoryResponseDto update(Long id, CategoryRequestDto dto) {
        Category c = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
        c.setName(dto.getName());
        c.setDescription(dto.getDescription());
        return mapToDto(categoryRepository.save(c));
    }

    @Override
    public String delete(Long id) {
        Category c = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
        if (c.getBooks() != null && !c.getBooks().isEmpty()) {
            return "Bu kategoriye ait kitap var. Bu kategori silinemedi.";
        }
        categoryRepository.delete(c);
        return "Kategori başarıyla silindi.";
    }
}
