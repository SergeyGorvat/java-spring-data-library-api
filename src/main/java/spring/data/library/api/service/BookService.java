package spring.data.library.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.data.library.api.dto.CreateBookDto;
import spring.data.library.api.dto.UpdateBookDto;
import spring.data.library.api.exception.BookNotFoundException;
import spring.data.library.api.model.Book;
import spring.data.library.api.repository.BookRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return (List<Book>) bookRepository.findAll();
    }

    public Optional<Book> getBookById(Long id) {
        return Optional.ofNullable(bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id)));
    }

    @Transactional
    public Book createBook(CreateBookDto dto) {
        Book book = Book.builder()
                .title(dto.getTitle())
                .author(dto.getAuthor())
                .publicationYear(dto.getPublicationYear())
                .build();

        return bookRepository.save(book);
    }

    @Transactional
    public Book updateBook(Long id, UpdateBookDto dto) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));

        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setPublicationYear(dto.getPublicationYear());

        return bookRepository.save(book);

    }

    @Transactional
    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new BookNotFoundException(id);
        }

        bookRepository.deleteById(id);
    }
}
