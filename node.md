什么时Shiro？

​	apache的强大灵活的开源安全框架。

​	认证、授权、企业会话、安全加密



Shiro与Spring Security比较

​	Shiro：

​		1.简单、灵活

​		2.可脱离Spring

​		3.粒度较粗

​	Spring Security：

​		1.复杂、笨重

​		2.不可脱离Spring

​		3.粒度更细



​	Shiro认证:

​		创建SecurityManager   --》

​		主体提交认证   --》 

​		SecurityManager认证   --》

​		Authenticator认证  --》

​		Realm验证



​                Shiro账号错误会抛出 UnknownAccountException

​		Shiro密码错误会抛出 IncorrectCredentialsException



​	Shiro授权：

​		创建SecurityManager --》

​		主体授权 --》

​		SecurityManager授权 --》

​		Authorizer授权 --》

​		Realm获取角色权限数据

​		

​		Shiro没有权限的时候会抛出 UnauthorizedException



​		