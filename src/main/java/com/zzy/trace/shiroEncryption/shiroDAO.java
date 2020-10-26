package com.zzy.trace.shiroEncryption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
 
public class shiroDAO {
    public shiroDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
 
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://123.57.241.104:3306/shiroEnc?characterEncoding=UTF-8", "zzy",
                "zzy@ali");
    }
    
    public String createUser(String name, String password) {
        int enc_time = 2;
        String sql = "insert into user values(null,?,?,?)";
         
        String salt = new SecureRandomNumberGenerator().nextBytes().toString(); //盐量随机
        String encodedPassword= new SimpleHash("md5",password,salt,enc_time).toString();
         
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
             
            ps.setString(1, name);
            ps.setString(2, encodedPassword);
            ps.setString(3, salt);
            ps.execute();
        } catch (SQLException e) {
 
            e.printStackTrace();
        }
        return null;       
         
    }
    
    public User getUser(String userName) {
        User user = null;
        String sql = "select * from user where name = ?";
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
             
            ps.setString(1, userName);
             
            ResultSet rs = ps.executeQuery();
             
            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                user.setSalt(rs.getString("salt"));
            }
             
        } catch (SQLException e) {
             
            e.printStackTrace();
        }
        return user;
    }
 
    public String getPassword(String userName) {
        String sql = "select password from user where name = ?";
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
             
            ps.setString(1, userName);
             
            ResultSet rs = ps.executeQuery();
 
            if (rs.next())
                return rs.getString("password");
 
        } catch (SQLException e) {
 
            e.printStackTrace();
        }
        return null;
    }
     
    public Set<String> listRoles(String userName) {
         
        Set<String> roles = new HashSet<>();
        String sql = "select r.name from user u "
                + "left join user_role ur on u.id = ur.uid "
                + "left join role r on r.id = ur.rid "
                + "where u.name = ?";
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setString(1, userName);
            ResultSet rs = ps.executeQuery();
             
            while (rs.next()) {
                roles.add(rs.getString(1));
            }
             
        } catch (SQLException e) {
             
            e.printStackTrace();
        }
        return roles;
    }
    public Set<String> listPermissions(String userName) {
        Set<String> permissions = new HashSet<>();
        String sql =
        "select p.name from user u "+
        "left join user_role ru on u.id = ru.uid "+
        "left join role r on r.id = ru.rid "+
        "left join role_permission rp on r.id = rp.rid "+
        "left join permission p on p.id = rp.pid "+
        "where u.name =?";
         
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
             
            ps.setString(1, userName);
             
            ResultSet rs = ps.executeQuery();
             
            while (rs.next()) {
                permissions.add(rs.getString(1));
            }
             
        } catch (SQLException e) {
             
            e.printStackTrace();
        }
        return permissions;
    }
    public static void main(String[] args) {
        System.out.println(new shiroDAO().listRoles("zhang3"));
        System.out.println(new shiroDAO().listRoles("li4"));
        System.out.println(new shiroDAO().listPermissions("zhang3"));
        System.out.println(new shiroDAO().listPermissions("li4"));
    }
}