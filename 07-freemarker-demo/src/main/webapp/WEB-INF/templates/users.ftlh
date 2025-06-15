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
                    <li><a href="/freemarker-demo/freemarker/users" class="active">用户列表</a></li>
                    <li><a href="/freemarker-demo/freemarker/profile">用户详情</a></li>
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
                    <p class="stat-number">${users?size}</p>
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
                        <#list users as user>
                            <tr class="<#if user_index % 2 == 0>even<#else>odd</#if>">
                                <td>${user.id}</td>
                                <td class="user-name">${user.name}</td>
                                <td>${user.email}</td>
                                <td>${user.age}岁</td>
                                <td>
                                    <a href="/freemarker-demo/freemarker/profile?id=${user.id}" 
                                       class="btn btn-primary">查看详情</a>
                                </td>
                            </tr>
                        </#list>
                    </tbody>
                </table>
            </div>

            <div class="pagination">
                <#if users?has_content>
                    <p class="pagination-info">
                        显示 1-${users?size} 条记录，共 ${users?size} 条
                    </p>
                <#else>
                    <div class="empty-state">
                        <h3>暂无用户数据</h3>
                        <p>系统中还没有用户信息</p>
                    </div>
                </#if>
            </div>

            <div class="features">
                <h3>FreeMarker特性演示</h3>
                <div class="feature-grid">
                    <div class="feature-card">
                        <h4>列表遍历</h4>
                        <p>使用&lt;#list&gt;指令遍历集合</p>
                        <span class="status active">✓ 支持</span>
                    </div>
                    
                    <div class="feature-card">
                        <h4>条件判断</h4>
                        <p>使用&lt;#if&gt;进行条件控制</p>
                        <span class="status active">✓ 支持</span>
                    </div>
                    
                    <div class="feature-card">
                        <h4>内置函数</h4>
                        <p>?size、?has_content等内置函数</p>
                        <span class="status active">✓ 支持</span>
                    </div>
                    
                    <div class="feature-card">
                        <h4>索引变量</h4>
                        <p>user_index提供循环索引</p>
                        <span class="status active">✓ 支持</span>
                    </div>
                </div>
            </div>

            <#-- FreeMarker宏演示 -->
            <#macro userCard user>
                <div class="user-card">
                    <h4>${user.name}</h4>
                    <p>邮箱: ${user.email}</p>
                    <p>年龄: ${user.age}岁</p>
                </div>
            </#macro>

            <div class="features">
                <h3>宏定义演示</h3>
                <div class="feature-grid">
                    <#list users as user>
                        <@userCard user=user />
                    </#list>
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