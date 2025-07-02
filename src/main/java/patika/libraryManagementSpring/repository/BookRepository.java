package patika.libraryManagementSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import patika.libraryManagementSpring.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {}

