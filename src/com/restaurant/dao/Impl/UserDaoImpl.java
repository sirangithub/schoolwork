package com.restaurant.dao.Impl;

import com.restaurant.JDBConnection;
import com.restaurant.dao.IBaseDAO;
import com.restaurant.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements IBaseDAO {
    @Override
    public List getList()throws SQLException {
        String sql="select * from user";
        Connection conn= JDBConnection.getConn();
        conn.setAutoCommit(false);
        PreparedStatement preparedStatement=conn.prepareStatement(sql);
        List list=new ArrayList(); //将从数据库中的数据取出存入list集合
        //执行查询语句返回结果集
        ResultSet resultSet=preparedStatement.executeQuery();
        while(resultSet.next()){
            User user=new User();
            user.setId(resultSet.getInt("id"));
            user.setUsername(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
            list.add(user);
        }
        resultSet.close();
        preparedStatement.close();
        conn.close();
        return list;
    }
    @Override
    public void saveList(List list) {

    }

    @Override
    public void deleteList(int id) {

    }

    @Override
    public void update(List list) {

    }
    public static void main(String[] args)throws SQLException{

        System.out.println(new UserDaoImpl().getList());

    }
}
