package spring.library.service;



import spring.library.domain.Book;

import java.util.List;

public interface BookService {

    // 도서 정보를 추가하고 추가된 도서 정보를 반환
    Book saveBook(Book book);

    // 저장된 모든 도서 정보를 리스트로 반환
    List<Book> getAllBooks();

    // 주어진 아이디의 도서 정보를 새로운 정보로 수정
    Book updateBook(Long bookId, Book bookDetails);

    // 주어진 아이디의 도서를 삭제
    void deleteBook(Long bookId);
}