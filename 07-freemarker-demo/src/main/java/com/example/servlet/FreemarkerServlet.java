package com.example.servlet;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * FreeMarker模板演示Servlet
 */
@WebServlet("/freemarker/*")
public class FreemarkerServlet extends HttpServlet {

    private Configuration freemarkerConfig;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        initializeFreemarker();
    }

    private void initializeFreemarker() throws ServletException {
        try {
            // 创建FreeMarker配置
            freemarkerConfig = new Configuration(Configuration.VERSION_2_3_32);
            
            // 设置模板加载器
            freemarkerConfig.setServletContextForTemplateLoading(getServletContext(), "/WEB-INF/templates");
            
            // 设置默认编码
            freemarkerConfig.setDefaultEncoding("UTF-8");
            
            // 设置异常处理器
            freemarkerConfig.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            
            // 设置日期格式
            freemarkerConfig.setDateFormat("yyyy-MM-dd");
            freemarkerConfig.setTimeFormat("HH:mm:ss");
            freemarkerConfig.setDateTimeFormat("yyyy-MM-dd HH:mm:ss");
            
        } catch (Exception e) {
            throw new ServletException("Failed to initialize FreeMarker", e);
        }
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
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        Map<String, Object> model = new HashMap<>();
        model.put("title", "FreeMarker模板引擎演示");
        model.put("message", "欢迎使用FreeMarker模板引擎！");
        model.put("currentTime", new java.util.Date());
        model.put("features", Arrays.asList(
            new Feature("强大的表达式", "支持复杂的数据处理", true),
            new Feature("宏定义", "可以定义可重用的模板片段", true),
            new Feature("内置函数", "提供丰富的内置函数", true),
            new Feature("性能优秀", "编译缓存提高性能", true)
        ));
        
        processTemplate("index.ftlh", model, response);
    }

    private void showUserList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        List<User> users = Arrays.asList(
            new User(1, "张三", "zhangsan@example.com", 25),
            new User(2, "李四", "lisi@example.com", 30),
            new User(3, "王五", "wangwu@example.com", 28)
        );
        
        Map<String, Object> model = new HashMap<>();
        model.put("title", "用户列表");
        model.put("users", users);
        
        processTemplate("users.ftlh", model, response);
    }

    private void showProfile(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        User user = new User(1, "张三", "zhangsan@example.com", 25);
        user.setAddress("北京市朝阳区");
        user.setPhone("13888888888");
        
        Map<String, Object> model = new HashMap<>();
        model.put("title", "用户详情");
        model.put("user", user);
        
        processTemplate("profile.ftlh", model, response);
    }
    
    private void processTemplate(String templateName, Map<String, Object> model, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Template template = freemarkerConfig.getTemplate(templateName);
            PrintWriter writer = response.getWriter();
            template.process(model, writer);
            writer.flush();
        } catch (TemplateException e) {
            throw new ServletException("Error processing FreeMarker template: " + templateName, e);
        }
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