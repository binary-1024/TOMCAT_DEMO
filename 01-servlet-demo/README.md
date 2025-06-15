# Servlet Demo 项目

## 项目概述
这是Tomcat应用项目系列的第一个子项目，主要演示Servlet的基本功能和用法。

## 功能特性
- ✅ HttpServlet的基本使用
- ✅ GET和POST请求处理
- ✅ 请求参数获取和验证
- ✅ 响应数据输出（HTML和JSON）
- ✅ 初始化参数使用
- ✅ 字符编码Filter
- ✅ 完整的用户管理CRUD操作
- ✅ REST风格的URL设计
- ✅ 错误处理机制
- ✅ 响应式前端界面

## 项目结构
```
01-servlet-demo/
├── pom.xml                     # Maven配置文件
├── README.md                   # 项目说明文档
└── src/
    └── main/
        ├── java/
        │   └── com/
        │       └── example/
        │           └── servlet/
        │               ├── User.java                    # 用户实体类
        │               ├── HelloServlet.java            # 基本Servlet演示
        │               ├── UserServlet.java             # 用户管理Servlet
        │               └── CharacterEncodingFilter.java # 字符编码过滤器
        └── webapp/
            ├── WEB-INF/
            │   └── web.xml             # Web应用配置文件
            ├── css/
            │   └── style.css           # 样式文件
            ├── error/
            │   ├── 404.html           # 404错误页面
            │   └── 500.html           # 500错误页面
            └── index.html              # 首页
```

## 技术栈
- **Java**: JDK 8+
- **Servlet API**: 4.0.1
- **构建工具**: Maven
- **JSON处理**: Jackson
- **前端**: HTML5 + CSS3 + JavaScript
- **Web容器**: Tomcat 9.x

## 核心组件

### 1. HelloServlet
演示Servlet基本功能的类，支持：
- GET和POST请求处理
- 请求参数获取
- 初始化参数使用
- 响应HTML内容输出

**访问路径**: `/hello`

### 2. UserServlet
完整的用户管理系统，支持：
- 用户列表查看 (`/user/list`)
- 添加用户 (`/user/add`)
- 编辑用户 (`/user/edit/{id}`)
- 查看用户详情 (`/user/view/{id}`)
- 删除用户 (`/user/delete/{id}`)
- JSON API接口 (`/user/api/list`)

### 3. CharacterEncodingFilter
字符编码过滤器，确保：
- 请求使用UTF-8编码
- 响应使用UTF-8编码

## 快速开始

### 1. 编译项目
```bash
cd 01-servlet-demo
mvn clean compile
```

### 2. 打包项目
```bash
mvn clean package
```

### 3. 运行项目
```bash
# 使用Tomcat Maven插件运行
mvn tomcat7:run

# 或者部署war包到Tomcat服务器
cp target/servlet-demo.war $TOMCAT_HOME/webapps/
```

### 4. 访问应用
- 首页: http://localhost:8080/servlet-demo/
- HelloServlet: http://localhost:8080/servlet-demo/hello
- 用户管理: http://localhost:8080/servlet-demo/user/list

## 测试场景

### HelloServlet测试
1. **基本访问**: `GET /hello`
2. **带参数访问**: `GET /hello?name=张三&age=25` 
3. **POST提交**: 通过表单提交数据

### 用户管理测试
1. **查看用户列表**: `GET /user/list`
2. **添加用户**: `GET /user/add` 然后 `POST /user/add`
3. **编辑用户**: `GET /user/edit/1` 然后 `POST /user/edit/1`
4. **查看用户详情**: `GET /user/view/1`
5. **删除用户**: `GET /user/delete/1`
6. **API接口**: `GET /user/api/list` (返回JSON)

## 配置说明

### web.xml配置
- Servlet配置和映射
- Filter配置和映射  
- 初始化参数设置
- 错误页面配置
- 欢迎页面设置

### Maven配置
- Servlet API依赖
- Jackson JSON处理
- Maven编译插件
- Tomcat插件配置

## 注意事项
1. 本项目使用内存存储用户数据，重启后数据会丢失
2. 密码以明文存储，仅用于演示
3. 没有实现复杂的权限控制
4. 前端界面采用纯HTML+CSS，没有使用框架

## 扩展建议
1. 集成数据库存储
2. 添加用户认证和授权
3. 实现数据校验
4. 添加日志记录
5. 优化错误处理
6. 添加单元测试

## 下一步
完成Filter Demo子项目，演示各种Filter的使用。 