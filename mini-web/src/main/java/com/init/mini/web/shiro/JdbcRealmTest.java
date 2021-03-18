package com.init.mini.web.shiro;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.Subject;

public class JdbcRealmTest {

    DruidDataSource dataSource = new DruidDataSource();

    /**
     * java 代码执行顺序
     * 1. 父类静态
     * 2. 子类静态
     * 3. 父类构造代码块
     * 4. 子类构造代码块
     * 5. 父类构造方法
     * 6. 子类构造方法
     */
    {
        dataSource.setUrl("jdbc:mysql://localhost:3306/jeesite?useUnicode=true&characterEncoding=utf-8&useSSL=true&serverTimezone=UTC");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
    }

    public void auth() {
        JdbcRealm jdbcRealm = new JdbcRealm();
        jdbcRealm.setDataSource(dataSource);
        // 默认不查询权限信息
        jdbcRealm.setPermissionsLookupEnabled(true);

        // 使用表和字段不是 shiro 框架定义,自定义 SQL
//        String sql = "select password from user_test where user_name = ?";
//        jdbcRealm.setAuthenticationQuery(sql);
//        jdbcRealm.setUserRolesQuery(null);
//        jdbcRealm.setPermissionsQuery(null);

        DefaultSecurityManager dsm = new DefaultSecurityManager();
        dsm.setRealm(jdbcRealm);
        SecurityUtils.setSecurityManager(dsm);
        Subject sub = SecurityUtils.getSubject();
        UsernamePasswordToken upt = new UsernamePasswordToken("Mark", "123456");
        sub.login(upt);
        System.out.println(sub.isAuthenticated());
        sub.checkRole("admin");
        sub.checkRoles("user", "admin");
        sub.checkPermission("user:select");
    }

    public static void main(String[] args) {
        JdbcRealmTest shiorTest = new JdbcRealmTest();
        shiorTest.auth();
    }
}
