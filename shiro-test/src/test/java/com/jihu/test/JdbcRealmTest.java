package com.jihu.test;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

import javax.sql.DataSource;

public class JdbcRealmTest {

    DruidDataSource dataSource = new DruidDataSource();

    {
        dataSource.setUrl("jdbc:mysql://localhost:3306/test");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
    }

    @Test
    public void testAuthentication(){
        JdbcRealm jdbcRealm = new JdbcRealm();
        jdbcRealm.setDataSource(dataSource);
        //设置权限的开关
        jdbcRealm.setPermissionsLookupEnabled(true);

        String sql = "select password from test_user where user_name = ?";
        jdbcRealm.setAuthenticationQuery(sql);

        String roleSql = "select role_name from test_user_role where user_name = ?";
        jdbcRealm.setUserRolesQuery(roleSql);

        //1.创建SecurityManager环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(jdbcRealm);

        //2.主体提交认证请求
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("Mark","123456");
        subject.login(token);
        System.out.println("是否登录:"+subject.isAuthenticated());

//        subject.checkRoles("admin","user");
//        subject.checkPermission("user:select");
        subject.checkRole("user");
    }

}
