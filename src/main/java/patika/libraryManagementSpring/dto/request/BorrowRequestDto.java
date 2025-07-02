package patika.libraryManagementSpring.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BorrowRequestDto {
    @NotNull
    private Long bookId;

    @NotBlank
    private String borrowerName;

    @NotBlank @Email
    private String borrowerEmail;

    @NotNull
    private LocalDate borrowingDate;
}