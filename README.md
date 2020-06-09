##文档
**本项目使用到的技能点**
- Spring Boot
- Spring MVC
- MyBatis
- Mybatis Generator
- Thymeleaf(前端页面)
- BootStrap(前端页面)
- Mysql
- Flyway
- ajax
- Git
- Github oAuth
- okHttp
- Lombok(插件)
- postMan

```sql
SHOW CREATE TABLE user;/*查询建表语句*/
```
```sql
truncate table question;/*清空表数据包括id*/
```
```text
mvn flyway:migrate;//flyway执行sql语句
mvn -Dmybatis.generator.overwrite=true mybatis-generator:generate//flyway生成xml文件
使用Mybatis Generator出现字段缺失把mysql数据库驱动版本降到5.X
```
