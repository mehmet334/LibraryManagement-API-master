package patika.libraryManagementSpring.service.Implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import patika.libraryManagementSpring.dto.request.BookRequestDto;
import patika.libraryManagementSpring.dto.response.AuthorResponseDto;
import patika.libraryManagementSpring.dto.response.BookResponseDto;
import patika.libraryManagementSpring.dto.response.CategoryResponseDto;
import patika.libraryManagementSpring.dto.response.PublisherResponseDto;
import patika.libraryManagementSpring.entity.Author;
import patika.libraryManagementSpring.entity.Book;
import patika.libraryManagementSpring.entity.Category;
import patika.libraryManagementSpring.entity.Publisher;
import patika.libraryManagementSpring.exception.ResourceNotFoundException;
import patika.libraryManagementSpring.repository.AuthorRepository;
import patika.libraryManagementSpring.repository.BookRepository;
import patika.libraryManagementSpring.repository.CategoryRepository;
import patika.libraryManagementSpring.repository.PublisherRepository;
import patika.libraryManagementSpring.service.Interface.BookService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;
    private final CategoryRepository categoryRepository;

    private BookResponseDto mapToDto(Book b) {
        // Author DTO
        AuthorResponseDto authorDto = AuthorResponseDto.builder()
                .id(b.getAuthor().getId())
                .name(b.getAuthor().getName())
                .birthDate(b.getAuthor().getBirthDate() != null ? b.getAuthor().getBirthDate().toString() : null)
                .country(b.getAuthor().getCountry())
                .build();

        // Publisher DTO (adres gizli)
        PublisherResponseDto pubDto = PublisherResponseDto.builder()
                .id(b.getPublisher().getId())
                .name(b.getPublisher().getName())
                .establishmentYear(b.getPublisher().getEstablishmentYear())
                .build();

        // Category DTO listesi
        List<CategoryResponseDto> cats = b.getCategories().stream()
                .map(c -> CategoryResponseDto.builder()
                        .id(c.getId())
                        .name(c.getName())
                        .description(c.getDescription())
                        .build())
                .collect(Collectors.toList());

        return BookResponseDto.builder()
                .id(b.getId())
                .name(b.getName())
                .publicationYear(b.getPublicationYear())
                .stock(b.getStock())
                .author(authorDto)
                .publisher(pubDto)
                .categories(cats)
                .build();
    }

    @Override
    public BookResponseDto create(BookRequestDto dto) {
        Author author = authorRepository.findById(dto.getAuthorId())
                .orElseThrow(() -> new ResourceNotFoundException("Author", "id", dto.getAuthorId()));
        Publisher publisher = publisherRepository.findById(dto.getPublisherId())
                .orElseThrow(() -> new ResourceNotFoundException("Publisher", "id", dto.getPublisherId()));
        List<Category> categories = dto.getCategoryIds() != null
                ? dto.getCategoryIds().stream()
                .map(cid -> categoryRepository.findById(cid)
                        .orElseThrow(() -> new ResourceNotFoundException("Category", "id", cid)))
                .collect(Collectors.toList())
                : List.of();

        Book b = Book.builder()
                .name(dto.getName())
                .publicationYear(dto.getPublicationYear())
                .stock(dto.getStock())
                .author(author)
                .publisher(publisher)
                .categories(categories)
                .build();

        return mapToDto(bookRepository.save(b));
    }

    @Override
    public List<BookResponseDto> getAll() {
        return bookRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public BookResponseDto getById(Long id) {
        Book b = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book", "id", id));
        return mapToDto(b);
    }

    @Override
    public BookResponseDto update(Long id, BookRequestDto dto) {
        Book b = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book", "id", id));
        Author author = authorRepository.findById(dto.getAuthorId())
                .orElseThrow(() -> new ResourceNotFoundException("Author", "id", dto.getAuthorId()));
        Publisher publisher = publisherRepository.findById(dto.getPublisherId())
                .orElseThrow(() -> new ResourceNotFoundException("Publisher", "id", dto.getPublisherId()));
        List<Category> categories = dto.getCategoryIds() != null
                ? dto.getCategoryIds().stream()
                .map(cid -> categoryRepository.findById(cid)
                        .orElseThrow(() -> new ResourceNotFoundException("Category", "id", cid)))
                .collect(Collectors.toList())
                : List.of();

        b.setName(dto.getName());
        b.setPublicationYear(dto.getPublicationYear());
        b.setStock(dto.getStock());
        b.setAuthor(author);
        b.setPublisher(publisher);
        b.setCategories(categories);

        return mapToDto(bookRepository.save(b));
    }

    @Override
    public String delete(Long id) {
        Book b = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book", "id", id));
        bookRepository.delete(b);
        return "Kitap başarıyla silindi.";
    }
}
