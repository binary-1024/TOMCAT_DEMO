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
                    <li><a href="${pageContext.request.contextPath}/jsp/users" class="active">用户列表</a></li>
                    <li><a href="${pageContext.request.contextPath}/jsp/profile">用户详情</a></li>
                </ul>
            </nav>
        </div>
    </header>

    <main class="main">
        <div class="container">
            <h2>${title}</h2>
            
            <div class="user-stats">
                <div class="stat-card">
                    <h3>总用户数</h3>
                    <p class="stat-number">${users.size()}</p>
                </div>
            </div>

            <div class="user-table-container">
                <table class="user-table">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>姓名</th>
                            <th>邮箱</th>
                            <th>年龄</th>
                            <th>操作</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="user" items="${users}" varStatus="status">
                            <tr class="${status.index % 2 == 0 ? 'even' : 'odd'}">
                                <td>${user.id}</td>
                                <td class="user-name">${user.name}</td>
                                <td>${user.email}</td>
                                <td>${user.age}岁</td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/jsp/profile?id=${user.id}" 
                                       class="btn btn-primary">查看详情</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>

            <div class="pagination">
                <c:if test="${users.size() > 0}">
                    <p class="pagination-info">
                        显示 1-${users.size()} 条记录，共 ${users.size()} 条
                    </p>
                </c:if>
                
                <c:if test="${empty users}">
                    <div class="empty-state">
                        <h3>暂无用户数据</h3>
                        <p>系统中还没有用户信息</p>
                    </div>
                </c:if>
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