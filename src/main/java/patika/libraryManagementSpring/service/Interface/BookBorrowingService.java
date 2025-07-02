package patika.libraryManagementSpring.service.Interface;

import patika.libraryManagementSpring.dto.request.BorrowRequestDto;
import patika.libraryManagementSpring.dto.request.ReturnRequestDto;
import patika.libraryManagementSpring.dto.response.BorrowResponseDto;

public interface BookBorrowingService {
    BorrowResponseDto borrow(BorrowRequestDto dto);
    BorrowResponseDto returnBook(Long id, ReturnRequestDto dto);
}
