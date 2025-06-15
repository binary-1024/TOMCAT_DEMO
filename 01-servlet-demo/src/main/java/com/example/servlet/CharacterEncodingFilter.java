package com.example.servlet;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 字符编码过滤器
 * 确保请求和响应都使用UTF-8编码
 */
public class CharacterEncodingFilter implements Filter {
    
    private String encoding = "UTF-8";
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 获取初始化参数中的编码设置
        String encodingParam = filterConfig.getInitParameter("encoding");
        if (encodingParam != null && !encodingParam.trim().isEmpty()) {
            this.encoding = encodingParam.trim();
        }
        System.out.println("CharacterEncodingFilter 初始化完成，使用编码：" + encoding);
    }
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        
        // 设置请求字符编码
        if (httpRequest.getCharacterEncoding() == null) {
            httpRequest.setCharacterEncoding(encoding);
        }
        
        // 设置响应字符编码
        httpResponse.setCharacterEncoding(encoding);
        
        // 继续执行过滤链
        chain.doFilter(request, response);
    }
    
    @Override
    public void destroy() {
        System.out.println("CharacterEncodingFilter 正在销毁...");
    }
} 