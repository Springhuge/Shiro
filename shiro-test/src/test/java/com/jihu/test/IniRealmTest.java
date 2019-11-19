package com.jihu.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;

public class IniRealmTest {

    @Test
    public void testAuthentication(){
        IniRealm iniRealm = new IniRealm("classpath:user.ini");

        //1.创建SecurityManager环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(iniRealm);

        //2.主体提交认证请求
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("Mark","123456");
        subject.login(token);
        System.out.println("是否登录:"+subject.isAuthenticated());

        subject.checkRole("admin");
        subject.checkPermission("user:update");
    }
}
