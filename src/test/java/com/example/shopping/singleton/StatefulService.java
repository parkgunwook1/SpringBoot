package com.example.shopping.singleton;

public class StatefulService {

//    private int price; // 상태를 유지하는 필드
    // 공유필드는 조심해야 한다. 스프링 빈은 항상 무상태로 설계하자.

    public int order(String name, int price){
        System.out.println("name = " + name + "price = " + price);
        return price;
//        this.price = price;
    }

//    public int getPrice(){
////        return price;
//    }

}
