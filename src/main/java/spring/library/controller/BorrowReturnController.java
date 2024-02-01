package spring.library.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.library.domain.BorrowReturn;
import spring.library.service.BorrowReturnService;

import java.util.List;

@RestController
@RequestMapping("/checkouts")
public class BorrowReturnController {

    private final BorrowReturnService borrowReturnService;

    @Autowired
    public BorrowReturnController(BorrowReturnService borrowReturnService) {
        this.borrowReturnService = borrowReturnService;
    }

    // 도서 대출 요청 처리
    @PostMapping("/{bookId}")
    public ResponseEntity<BorrowReturn> checkoutBook(@PathVariable Long bookId, @RequestBody Long memberId) {
        BorrowReturn borrowReturn = borrowReturnService.checkoutBook(bookId, memberId);
        return ResponseEntity.ok(borrowReturn);
    }

    // 대출한 도서 목록 조회
    @GetMapping("/member/{memberId}")
    public ResponseEntity<List<BorrowReturn>> getAllCheckoutsByMember(@PathVariable Long memberId) {
        List<BorrowReturn> checkouts = borrowReturnService.findAllCheckoutsByMember(memberId);
        return ResponseEntity.ok(checkouts);
    }

    // 도서 반납 처리
    @PatchMapping("/{checkOutId}/return")
    public ResponseEntity<BorrowReturn> returnBook(@PathVariable Long checkOutId) {
        BorrowReturn borrowReturn = borrowReturnService.returnBook(checkOutId);
        return ResponseEntity.ok(borrowReturn);
    }

    // 대출 기한 연장 처리
    @PatchMapping("/{checkOutId}/extension")
    public ResponseEntity<BorrowReturn> extendDueDate(@PathVariable Long checkOutId) {
        BorrowReturn borrowReturn = borrowReturnService.extendDueDate(checkOutId);
        return ResponseEntity.ok(borrowReturn);
    }
}
