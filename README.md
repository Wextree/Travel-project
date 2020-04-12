# Travel-project
SSM学习完的一个练手小项目
旅游开发网项目

##STAR

S：刚学完ssm框架之后进行一次复习回顾，并且学习一些新的知识点

T：做一个旅游网的小型项目，实现产品，订单，用户，角色，资源权限相关的功能

A：因为我主要是后台开发，所以前端任务会比较少。我就先搞定前端部分内容。这次使用的是adminLTE这个开源的模板主题工具，这个工具是建立在jQuery和Bootstrap之上的，直接从GitHub上拷贝下来就好，通过选取不同页面上的组件进行组装，从而形成自己的页面，可以简单达到不错的渲染效果和响应时布局。而后台的开发网我分为三个阶段：数据库和数据表的确定，系统功能的实现，权限的设定。数据表的设定是从功能上先确定所需要的表以及表的数据内容。然后再根据表之间的关系，是一对多还是一对一，还有表之间是否符合范式规则，进行外键的确立还有表的分离，如果是多对多，还需要建立一张对应的关联表。确定表之后进行类的设定，除了一些数据表的基本元素还有对应的一些字符串变量用于转换，外键用类或者类的集合进行设定。功能实现环节，直接建立一个maven工程，创建......子模块，然后进行基础的配置，比如数据库配置文件，MyBatis配置文件，扫描的包，还有springmvc视图解析器的配置，还有web里面一些监听器和前端控制器的配置。所有的功能都是按照先做好所需要页面，在页面里面设置对应的连接，然后写Controller-Service-Dao流程进行。功能的实现从产品相关-订单-用户-角色-资源权限。权限设定是有security操作的。方法级的权限控制在于jsr-250，页面级的权限控制在于taglibs。最后增加了一个AOP日志。

R：基本完成了页面的显示和运行。



##issue

1. 权限控制时，需要返回一个user类，但是我自己定义了一个user类，发生了冲突，然后一直没办法调用：改名。
2. user参数，password前加{noop}，角色要用列表。
3. 使用taglibs需要启用spel，但是这样一开始设定的是没有role前缀，就要加上。
4. 日期输入的格式转换。
5. 没有用principal获取当前用户名。
6. 多表操作。



## 数据库优化

>由于数据库表比较小，而且字段少，所以暂时先按照sql语句的优化和建立索引两种方式进行



1. 订单列表展示产品相关信息时对投影的*改为具体的列，但由于之后有其他功能也调用这个接口，所以重新分离出一个接口。

```mysql
-- java/com/dao/ProductDao
-- 原来的引用以及查询语句
@Result(column = "productId", property = "product", one = @One(
                    select = "com.dao.ProductDao.findById"))
select productName,productPrice from product where id = #{id}

-- 改动后
select productName,productPrice from product where id = #{id}
```

2. 同理改动security获取用户名密码等的sql语句。

```mysql
-- java/com/dao/UserDao
-- 原来
select * from users where username=#{username}
select * from users
select username from users where id = #{id}

-- 改动后
select id,username,password,STATUS from users where username=#{username}
select id,username,email,phoneNum,status from users
select username from users where id = #{id}
```

3. 为username建立一个普通索引

```sql
create index in_name on users(username);
```

