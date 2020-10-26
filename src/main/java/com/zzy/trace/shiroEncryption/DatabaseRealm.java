package com.zzy.trace.shiroEncryption;
import java.util.Set;
 
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
 
public class DatabaseRealm extends AuthorizingRealm {
 
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //能进入到这里，表示账号已经通过验证了
        String userName =(String) principalCollection.getPrimaryPrincipal();
        //通过DAO获取角色和权限
        Set<String> permissions = new shiroDAO().listPermissions(userName);
        Set<String> roles = new shiroDAO().listRoles(userName);
         
        //授权对象
        SimpleAuthorizationInfo s = new SimpleAuthorizationInfo();
        //把通过DAO获取到的角色和权限放进去
        s.setStringPermissions(permissions);
        s.setRoles(roles);
        return s;
    }
    /*
     *Authorization  授权， 认证通过后的操作 
      Authentication 认证，验证用户名密码是否正确 
    */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
    	int enc_time = 2;
        //获取前端输入的账号密码
        UsernamePasswordToken t = (UsernamePasswordToken) token;
        String userName= token.getPrincipal().toString();
        String password= new String( t.getPassword());
        
        //获取数据库存储的密码（加密后的） 和 盐 salt
        User user = new shiroDAO().getUser(userName);
        String passwordInDB = user.getPassword();
        String salt = user.getSalt();
        
        // self_compare 对应 shiro_enc.ini
        // shiro_provided 对应 shiro_enc2.ini
        String method = "shiro_provided"; 
        SimpleAuthenticationInfo a = null;
        switch (method) {
		case "self_compare":
		        //获取数据库中的密码  自己对输入的密码进行加密，然后比较--method-1
		        String passwordEncoded = new SimpleHash("md5",password,salt,enc_time).toString();
		        //如果为空就是账号不存在，如果不相同就是密码错误，但是都抛出AuthenticationException，而不是抛出具体错误原因，免得给破解者提供帮助信息
		        if(null==user || !passwordEncoded.equals(passwordInDB))
		            throw new AuthenticationException();
		        //认证信息里存放账号密码, getName() 是当前Realm的继承方法,通常返回当前类名 :databaseRealm
		        a = new SimpleAuthenticationInfo(userName,password,getName());
			break;
		case "shiro_provided":
		        //认证信息里存放账号密码, getName() 是当前Realm的继承方法,通常返回当前类名 :databaseRealm
		        //盐也放进去
		        //这样通过shiro.ini里配置的 HashedCredentialsMatcher 进行自动校验
		        a = new SimpleAuthenticationInfo(userName,passwordInDB,ByteSource.Util.bytes(salt),getName());
			break;
		default:
			break;
		}
        return a;
    }
}