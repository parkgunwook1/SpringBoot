package com.example.shopping.member;

public class MemberServiceImpl implements MemberService{

    /*
    * 설계 변경으로 MemberServiceImpl은 MemoryMemberRepository를 의존하지 않는다. 단지, MemberRepository 인터페이스만 의존한다.
    *
    * MemberServiceImpl 입장에서 생성자를 통해 어떤 구현 객체가 들어올지(주입될지)는 알 수 없다.
    * MembmerServiceImpl의 생성자를 통해서 어떤 구현 객체를 주입할지는 오직 외부 AppConfig에서 결정된다.
    * MemberServiceImpl은 이제부터 "의존관계에 대한 고민은 외부"에 맡기고 실행에만 집중하면 된다.
    *
    * 객체 생성과 연결은 AppConfig가 담당한다.
    * DIP 완성: MemberServiceImpl은 MemberRepository인 추상에만 의존하면 된다. 이제 구체 클래스를 몰라도 된다.
    * */

    private final MemberRepository memberRepository ;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
