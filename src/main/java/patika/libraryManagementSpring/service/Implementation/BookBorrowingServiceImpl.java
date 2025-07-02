package patika.libraryManagementSpring.service.Implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import patika.libraryManagementSpring.dto.request.BorrowRequestDto;
import patika.libraryManagementSpring.dto.request.ReturnRequestDto;
import patika.libraryManagementSpring.dto.response.BorrowResponseDto;
import patika.libraryManagementSpring.entity.Book;
import patika.libraryManagementSpring.entity.BookBorrowing;
import patika.libraryManagementSpring.exception.ResourceNotFoundException;
import patika.libraryManagementSpring.repository.BookBorrowingRepository;
import patika.libraryManagementSpring.repository.BookRepository;
import patika.libraryManagementSpring.service.Interface.BookBorrowingService;

@Service
@RequiredArgsConstructor
public class BookBorrowingServiceImpl implements BookBorrowingService {
    private final BookRepository bookRepository;
    private final BookBorrowingRepository brRepo;

    @Override
    public BorrowResponseDto borrow(BorrowRequestDto dto) {
        Book b = bookRepository.findById(dto.getBookId())
                .orElseThrow(() -> new ResourceNotFoundException("Book", "id", dto.getBookId()));
        if (b.getStock() <= 0) {
            throw new IllegalStateException("Kitap stokta yok. Ödünç verilemez.");
        }
        b.setStock(b.getStock() - 1);
        bookRepository.save(b);

        BookBorrowing bb = BookBorrowing.builder()
                .book(b)
                .borrowerName(dto.getBorrowerName())
                .borrowerEmail(dto.getBorrowerEmail())
                .borrowingDate(dto.getBorrowingDate())
                .returnDate(null)
                .build();
        BookBorrowing saved = brRepo.save(bb);

        return BorrowResponseDto.builder()
                .id(saved.getId())
                .bookId(b.getId())
                .borrowerName(saved.getBorrowerName())
                .borrowerEmail(saved.getBorrowerEmail())
                .borrowingDate(saved.getBorrowingDate())
                .returnDate(saved.getReturnDate())
                .build();
    }

    @Override
    public BorrowResponseDto returnBook(Long id, ReturnRequestDto dto) {
        BookBorrowing bb = brRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("BookBorrowing", "id", id));
        bb.setReturnDate(dto.getReturnDate());
        BookBorrowing updated = brRepo.save(bb);
        // stok iade isteniyorsa, bookRepository üzerinden b.getStock()++ eklenebilir
        return BorrowResponseDto.builder()
                .id(updated.getId())
                .bookId(updated.getBook().getId())
                .borrowerName(updated.getBorrowerName())
                .borrowerEmail(updated.getBorrowerEmail())
                .borrowingDate(updated.getBorrowingDate())
                .returnDate(updated.getReturnDate())
                .build();
    }
}