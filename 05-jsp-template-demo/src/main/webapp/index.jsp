<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
                    <li><a href="${pageContext.request.contextPath}/jsp/profile">用户详情</a></li>
                </ul>
            </nav>
        </div>
    </header>

    <main class="main">
        <div class="container">
            <div class="welcome-section">
                <h2>${title}</h2>
                <p class="message">${message}</p>
                <div class="info-card">
                    <h3>当前时间</h3>
                    <p class="time">
                        <fmt:formatDate value="${currentTime}" pattern="yyyy年MM月dd日 HH:mm:ss"/>
                    </p>
                </div>
            </div>

            <div class="features">
                <h3>JSP模板特性演示</h3>
                <div class="feature-grid">
                    <div class="feature-card">
                        <h4>JSTL标签库</h4>
                        <p>使用c:if、c:forEach等标签简化页面逻辑</p>
                        <c:if test="${not empty currentTime}">
                            <span class="status active">✓ 已加载</span>
                        </c:if>
                    </div>
                    
                    <div class="feature-card">
                        <h4>EL表达式</h4>
                        <p>使用\${}表达式访问数据</p>
                        <span class="status active">✓ 支持</span>
                    </div>
                    
                    <div class="feature-card">
                        <h4>页面包含</h4>
                        <p>支持页面片段的包含和重用</p>
                        <span class="status active">✓ 支持</span>
                    </div>
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