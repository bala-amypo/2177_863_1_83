// //*package com.example.demo.security;

// import jakarta.servlet.*;
// import jakarta.servlet.http.HttpServletRequest;
// import java.io.IOException;

// public class JwtAuthenticationFilter implements Filter {

//     private final JwtTokenProvider tokenProvider;

//     public JwtAuthenticationFilter(JwtTokenProvider tokenProvider) {
//         this.tokenProvider = tokenProvider;
//     }

//     @Override
//     public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//             throws IOException, ServletException {

//         HttpServletRequest req = (HttpServletRequest) request;
//         String header = req.getHeader("Authorization");

//         if (header != null && header.startsWith("Bearer ")) {
//             String token = header.substring(7);
//             tokenProvider.validateToken(token);
//         }

//         chain.doFilter(request, response);
//     }
// }/*
