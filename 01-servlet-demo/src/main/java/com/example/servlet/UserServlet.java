package com.example.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * UserServlet - 演示用户管理功能
 * 实现用户的增删改查操作，支持REST风格的URL
 */
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    // 使用内存存储用户数据（生产环境应使用数据库）
    private static final Map<Integer, User> users = new ConcurrentHashMap<>();
    private static final AtomicInteger idGenerator = new AtomicInteger(1);
    private static final ObjectMapper objectMapper = new ObjectMapper();
    
    @Override
    public void init() throws ServletException {
        super.init();
        // 初始化一些示例数据
        initSampleData();
        System.out.println("UserServlet 初始化完成，已添加示例用户数据");
    }
    
    private void initSampleData() {
        users.put(1, new User(1, "张三", "zhangsan@example.com", "123456"));
        users.put(2, new User(2, "李四", "lisi@example.com", "123456"));
        users.put(3, new User(3, "王五", "wangwu@example.com", "123456"));
        idGenerator.set(4);
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String pathInfo = request.getPathInfo();
        response.setContentType("text/html;charset=UTF-8");
        
        if (pathInfo == null || pathInfo.equals("/")) {
            // 重定向到用户列表
            response.sendRedirect(request.getContextPath() + "/user/list");
            return;
        }
        
        if (pathInfo.equals("/list")) {
            // 显示用户列表
            showUserList(request, response);
        } else if (pathInfo.equals("/add")) {
            // 显示添加用户表单
            showAddUserForm(request, response);
        } else if (pathInfo.matches("/edit/\\d+")) {
            // 显示编辑用户表单
            int userId = Integer.parseInt(pathInfo.split("/")[2]);
            showEditUserForm(request, response, userId);
        } else if (pathInfo.matches("/view/\\d+")) {
            // 显示用户详情
            int userId = Integer.parseInt(pathInfo.split("/")[2]);
            showUserDetail(request, response, userId);
        } else if (pathInfo.matches("/delete/\\d+")) {
            // 删除用户
            int userId = Integer.parseInt(pathInfo.split("/")[2]);
            deleteUser(request, response, userId);
        } else if (pathInfo.equals("/api/list")) {
            // 返回JSON格式的用户列表
            getUserListAsJson(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "页面未找到");
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        String pathInfo = request.getPathInfo();
        
        if (pathInfo == null || pathInfo.equals("/add")) {
            // 添加用户
            addUser(request, response);
        } else if (pathInfo.matches("/edit/\\d+")) {
            // 更新用户
            int userId = Integer.parseInt(pathInfo.split("/")[2]);
            updateUser(request, response, userId);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "操作未找到");
        }
    }
    
    private void showUserList(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        
        PrintWriter out = response.getWriter();
        
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>用户列表</title>");
        out.println("<meta charset='UTF-8'>");
        out.println("<link rel='stylesheet' href='../css/style.css'>");
        out.println("</head>");
        out.println("<body>");
        
        out.println("<div class='container'>");
        out.println("<h1>用户管理系统</h1>");
        
        out.println("<div class='toolbar'>");
        out.println("<a href='add' class='btn btn-primary'>添加用户</a>");
        out.println("<a href='../hello' class='btn btn-secondary'>返回首页</a>");
        out.println("</div>");
        
        out.println("<table class='user-table'>");
        out.println("<thead>");
        out.println("<tr>");
        out.println("<th>ID</th>");
        out.println("<th>用户名</th>");
        out.println("<th>邮箱</th>");
        out.println("<th>创建时间</th>");
        out.println("<th>操作</th>");
        out.println("</tr>");
        out.println("</thead>");
        out.println("<tbody>");
        
        for (User user : users.values()) {
            out.println("<tr>");
            out.println("<td>" + user.getId() + "</td>");
            out.println("<td>" + user.getUsername() + "</td>");
            out.println("<td>" + user.getEmail() + "</td>");
            out.println("<td>" + user.getCreateTime() + "</td>");
            out.println("<td>");
            out.println("<a href='view/" + user.getId() + "' class='btn btn-info btn-sm'>查看</a>");
            out.println("<a href='edit/" + user.getId() + "' class='btn btn-warning btn-sm'>编辑</a>");
            out.println("<a href='delete/" + user.getId() + "' class='btn btn-danger btn-sm' onclick='return confirm(\"确定要删除吗？\")'>删除</a>");
            out.println("</td>");
            out.println("</tr>");
        }
        
        out.println("</tbody>");
        out.println("</table>");
        
        out.println("<div class='info-box'>");
        out.println("<h3>API接口</h3>");
        out.println("<p><a href='api/list'>获取JSON格式的用户列表</a></p>");
        out.println("</div>");
        
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }
    
    private void showAddUserForm(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        
        PrintWriter out = response.getWriter();
        
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>添加用户</title>");
        out.println("<meta charset='UTF-8'>");
        out.println("<link rel='stylesheet' href='../css/style.css'>");
        out.println("</head>");
        out.println("<body>");
        
        out.println("<div class='container'>");
        out.println("<h1>添加用户</h1>");
        
        out.println("<form method='post' action='add' class='user-form'>");
        out.println("<div class='form-group'>");
        out.println("<label for='username'>用户名:</label>");
        out.println("<input type='text' id='username' name='username' required>");
        out.println("</div>");
        
        out.println("<div class='form-group'>");
        out.println("<label for='email'>邮箱:</label>");
        out.println("<input type='email' id='email' name='email' required>");
        out.println("</div>");
        
        out.println("<div class='form-group'>");
        out.println("<label for='password'>密码:</label>");
        out.println("<input type='password' id='password' name='password' required>");
        out.println("</div>");
        
        out.println("<div class='form-actions'>");
        out.println("<button type='submit' class='btn btn-primary'>保存</button>");
        out.println("<a href='list' class='btn btn-secondary'>取消</a>");
        out.println("</div>");
        out.println("</form>");
        
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }
    
    private void addUser(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        // 简单验证
        if (username == null || username.trim().isEmpty() ||
            email == null || email.trim().isEmpty() ||
            password == null || password.trim().isEmpty()) {
            
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "所有字段都是必填的");
            return;
        }
        
        // 创建新用户
        int newId = idGenerator.getAndIncrement();
        User newUser = new User(newId, username.trim(), email.trim(), password);
        users.put(newId, newUser);
        
        // 重定向到用户列表
        response.sendRedirect("list");
    }
    
    private void showEditUserForm(HttpServletRequest request, HttpServletResponse response, int userId) 
            throws IOException {
        
        User user = users.get(userId);
        if (user == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "用户不存在");
            return;
        }
        
        PrintWriter out = response.getWriter();
        
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>编辑用户</title>");
        out.println("<meta charset='UTF-8'>");
        out.println("<link rel='stylesheet' href='../../css/style.css'>");
        out.println("</head>");
        out.println("<body>");
        
        out.println("<div class='container'>");
        out.println("<h1>编辑用户</h1>");
        
        out.println("<form method='post' action='../edit/" + userId + "' class='user-form'>");
        out.println("<div class='form-group'>");
        out.println("<label for='username'>用户名:</label>");
        out.println("<input type='text' id='username' name='username' value='" + user.getUsername() + "' required>");
        out.println("</div>");
        
        out.println("<div class='form-group'>");
        out.println("<label for='email'>邮箱:</label>");
        out.println("<input type='email' id='email' name='email' value='" + user.getEmail() + "' required>");
        out.println("</div>");
        
        out.println("<div class='form-group'>");
        out.println("<label for='password'>密码:</label>");
        out.println("<input type='password' id='password' name='password' value='" + user.getPassword() + "' required>");
        out.println("</div>");
        
        out.println("<div class='form-actions'>");
        out.println("<button type='submit' class='btn btn-primary'>更新</button>");
        out.println("<a href='../list' class='btn btn-secondary'>取消</a>");
        out.println("</div>");
        out.println("</form>");
        
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }
    
    private void updateUser(HttpServletRequest request, HttpServletResponse response, int userId) 
            throws IOException {
        
        User user = users.get(userId);
        if (user == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "用户不存在");
            return;
        }
        
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        // 简单验证
        if (username == null || username.trim().isEmpty() ||
            email == null || email.trim().isEmpty() ||
            password == null || password.trim().isEmpty()) {
            
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "所有字段都是必填的");
            return;
        }
        
        // 更新用户信息
        user.setUsername(username.trim());
        user.setEmail(email.trim());
        user.setPassword(password);
        
        // 重定向到用户列表
        response.sendRedirect("../list");
    }
    
    private void showUserDetail(HttpServletRequest request, HttpServletResponse response, int userId) 
            throws IOException {
        
        User user = users.get(userId);
        if (user == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "用户不存在");
            return;
        }
        
        PrintWriter out = response.getWriter();
        
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>用户详情</title>");
        out.println("<meta charset='UTF-8'>");
        out.println("<link rel='stylesheet' href='../../css/style.css'>");
        out.println("</head>");
        out.println("<body>");
        
        out.println("<div class='container'>");
        out.println("<h1>用户详情</h1>");
        
        out.println("<div class='user-detail'>");
        out.println("<div class='detail-item'>");
        out.println("<label>ID:</label>");
        out.println("<span>" + user.getId() + "</span>");
        out.println("</div>");
        
        out.println("<div class='detail-item'>");
        out.println("<label>用户名:</label>");
        out.println("<span>" + user.getUsername() + "</span>");
        out.println("</div>");
        
        out.println("<div class='detail-item'>");
        out.println("<label>邮箱:</label>");
        out.println("<span>" + user.getEmail() + "</span>");
        out.println("</div>");
        
        out.println("<div class='detail-item'>");
        out.println("<label>创建时间:</label>");
        out.println("<span>" + user.getCreateTime() + "</span>");
        out.println("</div>");
        out.println("</div>");
        
        out.println("<div class='form-actions'>");
        out.println("<a href='../edit/" + userId + "' class='btn btn-warning'>编辑</a>");
        out.println("<a href='../list' class='btn btn-secondary'>返回列表</a>");
        out.println("</div>");
        
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }
    
    private void deleteUser(HttpServletRequest request, HttpServletResponse response, int userId) 
            throws IOException {
        
        User user = users.remove(userId);
        if (user == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "用户不存在");
            return;
        }
        
        // 重定向到用户列表
        response.sendRedirect("../list");
    }
    
    private void getUserListAsJson(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        // 创建返回数据
        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        result.put("count", users.size());
        result.put("data", new ArrayList<>(users.values()));
        
        // 转换为JSON
        String json = objectMapper.writeValueAsString(result);
        out.println(json);
    }
} 