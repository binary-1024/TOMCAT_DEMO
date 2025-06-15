package com.example.servlet;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Thymeleaf模板演示Servlet
 */
@WebServlet("/thymeleaf/*")
public class ThymeleafServlet extends HttpServlet {

    private TemplateEngine templateEngine;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        initializeTemplateEngine();
    }

    private void initializeTemplateEngine() {
        // 创建模板解析器
        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(getServletContext());
        templateResolver.setTemplateMode("HTML");
        templateResolver.setPrefix("/WEB-INF/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setCacheTTLMs(3600000L); // 1 hour cache
        templateResolver.setCharacterEncoding("UTF-8");

        // 创建模板引擎
        templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String pathInfo = request.getPathInfo();
        
        if (pathInfo == null || pathInfo.equals("/")) {
            showIndex(request, response);
        } else if (pathInfo.equals("/users")) {
            showUserList(request, response);
        } else if (pathInfo.equals("/profile")) {
            showProfile(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private void showIndex(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        WebContext context = new WebContext(request, response, getServletContext());
        context.setVariable("title", "Thymeleaf模板引擎演示");
        context.setVariable("message", "欢迎使用Thymeleaf模板引擎！");
        context.setVariable("currentTime", new java.util.Date());
        context.setVariable("features", Arrays.asList(
            new Feature("自然模板", "Thymeleaf模板就是标准的HTML", true),
            new Feature("强大表达式", "支持Spring EL表达式语法", true),
            new Feature("国际化", "内置国际化支持", true),
            new Feature("缓存机制", "高效的模板缓存", true)
        ));
        
        templateEngine.process("index", context, response.getWriter());
    }

    private void showUserList(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        List<User> users = Arrays.asList(
            new User(1, "张三", "zhangsan@example.com", 25),
            new User(2, "李四", "lisi@example.com", 30),
            new User(3, "王五", "wangwu@example.com", 28)
        );
        
        WebContext context = new WebContext(request, response, getServletContext());
        context.setVariable("title", "用户列表");
        context.setVariable("users", users);
        
        templateEngine.process("users", context, response.getWriter());
    }

    private void showProfile(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        User user = new User(1, "张三", "zhangsan@example.com", 25);
        user.setAddress("北京市朝阳区");
        user.setPhone("13888888888");
        
        WebContext context = new WebContext(request, response, getServletContext());
        context.setVariable("title", "用户详情");
        context.setVariable("user", user);
        
        templateEngine.process("profile", context, response.getWriter());
    }

    // 特性类
    public static class Feature {
        private String name;
        private String description;
        private boolean enabled;

        public Feature(String name, String description, boolean enabled) {
            this.name = name;
            this.description = description;
            this.enabled = enabled;
        }

        // Getter方法
        public String getName() { return name; }
        public String getDescription() { return description; }
        public boolean isEnabled() { return enabled; }
    }

    // 用户类
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