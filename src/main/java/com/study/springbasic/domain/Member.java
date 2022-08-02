package com.study.springbasic.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@NoArgsConstructor
@Entity
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String nickname;
    private String test;


    public Member(String email, String nickname, String test) {
        this.email = email;
        this.nickname = nickname;
        this.test = test;
    }

    @PrePersist
    public void prePersist() {
        this.nickname = this.nickname == null ? this.email : this.nickname;
    }

    @PostLoad
    public void postLoad() {
        this.test = this.test == null ? this.email : this.test;
    }
}