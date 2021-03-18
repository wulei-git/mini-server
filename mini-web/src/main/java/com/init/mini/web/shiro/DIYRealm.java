package com.init.mini.web.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DIYRealm extends AuthorizingRealm {

    Map<String, String> userMap = new HashMap<>(16);
    {
        // 密码加密加盐
        userMap.put("Mark", new Md5Hash("123456", "Mark").toString());
        super.setName("DIYRealm");
    }
    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String userName = (String)principalCollection.getPrimaryPrincipal();
        // 模拟获取角色数据
        Set<String> roleSet = getRoleSetByUserName(userName);
        // 模拟获取权限数据
        Set<String> permSet = getPermByUserName(userName);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setRoles(roleSet);
        info.setStringPermissions(permSet);
        return info;
    }

    /**
     * 认证
     * @param authenticationToken 主体传传过来的主体认证信息
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 1. 获取认证信息的用户名
        String userName = (String) authenticationToken.getPrincipal();
        // 2. 通过 userName 到数据库中获取密码凭证
        String password = getPasswordByUserName(userName);
        if (password == null) return null;

        SimpleAuthenticationInfo authInfo = new SimpleAuthenticationInfo(userName, password, "DIYRealm");
        authInfo.setCredentialsSalt(ByteSource.Util.bytes("Mark"));
        return authInfo;
    }

    /**
     * 模拟数据库获取密码
     *
     * @param userName
     * @return
     */
    private String getPasswordByUserName(String userName) {
        return userMap.get(userName);
    }

    private Set<String> getRoleSetByUserName(String userName) {
        Set<String> set = new HashSet<>();
        set.add("admin");
        set.add("user");
        return set;
    }

    private Set<String> getPermByUserName(String userName) {
        Set<String> set = new HashSet<>();
        set.add("user:add");
        set.add("user:delete");
        return set;
    }
}
