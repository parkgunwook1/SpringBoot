package com.example.shopping.servlet.web.servletmvc;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "mvcMemberFormServlet", urlPatterns = "/servlet-mvc/members/new-form")
public class MvcMemberFormServlet extends HttpServlet {

    //회원가입
    // mvc 규칙은 항상 컨트롤러를 거치고 뷰로 간다.
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String viewPath = "/WEB-INF/views/new-form.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        // 컨트롤러에서 뷰로 이동할 때 사용하는 것.
        dispatcher.forward(request,response);
        // dispatcher.forward : 다른 서블릿이나 jsp로 이동할 수 있는 기능이다. 서버 내부에서 다시 호출이 발생한다.
        // WEB-INF는 컨트롤러에서 forward 해야 호출이 되게끔 하는것이다. 즉, WEB-INF/views/new-form.jsp url을 타이핑해서 접근하지 못하게하는것이다.
    }
}
