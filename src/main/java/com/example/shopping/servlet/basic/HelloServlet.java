package com.example.shopping.servlet.basic;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet(name = "helloServlet", urlPatterns = "/hello") // name: 서블릿 이름, urlpatterns : url 매핑
public class HelloServlet extends HttpServlet {

    // http 요청을 통해 매핑된 url이 호출되면 서블릿 컨테이너는 service 메서드를 실행한다.
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("HelloServlet.service");
        System.out.println("request = " + request);
        System.out.println("response = " + response);

        String username = request.getParameter("username");
        System.out.println("username = " + username);

        response.setContentType("text.plain");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write("hello" + username); //http 바디에 들어감

        //http 기능들을 직접 만들어서 사용하면 힘든데 서블릿이 많이 도와준다.
        //http응답에서 content-length는 웹 애플리케이션 서버가 자동으로 생성해준다.
    }
}
