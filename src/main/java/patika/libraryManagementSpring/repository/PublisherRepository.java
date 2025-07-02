package patika.libraryManagementSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import patika.libraryManagementSpring.entity.Publisher;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {}

