package spring.data.library.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import spring.data.library.api.model.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {


}
