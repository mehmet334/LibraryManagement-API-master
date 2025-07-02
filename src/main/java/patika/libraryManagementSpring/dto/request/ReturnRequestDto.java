package patika.libraryManagementSpring.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.LocalDate;

@Data @NoArgsConstructor @AllArgsConstructor
public class ReturnRequestDto {
    @NotNull
    private LocalDate returnDate;
}
