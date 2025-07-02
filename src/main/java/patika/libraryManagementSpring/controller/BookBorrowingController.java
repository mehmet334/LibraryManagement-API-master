package patika.libraryManagementSpring.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import patika.libraryManagementSpring.dto.request.BorrowRequestDto;
import patika.libraryManagementSpring.dto.request.ReturnRequestDto;
import patika.libraryManagementSpring.dto.response.BorrowResponseDto;
import patika.libraryManagementSpring.service.Interface.BookBorrowingService;

@RestController
@RequestMapping("/api/borrowings")
@RequiredArgsConstructor
@Validated
public class BookBorrowingController {
    private final BookBorrowingService service;

    @PostMapping
    public ResponseEntity<BorrowResponseDto> borrow(@RequestBody @Valid BorrowRequestDto dto) {
        return new ResponseEntity<>(service.borrow(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}/return")
    public ResponseEntity<BorrowResponseDto> returnBook(@PathVariable Long id,
                                                        @RequestBody @Valid ReturnRequestDto dto) {
        return ResponseEntity.ok(service.returnBook(id, dto));
    }
}
