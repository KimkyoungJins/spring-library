package spring.library.service;



import spring.library.domain.Member;


import java.util.List;

public interface MemberService {

    // 새 회원을 등록하고 등록된 회원정보 반환
    Member registerMember(Member member);

    //등록된 모든 회원의 정보를 리스트로 반환
    List<Member> findAllMembers();

    // 주어진 ID를 가진 회원의 정보를 새로운 정보로 업데이트하고 업데이트 업데이트된 회원 정보를 반환
    Member updateMember(Long memberId, Member memberDetails);

    // 주어진 ID를 가진 회원을 시스템에서 삭제
    void deleteMember(Long memberId);
}
