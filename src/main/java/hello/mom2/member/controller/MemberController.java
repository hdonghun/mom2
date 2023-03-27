package hello.mom2.member.controller;

import hello.mom2.member.domain.Member;
import hello.mom2.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {

        this.memberService = memberService;
    }

    @PostMapping("/members")
    public ResponseEntity<Member> join(@RequestBody Member member) {
        Member savedMember = memberService.join(member);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedMember.getId())
                .toUri();
        return ResponseEntity.created(location).body(savedMember);
    }

    @GetMapping("/members/{id}")
    public ResponseEntity<Member> getMember(@PathVariable Long id) {
        Optional<Member> optionalMember = memberService.findMember(id);
        if (optionalMember.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(optionalMember.get());
    }

    @GetMapping("/members")
    public ResponseEntity<List<Member>> getMembers() {

        return ResponseEntity.ok(memberService.findAllMembers());
    }

}
