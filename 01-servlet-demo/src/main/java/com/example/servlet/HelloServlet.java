package com.example.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Enumeration;

/**
 * HelloServlet - 演示Servlet基本功能
 * 处理GET和POST请求，展示参数获取、初始化参数使用等功能
 */
public class HelloServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private String greeting;
    
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        // 获取初始化参数
        greeting = config.getInitParameter("greeting");
        if (greeting == null) {
            greeting = "Hello, Servlet World!";
        }
        System.out.println("HelloServlet 初始化完成，问候语：" + greeting);
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // 设置响应内容类型和字符编码
        response.setContentType("text/html;charset=UTF-8");
        
        // 获取请求参数
        String name = request.getParameter("name");
        String age = request.getParameter("age");
        
        PrintWriter out = response.getWriter();
        
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>HelloServlet演示</title>");
        out.println("<meta charset='UTF-8'>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; margin: 40px; }");
        out.println(".container { max-width: 800px; margin: 0 auto; }");
        out.println(".info-box { background: #f0f8ff; padding: 20px; border-radius: 5px; margin: 10px 0; }");
        out.println(".param-box { background: #f5f5f5; padding: 15px; border-radius: 5px; margin: 10px 0; }");
        out.println("h1 { color: #333; }");
        out.println("h2 { color: #666; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        
        out.println("<div class='container'>");
        out.println("<h1>" + greeting + "</h1>");
        out.println("<div class='info-box'>");
        out.println("<h2>服务器信息</h2>");
        out.println("<p><strong>当前时间:</strong> " + new Date() + "</p>");
        out.println("<p><strong>Servlet名称:</strong> " + getServletName() + "</p>");
        out.println("<p><strong>请求方法:</strong> " + request.getMethod() + "</p>");
        out.println("<p><strong>请求URI:</strong> " + request.getRequestURI() + "</p>");
        out.println("<p><strong>客户端IP:</strong> " + request.getRemoteAddr() + "</p>");
        out.println("</div>");
        
        // 显示请求参数
        if (name != null || age != null) {
            out.println("<div class='param-box'>");
            out.println("<h2>接收到的参数</h2>");
            if (name != null) {
                out.println("<p><strong>姓名:</strong> " + name + "</p>");
            }
            if (age != null) {
                out.println("<p><strong>年龄:</strong> " + age + "</p>");
            }
            out.println("</div>");
        }
        
        // 显示所有请求参数
        out.println("<div class='param-box'>");
        out.println("<h2>所有请求参数</h2>");
        Enumeration<String> paramNames = request.getParameterNames();
        if (paramNames.hasMoreElements()) {
            out.println("<ul>");
            while (paramNames.hasMoreElements()) {
                String paramName = paramNames.nextElement();
                String[] paramValues = request.getParameterValues(paramName);
                out.println("<li><strong>" + paramName + ":</strong> ");
                for (int i = 0; i < paramValues.length; i++) {
                    out.println(paramValues[i]);
                    if (i < paramValues.length - 1) {
                        out.println(", ");
                    }
                }
                out.println("</li>");
            }
            out.println("</ul>");
        } else {
            out.println("<p>暂无请求参数</p>");
        }
        out.println("</div>");
        
        // 显示测试链接
        out.println("<div class='info-box'>");
        out.println("<h2>测试链接</h2>");
        out.println("<ul>");
        out.println("<li><a href='hello?name=张三&age=25'>测试带参数的GET请求</a></li>");
        out.println("<li><a href='hello'>不带参数的GET请求</a></li>");
        out.println("<li><a href='user/list'>查看用户列表</a></li>");
        out.println("<li><a href='index.html'>返回首页</a></li>");
        out.println("</ul>");
        out.println("</div>");
        
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // 设置请求字符编码
        request.setCharacterEncoding("UTF-8");
        
        // POST请求的处理逻辑
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>HelloServlet POST处理</title>");
        out.println("<meta charset='UTF-8'>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; margin: 40px; }");
        out.println(".container { max-width: 800px; margin: 0 auto; }");
        out.println(".success-box { background: #d4edda; padding: 20px; border-radius: 5px; border: 1px solid #c3e6cb; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        
        out.println("<div class='container'>");
        out.println("<div class='success-box'>");
        out.println("<h1>POST请求处理成功</h1>");
        out.println("<p>您通过POST方法提交了以下数据：</p>");
        
        // 显示POST数据
        out.println("<ul>");
        Enumeration<String> paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String paramName = paramNames.nextElement();
            String paramValue = request.getParameter(paramName);
            out.println("<li><strong>" + paramName + ":</strong> " + paramValue + "</li>");
        }
        out.println("</ul>");
        
        out.println("<p><a href='hello'>返回GET页面</a></p>");
        out.println("</div>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }
    
    @Override
    public void destroy() {
        System.out.println("HelloServlet 正在销毁...");
        super.destroy();
    }
} 