package com.example.demo.servlet;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Webserver("/status")
public class SimpleStatusServlet extends HttpServlet{

    @Overide
    protected void doGet(HttpServletRequest r,HttpServletResponce rep)throws Se
}