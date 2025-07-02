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
public class AuthorRequestDto {
    @NotBlank
    private String name;
    private String birthDate; // YYYY-MM-DD
    private String country;
}