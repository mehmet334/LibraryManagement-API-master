package patika.libraryManagementSpring.service.Interface;

import patika.libraryManagementSpring.dto.request.BookRequestDto;
import patika.libraryManagementSpring.dto.response.BookResponseDto;

import java.util.List;

public interface BookService {
    BookResponseDto create(BookRequestDto dto);
    List<BookResponseDto> getAll();
    BookResponseDto getById(Long id);
    BookResponseDto update(Long id, BookRequestDto dto);
    String delete(Long id);
}