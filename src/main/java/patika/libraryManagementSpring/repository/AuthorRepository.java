package patika.libraryManagementSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import patika.libraryManagementSpring.entity.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {}
