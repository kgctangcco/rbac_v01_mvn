# rbac_v01_mvn
基于角色的管理servlet的maven版本

项目启动配置注意事项

  - 启动命令
    - jetty:run
    - tomcat7:run
    - 以上命令分别为tomcat容器和jetty容器启动命令
    - 默认使用端口为8080

  - 数据库配置在src/main/resources目录下c3p0-config.xml文件中

    - 为避免配置文件频繁修改统一讲数据库命名为rbac

    - 数据库账号为root

    - 数据库密码为123456

  - 该项目前端页面为html

  - 该项目使用ajax方式传值，

    - 为了将来实现页面和后台程序的分开部署所有的后端服务器地址需要在js文件统一配置后台服务器地址

    - 配置文件存放在json/serverconfig.json文件中
    
    - "protocol": "http://",
    
    - "domain": "localhost",
    
    - "port": ":8080",
    
    - "context": ""
  - login.js为json方式跨域提交，login1.js为传统表单方式提交

用户表演示数据

    +--------+-----------+----------+-----------+
    | userId | account   | password | nickname  |
    +--------+-----------+----------+-----------+
    |      1 | student01 | 123456   | 学生1     |
    |      2 | student02 | 123456   | 学生2     |
    |      3 | teacher01 | 123456   | 教师1     |
    |      4 | teacher02 | 123456   | 教师2     |
    |      5 | admin     | 123456   | 管理员    |
    +--------+-----------+----------+-----------+

