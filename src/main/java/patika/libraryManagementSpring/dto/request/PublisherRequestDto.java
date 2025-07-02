package patika.libraryManagementSpring.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PublisherRequestDto {
    @NotBlank
    private String name;
    private Integer establishmentYear;
    @NotBlank
    private String address;
}
