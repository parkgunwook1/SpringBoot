package com.example.shopping.member;

public interface MemberRepository {

    void save(Member member);

    Member findById(Long member);
}
