package com.init.mini.web.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;

public class DIYRealmTest {
    public void authentication() {
        // 1. 引入 shiro 框架,构建DefaultSecurityManager环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        DIYRealm diyRealm = new DIYRealm();
        defaultSecurityManager.setRealm(diyRealm);

        // 加密
        HashedCredentialsMatcher hcm = new HashedCredentialsMatcher();
        // 设置加密算法 realm 中密码要为加密后的密码
        hcm.setHashAlgorithmName("md5");
        // 设置加密次数
        hcm.setHashIterations(1);
        diyRealm.setCredentialsMatcher(hcm);

        SecurityUtils.setSecurityManager(defaultSecurityManager);

        Subject subject = SecurityUtils.getSubject();

        // 客户端
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("Mark", "123456");

        subject.login(usernamePasswordToken);

        System.out.println(subject.isAuthenticated());
        // 检查主体角色 Subject does not have role [admin1]
        subject.checkRole("admin");
//      // 检查主体多角色
        subject.checkRoles("admin", "user");
    }

    public static void main(String[] args) {
        DIYRealmTest shiorTest = new DIYRealmTest();
        shiorTest.authentication();
    }
}
