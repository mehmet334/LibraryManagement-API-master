package patika.libraryManagementSpring.service.Implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import patika.libraryManagementSpring.dto.request.AuthorRequestDto;
import patika.libraryManagementSpring.dto.response.AuthorResponseDto;
import patika.libraryManagementSpring.entity.Author;
import patika.libraryManagementSpring.exception.ResourceNotFoundException;
import patika.libraryManagementSpring.repository.AuthorRepository;
import patika.libraryManagementSpring.service.Interface.AuthorService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    private AuthorResponseDto mapToDto(Author a) {
        return AuthorResponseDto.builder()
                .id(a.getId())
                .name(a.getName())
                .birthDate(a.getBirthDate() != null ? a.getBirthDate().toString() : null)
                .country(a.getCountry())
                .build();
    }

    @Override
    public AuthorResponseDto create(AuthorRequestDto dto) {
        Author a = Author.builder()
                .name(dto.getName())
                .birthDate(dto.getBirthDate() != null ? LocalDate.parse(dto.getBirthDate()) : null)
                .country(dto.getCountry())
                .build();
        return mapToDto(authorRepository.save(a));
    }

    @Override
    public List<AuthorResponseDto> getAll() {
        return authorRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public AuthorResponseDto getById(Long id) {
        Author a = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author", "id", id));
        return mapToDto(a);
    }

    @Override
    public AuthorResponseDto update(Long id, AuthorRequestDto dto) {
        Author a = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author", "id", id));
        a.setName(dto.getName());
        a.setBirthDate(dto.getBirthDate() != null ? LocalDate.parse(dto.getBirthDate()) : null);
        a.setCountry(dto.getCountry());
        return mapToDto(authorRepository.save(a));
    }

    @Override
    public String delete(Long id) {
        Author a = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author", "id", id));
        authorRepository.delete(a);
        return "Yazar başarıyla silindi.";
    }
}