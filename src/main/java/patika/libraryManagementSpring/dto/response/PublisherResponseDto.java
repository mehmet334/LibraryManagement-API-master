package patika.libraryManagementSpring.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PublisherResponseDto {
    private Long id;
    private String name;
    private Integer establishmentYear;
    // address yok (isteğe göre gizlendi)
}
