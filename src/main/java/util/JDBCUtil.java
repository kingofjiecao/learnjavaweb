package util;

import com.sun.org.apache.bcel.internal.generic.DREM;
import model.Item;
import model.User;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCUtil {
    private static String jdbcUrl = (String)PropertiesUtil.getMysqlConfig().get("jdbc.url");
    private static String jdbcUsername = (String)PropertiesUtil.getMysqlConfig().get("jdbc.username");
    private static String jdbcPassword = (String)PropertiesUtil.getMysqlConfig().get("jdbc.password");
    static void close(ResultSet rs, Statement statement, Connection con){
        try {
            if(rs != null)
                rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if(statement != null)
                statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if(statement != null)
               con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Test public void testConnectionNotNull(){
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaweb?useUnicode=true&characterEncoding=UTF-8&useSSL=false", "root", "ltc.523321314");
            String sql = "insert into user(username, password) value(?, ?)";
            statement = con.prepareStatement(sql);
            statement.setString(1, "123");
            statement.setString(2, "123");
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            close(rs, statement, con);
        }

    }
    @Test public void test02(){
        insertUser("1234", "123");
    }
    public static boolean insertUser(String username, String password){
        Connection con = null;
        PreparedStatement statement = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);
            String sql = "insert into user(username, password) value(? , ?)";
            statement = con.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);
            int result = statement.executeUpdate();
            if(result > 0){
                System.out.println("插入成功");
                return true;
            }else{
                System.out.println("插入失败");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            close(null, statement, con);
        }
        return false;
    }
    public static User selectUserByUsername(String username){
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);
            String sql = "select * from user where username = ?";
            statement = con.prepareStatement(sql);
            statement.setString(1, username);
            rs = statement.executeQuery();
            if (rs.next()){
                User u = new User.Builder(rs.getString(2),rs.getString(3)).id(rs.getInt(1)).build();
                return u;
            }else{
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            close(rs, statement, con);
        }
        return null;
    }
    public static User selectUserByUsernameAndPassword(String username, String password) {
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);
            String sql = "select * from user where username = ? and password = ?";
            statement = con.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);
            rs = statement.executeQuery();
            if (rs.next()) {
                User u = new User.Builder(rs.getString(2), rs.getString(3)).id(rs.getInt(1)).build();
                return u;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(rs, statement, con);
        }
        return null;
    }
    public static boolean selectCartByIdAndItem(int id, String item){
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);
            String sql = "select * from cart where u_id = ? and item = ?";
            statement = con.prepareStatement(sql);
            statement.setInt(1, id);
            statement.setString(2, item);
            rs = statement.executeQuery();
            if(rs.next()){
                return true;
            }else {
                return false;
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            close(rs, statement, con);
        }
        return false;
    }
    public static boolean insertItem(int id, String item){
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);
            String sql = "insert into cart value(?, ?, 1)";
            statement = con.prepareStatement(sql);
            statement.setString(1, item);
            statement.setInt(2, id);
            int result = statement.executeUpdate();
            if(result == 0){
                return false;
            }else {
                return true;
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            close(rs, statement, con);
        }
        return false;
    }
    public static boolean updateCartByIdAndItem(int id, String item){
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);
            String sql = "update cart set item_count = item_count + 1 where u_id = ? and item = ?";
            statement = con.prepareStatement(sql);
            statement.setInt(1, id);
            statement.setString(2, item);
            int result = statement.executeUpdate();
            if(result == 0){
                return false;
            }else {
                return true;
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            close(rs, statement, con);
        }
        return false;
    }
    public static List<Item> selectCartById(int id){
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);
            String sql = "select * from cart where u_id = ?";
            statement = con.prepareStatement(sql);
            statement.setInt(1, id);
            rs = statement.executeQuery();
            List<Item> list = new ArrayList<Item>();
            if(rs.next()){
                do{
                    Item i = new Item(rs.getString(1), rs.getInt(3));
                    list.add(i);
                }while(rs.next());
                return list;
            }else {
                return null;
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            close(rs, statement, con);
        }
        return null;
    }
}
