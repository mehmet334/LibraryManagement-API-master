package patika.libraryManagementSpring.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthorResponseDto {
    private Long id;
    private String name;
    private String birthDate;
    private String country;
}