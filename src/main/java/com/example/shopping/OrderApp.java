package com.example.shopping;

import com.example.shopping.member.Grade;
import com.example.shopping.member.Member;
import com.example.shopping.member.MemberService;
import com.example.shopping.member.MemberServiceImpl;
import com.example.shopping.order.Order;
import com.example.shopping.order.OrderService;
import com.example.shopping.order.OrderServiceImpl;

public class OrderApp {

    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();
        OrderService orderService = new OrderServiceImpl();

        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA",10000);

        System.out.println("order = " + order);
        System.out.println("order = " + order.calculatePrice());
    }
}
