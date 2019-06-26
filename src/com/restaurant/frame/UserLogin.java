package com.restaurant.frame;

import com.restaurant.dao.Impl.UserDaoImpl;
import com.restaurant.entity.User;

public class UserLogin {
    String user = "";
    String password = "";

    public UserLogin(String user, String password) {
        this.user = user;
        this.password = password;
    }
    public boolean isLoginSuccess() {
        UserDaoImpl userDao = new UserDaoImpl();
        User userinfo = userDao.getUser(user);
        if (user.equals(userinfo.getUsername()) && password.equals(userinfo.getPassword())) {
            return true;
        } else {
            return false;
        }
    }
}
