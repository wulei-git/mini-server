package com.init.mini.web.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;

public class IniRealmTest {
    private IniRealm iniRealm = new IniRealm("classpath:user.ini");

    public void authentication() {
        // 1. 引入 shiro 框架,构建DefaultSecurityManager环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        // 2. 设置iniRealm
        defaultSecurityManager.setRealm(iniRealm);

        SecurityUtils.setSecurityManager(defaultSecurityManager);

        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("Mark", "123456");

        subject.login(usernamePasswordToken);

        System.out.println(subject.isAuthenticated());
        // 检查主体角色 Subject does not have role [admin1]
        subject.checkRole("admin");
//      // 检查主体多角色
//        subject.checkRoles("admin", "super");
        subject.checkPermission("user:delete");
        subject.checkPermission("user:update");

    }

    public static void main(String[] args) {
        IniRealmTest shiorTest = new IniRealmTest();
        shiorTest.authentication();
    }
}
