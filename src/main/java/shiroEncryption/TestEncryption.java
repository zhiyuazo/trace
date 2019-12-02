package shiroEncryption;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;

import cn.hutool.crypto.digest.DigestUtil;

public class TestEncryption {
    public static void main(String[] args) {
        String password = "123";
        String encodedPassword = new Md5Hash(password).toString();
        System.out.println(encodedPassword);
        
        String password1 = "123";
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        int times = 2;
        String algorithmName = "md5";
             
        String encodedPassword1 = new SimpleHash(algorithmName,password1,salt,times).toString();
             
        System.out.printf("原始密码是 %s , 盐是： %s, 运算次数是： %d, 运算出来的密文是：%s ",password1,salt,times,encodedPassword1);
             
        
    }
}
