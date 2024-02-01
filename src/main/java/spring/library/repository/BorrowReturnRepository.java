package spring.library.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.library.domain.BorrowReturn;

import java.util.List;

@Repository
public interface BorrowReturnRepository extends JpaRepository<BorrowReturn, Long> {
    List<BorrowReturn> findByMemberMemberId(Long memberId);

}
