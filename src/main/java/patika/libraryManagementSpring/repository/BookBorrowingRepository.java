package patika.libraryManagementSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import patika.libraryManagementSpring.entity.BookBorrowing;

public interface BookBorrowingRepository extends JpaRepository<BookBorrowing, Long> {}
