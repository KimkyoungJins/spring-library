package spring.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.library.domain.Book;

import java.util.List;


// JPA repositroy를 상속받는다.
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}

