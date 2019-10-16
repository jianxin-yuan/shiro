import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import realm.UserRealm;

import java.util.Arrays;
import java.util.List;

/**
 * @author yuan
 * @date 2019/10/6 9:12 下午
 */
public class App {
    private static final Logger logger  = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        //创建工厂类
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        //得到securityManager
        DefaultSecurityManager securityManager = (DefaultSecurityManager)factory.getInstance();

        //将securityManager设置到本地线程(ThreadLocal)里
        SecurityUtils.setSecurityManager(securityManager);
        securityManager.setRealm(new UserRealm());

        //得到主体
        Subject subject = SecurityUtils.getSubject();

        //登录
        String userName = "zhangsan";
        String password = "123456";
        AuthenticationToken authenticationToken = new UsernamePasswordToken(userName, password);

        try {
            subject.login(authenticationToken);
        } catch (AuthenticationException e) {
            logger.debug("登录失败.用户名或密码错误");
            System.exit(0);
        }

        logger.debug("登录成功,当前登录人" + subject.getPrincipal());


        //subject.logout();
        //System.out.println("退出登录");
        //
        //if (subject.isAuthenticated()) {
        //    System.out.println("已登录");
        //}else {
        //    System.out.println("未登录");
        //}


        List<String> roles = Arrays.asList("role1", "role2", "role3");

        for (String role : roles) {
            logger.debug("具有" + role + "角色=" + subject.hasRole(role));
        }

        List<String> permissions = Arrays.asList("user:query", "user:add", "user:update", "user:delete", "user:export");
        for (String permission : permissions) {
            logger.debug("具有" + permission + "权限=" + subject.isPermitted(permission));
        }

    }
}
