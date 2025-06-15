# Java模板引擎演示项目

基于原始的 `servlet-demo` 项目，创建了多个使用不同模板引擎的演示项目，展示各种模板技术的特性和用法。

## 项目列表

### 1. JSP模板项目 (05-jsp-template-demo)
- **端口**: 8081
- **访问地址**: http://localhost:8081/jsp-template-demo/jsp/
- **特点**: 
  - 使用JSTL标签库
  - EL表达式支持
  - 页面包含和重用
  - 内置国际化支持

### 2. Thymeleaf模板项目 (06-thymeleaf-demo)
- **端口**: 8082
- **访问地址**: http://localhost:8082/thymeleaf-demo/thymeleaf/
- **特点**:
  - 自然模板（标准HTML）
  - 强大的表达式引擎
  - 内置工具对象
  - Spring集成友好

### 3. FreeMarker模板项目 (07-freemarker-demo)
- **端口**: 8083
- **访问地址**: http://localhost:8083/freemarker-demo/freemarker/
- **特点**:
  - 强大的表达式语言
  - 宏定义支持
  - 丰富的内置函数
  - 高性能缓存机制

### 4. Velocity模板项目 (08-velocity-demo)
- **端口**: 8084
- **访问地址**: http://localhost:8084/velocity-demo/velocity/
- **特点**:
  - 简洁的模板语法
  - 宏和指令支持
  - 静默引用处理
  - 高性能编译缓存

### 5. Mustache模板项目 (09-mustache-demo)
- **端口**: 8085
- **访问地址**: http://localhost:8085/mustache-demo/mustache/
- **特点**:
  - 逻辑无关模板
  - 跨语言支持
  - 极简语法
  - 自动HTML转义

## 启动和运行

### 方式一：使用Maven插件启动

每个项目都配置了Tomcat Maven插件，可以直接启动：

```bash
# 启动JSP项目
cd 05-jsp-template-demo
mvn tomcat7:run

# 启动Thymeleaf项目
cd 06-thymeleaf-demo
mvn tomcat7:run

# 启动FreeMarker项目
cd 07-freemarker-demo
mvn tomcat7:run

# 启动Velocity项目
cd 08-velocity-demo
mvn tomcat7:run

# 启动Mustache项目
cd 09-mustache-demo
mvn tomcat7:run
```

### 方式二：打包部署到Tomcat

1. 编译打包项目：
```bash
mvn clean package
```

2. 将生成的WAR文件复制到Tomcat的webapps目录：
```bash
cp target/*.war $TOMCAT_HOME/webapps/
```

3. 启动Tomcat服务器

## 项目结构

每个项目都遵循标准的Maven Web项目结构：

```
项目名/
├── pom.xml                           # Maven配置文件
├── src/
│   └── main/
│       ├── java/
│       │   └── com/example/servlet/  # Servlet类
│       ├── webapp/
│       │   ├── css/                  # 样式文件
│       │   └── WEB-INF/
│       │       ├── web.xml           # Web配置
│       │       └── templates/        # 模板文件
│       └── resources/                # 资源文件（部分项目）
└── target/                           # 编译输出目录
```

## 模板引擎语法对比

### 变量输出
- **JSP**: `${变量名}`
- **Thymeleaf**: `th:text="${变量名}"`
- **FreeMarker**: `${变量名}`
- **Velocity**: `$变量名`
- **Mustache**: `{{变量名}}`

### 条件判断
- **JSP**: `<c:if test="${条件}">...</c:if>`
- **Thymeleaf**: `th:if="${条件}"`
- **FreeMarker**: `<#if 条件>...</#if>`
- **Velocity**: `#if($条件)...#end`
- **Mustache**: `{{#条件}}...{{/条件}}`

### 循环遍历
- **JSP**: `<c:forEach var="item" items="${list}">...</c:forEach>`
- **Thymeleaf**: `th:each="item : ${list}"`
- **FreeMarker**: `<#list list as item>...</#list>`
- **Velocity**: `#foreach($item in $list)...#end`
- **Mustache**: `{{#list}}...{{/list}}`

## 特性演示页面

每个项目都包含三个演示页面：

1. **首页**: 展示模板引擎的基本语法和特性
2. **用户列表页**: 演示数据循环和表格渲染
3. **用户详情页**: 演示条件判断和数据展示

## 依赖管理

各项目的核心依赖：

| 项目 | 核心依赖 | 版本 |
|------|----------|------|
| JSP | javax.servlet.jsp-api, jstl | 2.3.3, 1.2 |
| Thymeleaf | thymeleaf | 3.0.15.RELEASE |
| FreeMarker | freemarker | 2.3.32 |
| Velocity | velocity-engine-core | 2.3 |
| Mustache | mustache-java | 0.9.10 |

## 性能对比

各模板引擎的特点对比：

| 特性 | JSP | Thymeleaf | FreeMarker | Velocity | Mustache |
|------|-----|-----------|------------|----------|----------|
| 学习曲线 | 中等 | 中等 | 较难 | 简单 | 最简单 |
| 性能 | 很高 | 中等 | 高 | 高 | 高 |
| 功能丰富度 | 高 | 很高 | 很高 | 高 | 低 |
| 语法复杂度 | 中等 | 中等 | 较高 | 简单 | 最简单 |
| Spring集成 | 好 | 优秀 | 好 | 好 | 一般 |

## 注意事项

1. 各项目使用不同端口避免冲突
2. 确保Java 8+和Maven环境已配置
3. 模板文件编码统一使用UTF-8
4. 建议按需选择适合的模板引擎

## 扩展建议

1. 添加更多复杂的业务场景演示
2. 集成数据库操作
3. 添加国际化支持
4. 集成Spring框架
5. 添加单元测试 