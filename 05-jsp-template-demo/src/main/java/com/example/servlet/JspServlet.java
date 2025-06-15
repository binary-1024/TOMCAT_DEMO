package com.example.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * JSP模板演示Servlet
 */
@WebServlet("/jsp/*")
public class JspServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String pathInfo = request.getPathInfo();
        
        if (pathInfo == null || pathInfo.equals("/")) {
            // 显示首页
            showIndex(request, response);
        } else if (pathInfo.equals("/users")) {
            // 用户列表
            showUserList(request, response);
        } else if (pathInfo.equals("/profile")) {
            // 用户详情
            showProfile(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private void showIndex(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setAttribute("title", "JSP模板引擎演示");
        request.setAttribute("message", "欢迎使用JSP模板引擎！");
        request.setAttribute("currentTime", new java.util.Date());
        
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    private void showUserList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // 模拟用户数据
        List<User> users = Arrays.asList(
            new User(1, "张三", "zhangsan@example.com", 25),
            new User(2, "李四", "lisi@example.com", 30),
            new User(3, "王五", "wangwu@example.com", 28)
        );
        
        request.setAttribute("title", "用户列表");
        request.setAttribute("users", users);
        
        request.getRequestDispatcher("/users.jsp").forward(request, response);
    }

    private void showProfile(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        User user = new User(1, "张三", "zhangsan@example.com", 25);
        user.setAddress("北京市朝阳区");
        user.setPhone("13888888888");
        
        request.setAttribute("title", "用户详情");
        request.setAttribute("user", user);
        
        request.getRequestDispatcher("/profile.jsp").forward(request, response);
    }

    // 内部用户类
    public static class User {
        private int id;
        private String name;
        private String email;
        private int age;
        private String address;
        private String phone;

        public User(int id, String name, String email, int age) {
            this.id = id;
            this.name = name;
            this.email = email;
            this.age = age;
        }

        // Getter和Setter方法
        public int getId() { return id; }
        public void setId(int id) { this.id = id; }
        
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        
        public int getAge() { return age; }
        public void setAge(int age) { this.age = age; }
        
        public String getAddress() { return address; }
        public void setAddress(String address) { this.address = address; }
        
        public String getPhone() { return phone; }
        public void setPhone(String phone) { this.phone = phone; }
    }
} 