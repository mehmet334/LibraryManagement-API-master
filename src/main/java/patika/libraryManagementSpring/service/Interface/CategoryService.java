package patika.libraryManagementSpring.service.Interface;

import patika.libraryManagementSpring.dto.request.CategoryRequestDto;
import patika.libraryManagementSpring.dto.response.CategoryResponseDto;

import java.util.List;

public interface CategoryService {
    CategoryResponseDto create(CategoryRequestDto dto);
    List<CategoryResponseDto> getAll();
    CategoryResponseDto getById(Long id);
    CategoryResponseDto update(Long id, CategoryRequestDto dto);
    String delete(Long id);
}