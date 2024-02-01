package spring.library.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.library.domain.Book;
import spring.library.service.BookService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping    // 새 도서 정보를 추가
    public Book addBook(@RequestBody Book book) {
        return bookService.saveBook(book);
    }

    @GetMapping     //모든 도서 정보를 조회
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @PutMapping("/{bookId}")        // 특정 도서 정보를 수정
    public ResponseEntity<Book> updateBook(@PathVariable Long bookId, @RequestBody Book bookDetails) {
        Book updatedBook = bookService.updateBook(bookId, bookDetails);
        return ResponseEntity.ok(updatedBook);
    }

    @DeleteMapping("/{bookId}")     // 특정 도서를 삭제
    public ResponseEntity<Map<String, Boolean>> deleteBook(@PathVariable Long bookId) {
        bookService.deleteBook(bookId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}