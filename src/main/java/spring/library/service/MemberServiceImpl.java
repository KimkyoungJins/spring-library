package spring.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.library.domain.Member;
import spring.library.repository.MemberRepository;

import java.util.List;

// MemberService 인터페이스 구현체

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    @Transactional  // 데이터베이스 트랜잭션 처리한다!
    public Member registerMember(Member member) {
        return memberRepository.save(member);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Member> findAllMembers() {
        return memberRepository.findAll();
    }

    @Override
    @Transactional
    public Member updateMember(Long memberId, Member memberDetails) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found with id: " + memberId));


        // 받은 상세 정보로 회원 정보 업데이트
        member.setName(memberDetails.getName());
        member.setIdNumber(memberDetails.getIdNumber());
        member.setFeature(memberDetails.getFeature());
        member.setEmail(memberDetails.getEmail());
        member.setPhoneNumber(memberDetails.getPhoneNumber());

        return memberRepository.save(member);       // 업데이트된 회원 정보 저장
    }

    @Override
    @Transactional
    public void deleteMember(Long memberId) {
        memberRepository.deleteById(memberId);      // 회원 삭제
    }
}
