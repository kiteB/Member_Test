package com.study.springbasic.controller;

import com.study.springbasic.domain.Member;
import com.study.springbasic.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class MemberController {

    @Autowired
    MemberRepository memberRepository;

    @GetMapping("/members")
    public ResponseEntity<List<Member>> getAllMembers() {
        try {
            List<Member> members = new ArrayList<Member>();
            memberRepository.findAll().forEach(members::add);

            if (members.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            return new ResponseEntity<>(members, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/members")
    public ResponseEntity<Member> createMember(@RequestBody Member member) {
        try {
            Member member2 = memberRepository
                    .save(new Member(member.getEmail(), member.getNickname(), member.getTest()));
            return new ResponseEntity<>(member2, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
