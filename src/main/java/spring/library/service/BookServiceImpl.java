package spring.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.library.domain.Book;
import spring.library.repository.BookRepository;

import java.util.List;
    // BookService 인터페이스 구현체
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

        // 새로운 도서 정보를 저장하고 저장된 도서 객체를 반환
    @Override
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

        // 저장된 모든 도서 정보를 리스트로 반환
    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book updateBook(Long bookId, Book bookDetails) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + bookId));

        // 받아온 도서 정보로 기존 도서 정보 업데이트
        book.setTitle(bookDetails.getTitle());
        book.setAuthor(bookDetails.getAuthor());
        book.setPublisher(bookDetails.getPublisher());
        book.setPublicationYear(bookDetails.getPublicationYear());
        book.setClassification(bookDetails.getClassification());
        return bookRepository.save(book);
    }

    @Override
    public void deleteBook(Long bookId) {   // 주어진 ID의 도서를 삭제
        bookRepository.deleteById(bookId);
    }
}
