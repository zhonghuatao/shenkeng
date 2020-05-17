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
```text
mvn flyway:migrate
```
- Git
- Github oAuth
- okHttp
- Lombok(插件)


```sql
SHOW CREATE TABLE user;//查询建表语句
```
```sql
truncate table question;//清空表数据包括id
```
```text
mvn -Dmybatis.generator.overwrite=true mybatis-generator:generate
使用Mybatis Generator出现字段缺失把mysql数据库驱动版本降到5.X

```
