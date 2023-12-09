package com.example.shopping.servlet.basic.response;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {

    // 응답 기본
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        // [status-line]
        response.setStatus(HttpServletResponse.SC_OK); //SC_OK HTTP 200 응답

        // [response-headers]
        response.setHeader("Content-Type", "text/plain;charset=utf-8"); //;charset=utf-8 안쓰면 한글 깨진다.
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // 캐시 무효화
        response.setHeader("Pragma", "no-cache"); // 캐시 무효화
        response.setHeader("my-header","hello"); // 임의의 헤더, http 헤더에 실려나간다.

        //[Header 편의 메서드]
        content(response);
        cookie(response);
        redirect(response);

        // [message body] 원하는 값을 넣어주면 메시지바디에 넣어주게 된다. // wirter,inputstream 두 개 받을 수 있다.
        PrintWriter writer = response.getWriter();
        writer.println("안녕하세요");
    }
    // content 편의 메서드 사용법
    private void content(HttpServletResponse response) {
        //Content-Type: text/plain;charset=utf-8
        //Content-Length: 2
        //response.setHeader("Content-Type", "text/plain;charset=utf-8");
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        //response.setContentLength(2); //(생략시 자동 생성)
    }

    // cookie 편의 메서드 사용법
    private void cookie(HttpServletResponse response) {
        //Set-Cookie: myCookie=good; Max-Age=600;
        //response.setHeader("Set-Cookie", "myCookie=good; Max-Age=600");
        Cookie cookie = new Cookie("myCookie", "good");
        cookie.setMaxAge(600); //600초
        response.addCookie(cookie);
        /*
        * cookie 객체가 있다.
        * 위 주석코드인 setHeader를 사용하는것과 동일한 효과를 보여준다.
        * */
    }

    // rediredc 편의 메서드 사용법
    private void redirect(HttpServletResponse response) throws IOException {
        //Status Code 302
        //Location: /basic/hello-form.html

        //response.setStatus(HttpServletResponse.SC_FOUND); //302
        //response.setHeader("Location", "/basic/hello-form.html");
        response.sendRedirect("/basic/hello-form.html"); // 위 두줄을 생략해서 보냄.
    }
}
