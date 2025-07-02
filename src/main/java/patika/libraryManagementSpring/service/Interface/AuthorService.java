package patika.libraryManagementSpring.service.Interface;

import patika.libraryManagementSpring.dto.request.AuthorRequestDto;
import patika.libraryManagementSpring.dto.response.AuthorResponseDto;

import java.util.List;

public interface AuthorService {
    AuthorResponseDto create(AuthorRequestDto dto);
    List<AuthorResponseDto> getAll();
    AuthorResponseDto getById(Long id);
    AuthorResponseDto update(Long id, AuthorRequestDto dto);
    String delete(Long id);
}
