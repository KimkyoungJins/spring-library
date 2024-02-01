package spring.library.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.library.domain.Book;
import spring.library.domain.BorrowReturn;
import spring.library.domain.Member;
import spring.library.repository.BookRepository;
import spring.library.repository.BorrowReturnRepository;
import spring.library.repository.MemberRepository;

import java.time.LocalDate;
import java.util.List;

// BoorrowReturn 인터페이스 구현체
@Service
public class BorrowReturnServiceImpl implements BorrowReturnService {

    @Autowired
    private BorrowReturnRepository borrowReturnRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public BorrowReturn checkoutBook(Long bookId, Long memberId) {
        // 여기에 대출 로직 구현
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new RuntimeException("Member not found"));

        BorrowReturn borrowReturn = new BorrowReturn();
        borrowReturn.setBook(book);
        borrowReturn.setMember(member);
        borrowReturn.setCheckOutDate(LocalDate.now());
        // 대출 기한 설정 로직 추가 필요
        borrowReturn.setDueDate(LocalDate.now().plusDays(10)); // 기본값 예시
        borrowReturn.setRenewalCount(0);
        borrowReturn.setReturned(false);

        return borrowReturnRepository.save(borrowReturn);
    }

    @Override
    public List<BorrowReturn> findAllCheckoutsByMember(Long memberId) {
        // 회원별 대출 목록 조회 로직 구현
        return borrowReturnRepository.findByMemberMemberId(memberId);
    }

    @Override
    public BorrowReturn returnBook(Long checkOutId) {
        // 반납 로직 구현
        BorrowReturn borrowReturn = borrowReturnRepository.findById(checkOutId)
                .orElseThrow(() -> new RuntimeException("Checkout record not found"));
        borrowReturn.setReturned(true);
        return borrowReturnRepository.save(borrowReturn);
    }

    @Override
    public BorrowReturn extendDueDate(Long checkOutId) {
        // 대출 기한 연장 로직 구현
        BorrowReturn borrowReturn = borrowReturnRepository.findById(checkOutId)
                .orElseThrow(() -> new RuntimeException("Checkout record not found"));

        if (borrowReturn.getRenewalCount() >= 1) {
            throw new RuntimeException("Renewal limit reached");
        }

        borrowReturn.setDueDate(borrowReturn.getDueDate().plusDays(5));
        borrowReturn.setRenewalCount(borrowReturn.getRenewalCount() + 1);
        return borrowReturnRepository.save(borrowReturn);
    }
}
