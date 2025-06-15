package com.example.servlet;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * Velocity模板演示Servlet
 */
@WebServlet("/velocity/*")
public class VelocityServlet extends HttpServlet {

    private VelocityEngine velocityEngine;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        initializeVelocity();
    }

    private void initializeVelocity() throws ServletException {
        try {
            // 创建Velocity引擎
            velocityEngine = new VelocityEngine();
            
            // 配置Velocity属性
            Properties props = new Properties();
            props.setProperty(RuntimeConstants.RESOURCE_LOADERS, "classpath");
            props.setProperty("resource.loader.classpath.class", 
                            "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
            props.setProperty("resource.loader.classpath.cache", "false");
            props.setProperty("resource.loader.classpath.modification_check_interval", "0");
            props.setProperty(RuntimeConstants.INPUT_ENCODING, "UTF-8");
            props.setProperty(RuntimeConstants.ENCODING_DEFAULT, "UTF-8");
            
            // 初始化引擎
            velocityEngine.init(props);
            
        } catch (Exception e) {
            throw new ServletException("Failed to initialize Velocity", e);
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
        
        VelocityContext context = new VelocityContext();
        context.put("title", "Velocity模板引擎演示");
        context.put("message", "欢迎使用Velocity模板引擎！");
        context.put("currentTime", new java.util.Date());
        context.put("features", Arrays.asList(
            new Feature("简单语法", "简洁易学的模板语法", true),
            new Feature("高性能", "编译型模板引擎，性能优秀", true),
            new Feature("工具集成", "丰富的内置工具类", true),
            new Feature("可扩展", "支持自定义指令和函数", true)
        ));
        
        processTemplate("index.vm", context, response);
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
        
        VelocityContext context = new VelocityContext();
        context.put("title", "用户列表");
        context.put("users", users);
        
        processTemplate("users.vm", context, response);
    }

    private void showProfile(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        User user = new User(1, "张三", "zhangsan@example.com", 25);
        user.setAddress("北京市朝阳区");
        user.setPhone("13888888888");
        
        VelocityContext context = new VelocityContext();
        context.put("title", "用户详情");
        context.put("user", user);
        
        processTemplate("profile.vm", context, response);
    }
    
    private void processTemplate(String templateName, VelocityContext context, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Template template = velocityEngine.getTemplate(templateName, "UTF-8");
            StringWriter writer = new StringWriter();
            template.merge(context, writer);
            
            response.getWriter().write(writer.toString());
            response.getWriter().flush();
        } catch (Exception e) {
            throw new ServletException("Error processing Velocity template: " + templateName, e);
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