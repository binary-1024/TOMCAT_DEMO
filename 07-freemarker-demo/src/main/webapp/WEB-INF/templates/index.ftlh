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
                    <li><a href="/freemarker-demo/freemarker/" class="active">首页</a></li>
                    <li><a href="/freemarker-demo/freemarker/users">用户列表</a></li>
                    <li><a href="/freemarker-demo/freemarker/profile">用户详情</a></li>
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
                    <p class="time">${currentTime?datetime}</p>
                </div>
            </div>

            <div class="features">
                <h3>FreeMarker模板特性演示</h3>
                <div class="feature-grid">
                    <#list features as feature>
                        <div class="feature-card">
                            <h4>${feature.name}</h4>
                            <p>${feature.description}</p>
                            <#if feature.enabled>
                                <span class="status active">✓ 已启用</span>
                            <#else>
                                <span class="status inactive">✗ 未启用</span>
                            </#if>
                        </div>
                    </#list>
                </div>
            </div>

            <div class="freemarker-examples">
                <h3>FreeMarker语法示例</h3>
                <div class="example-grid">
                    <div class="example-card">
                        <h4>变量输出</h4>
                        <code>${'$'}{变量名}</code>
                        <div class="example-demo">
                            <p>标题: ${title}</p>
                        </div>
                    </div>
                    
                    <div class="example-card">
                        <h4>条件判断</h4>
                        <code>&lt;#if&gt; &lt;#else&gt; &lt;/#if&gt;</code>
                        <div class="example-demo">
                            <#if currentTime??>
                                <p class="success">✓ 当前时间已加载</p>
                            <#else>
                                <p class="error">✗ 当前时间未加载</p>
                            </#if>
                        </div>
                    </div>
                    
                    <div class="example-card">
                        <h4>循环遍历</h4>
                        <code>&lt;#list items as item&gt;</code>
                        <div class="example-demo">
                            <ul>
                                <#list features as feature>
                                    <li>${feature.name}</li>
                                </#list>
                            </ul>
                        </div>
                    </div>

                    <div class="example-card">
                        <h4>内置函数</h4>
                        <code>?size, ?upper_case</code>
                        <div class="example-demo">
                            <p>特性数量: ${features?size}</p>
                            <p>大写标题: ${title?upper_case}</p>
                        </div>
                    </div>
                </div>
            </div>

            <div class="macro-example">
                <h3>宏定义示例</h3>
                
                <#-- 定义一个简单的宏 -->
                <#macro button text color="primary">
                    <button class="btn btn-${color}">${text}</button>
                </#macro>
                
                <div class="macro-demo">
                    <p>使用宏生成按钮:</p>
                    <@button text="主要按钮" />
                    <@button text="成功按钮" color="success" />
                    <@button text="警告按钮" color="warning" />
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