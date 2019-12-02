package shiroEncryption;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
//match to shiro_enc.ini
public class TestShiroENC {
    public static void main(String[] args) {
//      new shiroDAO().createUser("tom", "123");//用于注册一个用户
         
        User user = new User();
        user.setName("tom");
        user.setPassword("123");
        if(login(user))
            System.out.println(user.getName() + ":登录成功");
        else
            System.out.println(user.getName() + ":登录失败");
    }
     
    private static boolean hasRole(User user, String role) {
        Subject subject = getSubject();
        return subject.hasRole(role);
    }
     
    private static boolean isPermitted(User user, String permit) {
        Subject subject = getSubject();
        return subject.isPermitted(permit);
    }
 
    private static Subject getSubject() {
        //加载配置文件，并获取工厂
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro_enc.ini");
        //获取安全管理者实例
        org.apache.shiro.mgt.SecurityManager sm = factory.getInstance();
        //将安全管理者放入全局对象
        SecurityUtils.setSecurityManager(sm);
        //全局对象通过安全管理者生成Subject对象
        Subject subject = SecurityUtils.getSubject();
 
        return subject;
    }
     
    private static boolean login(User user) {
        Subject subject= getSubject();
        //如果已经登录过了，退出
        if(subject.isAuthenticated())
            subject.logout();
         
        //封装用户的数据
        UsernamePasswordToken token = new UsernamePasswordToken(user.getName(), user.getPassword());
        try {
            //将用户的数据token 最终传递到Realm中进行对比
            subject.login(token);
        } catch (AuthenticationException e) {
            //验证错误
            return false;
        }              
         
        return subject.isAuthenticated();
    }
}
