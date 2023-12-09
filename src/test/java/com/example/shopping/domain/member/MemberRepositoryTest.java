package com.example.shopping.domain.member;

import com.example.shopping.servlet.domain.member.Member;
import com.example.shopping.servlet.domain.member.MemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemberRepositoryTest {

    MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach
    void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void save() {
        //given -> 이런게 주어졌을대
        Member member = new Member("hello", 20);

        //when -> 이런걸 실행했을때
        Member savedMember = memberRepository.save(member);

        //then -> 결과는 이거여야해
        Member findMember = memberRepository.findById(savedMember.getId());
        assertThat(findMember).isEqualTo(savedMember);
    }

    @Test
    void findAll() {
       //given
        Member member1 = new Member("member1", 20);
        Member member2 = new Member("member2", 30);

        memberRepository.save(member1);
        memberRepository.save(member2);
       //when
        List<Member> result = memberRepository.findAll();

       //then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(member1,member2); // result 객체에 member1, member2가 들어있는가
    }
}