package spring.library.service;


import spring.library.domain.BorrowReturn;
import java.util.List;

public interface BorrowReturnService {

    // 특정 맴버에 의한 특정 도서의 대출을 처리 대출 정보 반환
    BorrowReturn checkoutBook(Long bookId, Long memberId);

    // 특정 맴버에 의해 대출된 모든 도서의 목록을 반환
    List<BorrowReturn> findAllCheckoutsByMember(Long memberId);

    // 대출된 도서의 반납을 처리하고 반납된 도서의 정보를 반환
    BorrowReturn returnBook(Long checkOutId);

    // 대출된 도서의 반납 기한을 연장하고 업데이트된 대출 정보를 반환
    BorrowReturn extendDueDate(Long checkOutId);
}
