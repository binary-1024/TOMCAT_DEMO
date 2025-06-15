<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${title} - FreeMarker模板演示</title>
    <link rel="stylesheet" href="/freemarker-demo/css/style.css">
</head>
<body>
    <header class="header">
        <div class="container">
            <h1>FreeMarker模板引擎演示</h1>
            <nav>
                <ul>
                    <li><a href="/freemarker-demo/freemarker/">首页</a></li>
                    <li><a href="/freemarker-demo/freemarker/users">用户列表</a></li>
                    <li><a href="/freemarker-demo/freemarker/profile" class="active">用户详情</a></li>
                </ul>
            </nav>
        </div>
    </header>

    <main class="main">
        <div class="container">
            <h2>${title}</h2>
            
            <div class="profile-card">
                <div class="profile-header">
                    <h3>${user.name}</h3>
                    <p>${user.email}</p>
                </div>

                <div class="profile-details">
                    <p>用户ID: ${user.id}</p>
                    <p>姓名: ${user.name}</p>
                    <p>邮箱: ${user.email}</p>
                    <p>年龄: ${user.age}岁</p>
                    
                    <#if user.address??>
                        <p>地址: ${user.address}</p>
                    </#if>
                    
                    <#if user.phone??>
                        <p>电话: ${user.phone}</p>
                    </#if>
                </div>

                <div class="profile-actions">
                    <a href="/freemarker-demo/freemarker/users" class="btn">返回列表</a>
                </div>
            </div>
        </div>
    </main>

    <footer class="footer">
        <div class="container">
            <p>&copy; 2024 FreeMarker模板演示项目</p>
        </div>
    </footer>
</body>
</html> 