package com.example.booksrestserver;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

@WebFilter(filterName = "MyCustomFilter")
public class MyCustomFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws ServletException, IOException {

//        ((HttpServletRequest) request).getSession().setAttribute("attributeName", "Pesho");

        chain.doFilter(request, response);
    }
}
