### 本`Demo`使用 `Spring Boot Starter` 实现微信公众号消息推送功能。




1. 修改对应配置为正确的配置，

   1.执行建表的sql语句sql文件夹下面

    2.修改main/resources/application.yml  6行的数据库链接和库名称

   ![](/file/1661264265405.jpg)

   3.修改src\main\java\demo\wx\mp\service\impl\VxPushMsgServiceImpl.java

   修改32行和33行填写你自己的 appId和secret

   ```
   public static final String appId = "**";
   public static final String secret = "**";
   ```

   修改93行，只需要修改你自己的高德API的KEY就可以了，没有就去申请一个

   ```
   String usrl="https://restapi.amap.com/v3/weather/weatherInfo?city={{0}}&key=KEY";

   ```

2. 运行DemoApplication，访问 ：`localhost:8087`

3. 登录账号，普通账号为：test 密码：123456@，管理员账号：admin 密码：zl206518，可自行在src\main\java\demo.wx.mp.config.SecurityConfig.java   37行和39行处修改

4.测试公众地址 https://mp.weixin.qq.com/debug/cgi-bin/sandboxinfo?action=showinfo&t=sandbox/index

5.消息模板

{{name.DATA}} 
历史上的今天：{{history.DATA}} 
当前时间：{{time.DATA}} 
所在城市：{{city.DATA}} 
城市编码：{{code.DATA}} 
当前气温：{{temp.DATA}} 
天气：{{weather.DATA}} 
风向： {{windDirection.DATA}} 
湿度： {{humidity.DATA}} 
{{love.DATA}}
