package patika.libraryManagementSpring.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookRequestDto {
    @NotBlank
    private String name;

    private Integer publicationYear;

    @NotNull
    @Min(0)
    private Integer stock;

    @NotNull
    private Long authorId;

    @NotNull
    private Long publisherId;

    private List<Long> categoryIds;
}
