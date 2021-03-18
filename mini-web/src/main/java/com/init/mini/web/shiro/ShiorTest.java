package com.init.mini.web.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;

public class ShiorTest {

    private SimpleAccountRealm simpleAccountRealm = new SimpleAccountRealm();

    public void authentication() {
        // 1. 引入 shiro 框架,构建DefaultSecurityManager环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        // 2. 设置账号、角色
        simpleAccountRealm.addAccount("Mark", "123456", "admin", "super");
        defaultSecurityManager.setRealm(simpleAccountRealm);

        SecurityUtils.setSecurityManager(defaultSecurityManager);

        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("Mark", "123456");

        subject.login(usernamePasswordToken);

        System.out.println(subject.isAuthenticated());
        // 检查主体角色 Subject does not have role [admin1]
        subject.checkRole("admin");
//      // 检查主体多角色
        subject.checkRoles("admin", "super");
    }

    public static void main(String[] args) {
        ShiorTest shiorTest = new ShiorTest();
        shiorTest.authentication();
    }
}
