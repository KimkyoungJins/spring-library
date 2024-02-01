package spring.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.library.domain.Member;
import spring.library.service.MemberService;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("members")
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // 회원 등록
    @PostMapping
    public ResponseEntity<Member> registerMember(@RequestBody Member member) {
        Member newMember = memberService.registerMember(member);
        return new ResponseEntity<>(newMember, HttpStatus.CREATED);
    }

    // 등록된 회원 목록 조회
    @GetMapping
    public ResponseEntity<Map<String, List<Member>>> getAllMembers() {
        List<Member> members = memberService.findAllMembers();
        Map<String, List<Member>> response = new HashMap<>();
        response.put("members", members);
        return ResponseEntity.ok(response);
    }

    // 회원 정보 수정
    @PutMapping("/{memberId}")
    public ResponseEntity<Member> updateMember(@PathVariable Long memberId, @RequestBody Member memberDetails) {
        Member updatedMember = memberService.updateMember(memberId, memberDetails);
        return ResponseEntity.ok(updatedMember);
    }

    // 회원 정보 삭제
    @DeleteMapping("/{memberId}")
    public ResponseEntity<Map<String, Boolean>> deleteMember(@PathVariable Long memberId) {
        memberService.deleteMember(memberId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}




