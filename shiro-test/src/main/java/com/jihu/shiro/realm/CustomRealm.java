package com.jihu.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CustomRealm extends AuthorizingRealm {

    Map<String,String> userMap = new HashMap<String,String>(16);

    {
        userMap.put("Mark","123456");
        super.setName("customRealm");
    }

    //用户授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String userName = (String) principalCollection.getPrimaryPrincipal();
        Set<String> roles = getRoleByUserName(userName);

        Set<String> permissions = getPermissionsByUserName(userName);

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(roles);
        authorizationInfo.setStringPermissions(permissions);
        return authorizationInfo;
    }

    private Set<String> getPermissionsByUserName(String userName) {
        Set<String> sets = new HashSet<>();
        sets.add("user:delete");
        sets.add("user:select");
        sets.add("user:update");
        return sets;
    }

    private Set<String> getRoleByUserName(String userName) {
        Set<String> sets = new HashSet<>();
        sets.add("admin");
        sets.add("user");
        return sets;
    }

    //登录认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        //1.从主体传过来的认证信息获得用户名
        String userName = (String) authenticationToken.getPrincipal();

        //2.通过用户名到数据库中获取凭证
        String password = getPasswordByUsername(userName);
        if(password == null){
            return null;
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo
                ("Mark",password,"customRealm");
        return authenticationInfo;
    }

    private String getPasswordByUsername(String userName) {
        return userMap.get(userName);
    }
}
