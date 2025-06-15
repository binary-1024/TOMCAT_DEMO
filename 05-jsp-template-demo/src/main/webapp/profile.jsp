<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${title} - JSP模板演示</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <header class="header">
        <div class="container">
            <h1>JSP模板引擎演示</h1>
            <nav>
                <ul>
                    <li><a href="${pageContext.request.contextPath}/jsp/">首页</a></li>
                    <li><a href="${pageContext.request.contextPath}/jsp/users">用户列表</a></li>
                    <li><a href="${pageContext.request.contextPath}/jsp/profile" class="active">用户详情</a></li>
                </ul>
            </nav>
        </div>
    </header>

    <main class="main">
        <div class="container">
            <h2>${title}</h2>
            
            <div class="profile-card">
                <div class="profile-header">
                    <div class="avatar">
                        <span class="avatar-text">${user.name.substring(0,1)}</span>
                    </div>
                    <div class="profile-info">
                        <h3>${user.name}</h3>
                        <p class="profile-email">${user.email}</p>
                    </div>
                </div>

                <div class="profile-details">
                    <div class="detail-group">
                        <label>用户ID：</label>
                        <span>${user.id}</span>
                    </div>
                    
                    <div class="detail-group">
                        <label>姓名：</label>
                        <span>${user.name}</span>
                    </div>
                    
                    <div class="detail-group">
                        <label>邮箱：</label>
                        <span>${user.email}</span>
                    </div>
                    
                    <div class="detail-group">
                        <label>年龄：</label>
                        <span>${user.age}岁</span>
                    </div>
                    
                    <c:if test="${not empty user.address}">
                        <div class="detail-group">
                            <label>地址：</label>
                            <span>${user.address}</span>
                        </div>
                    </c:if>
                    
                    <c:if test="${not empty user.phone}">
                        <div class="detail-group">
                            <label>电话：</label>
                            <span>${user.phone}</span>
                        </div>
                    </c:if>
                </div>

                <div class="profile-actions">
                    <button class="btn btn-primary">编辑用户</button>
                    <button class="btn btn-secondary">发送消息</button>
                    <a href="${pageContext.request.contextPath}/jsp/users" class="btn btn-outline">返回列表</a>
                </div>
            </div>
        </div>
    </main>

    <footer class="footer">
        <div class="container">
            <p>&copy; 2024 JSP模板演示项目</p>
        </div>
    </footer>
</body>
</html> 