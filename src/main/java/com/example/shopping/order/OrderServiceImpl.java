package com.example.shopping.order;

import com.example.shopping.discount.DiscountPolicy;
import com.example.shopping.discount.FixDiscountPolicy;
import com.example.shopping.member.Member;
import com.example.shopping.member.MemberRepository;
import com.example.shopping.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
