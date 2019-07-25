package service;

import model.User;
import util.JDBCUtil;

public class UserService {
    private static UserService userService = new UserService();
    private UserService(){}
    public static UserService getUserService(){
        return userService;
    }
    public boolean register(String username, String password){
        User u = JDBCUtil.selectUserByUsername(username);
        if(u == null){
            return JDBCUtil.insertUser(username, password);
        }else {
            return false;
        }
    }
    public User login(String username, String password){
        return JDBCUtil.selectUserByUsernameAndPassword(username, password);
    }
    public boolean shopping(int id, String item){
        if(JDBCUtil.selectCartByIdAndItem(id, item)){
            return JDBCUtil.updateCartByIdAndItem(id, item);
        }else{
            return JDBCUtil.insertItem(id, item);
        }
    }
}
