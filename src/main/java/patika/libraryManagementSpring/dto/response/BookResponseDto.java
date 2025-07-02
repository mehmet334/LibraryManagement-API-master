package patika.libraryManagementSpring.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookResponseDto {
    private Long id;
    private String name;
    private Integer publicationYear;
    private Integer stock;
    private AuthorResponseDto author;
    private PublisherResponseDto publisher;
    private List<CategoryResponseDto> categories;
}
