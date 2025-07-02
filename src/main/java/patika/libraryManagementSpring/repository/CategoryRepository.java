package patika.libraryManagementSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import patika.libraryManagementSpring.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {}

