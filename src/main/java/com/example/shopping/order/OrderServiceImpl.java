package com.example.shopping.order;

import com.example.shopping.discount.DiscountPolicy;
import com.example.shopping.discount.FixDiscountPolicy;
import com.example.shopping.discount.RateDiscountPolicy;
import com.example.shopping.member.Member;
import com.example.shopping.member.MemberRepository;
import com.example.shopping.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository; // final이 되어있으면 기본으로 할당을 하든, 생성자로 할당을 해야한다.
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    //    private final DiscountPolicy discountPolicy = new FixDiscountPolicy(); => DIP 위반

    /*
    * - 설계 변경으로 OrderServiceImpl은 FixDiscountPolicy를 의존하지 않는다. 단지, DiscountPolicy 인터페이스만 의존한다.
    * - OrderServiceImpl 입장에서 생성자를 통해 어떤 구현 객체다 글어올지(주입될지)는 알 수 없다.
    * - OrderServiceImpl 의 생성자를 통해서 어떤 구현 객체를 주입할지는 오직 외부 AppConfig 에서 결정한다.
    * - OrderServiceImpl 은 이제부터 실행에만 집중하면 된다.
    *
    * - OrderServiceImpl 에는 MemoryMemberRepository, FixDiscountPolicy 객체의 의존관계가 주입된다.
    * */



    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
