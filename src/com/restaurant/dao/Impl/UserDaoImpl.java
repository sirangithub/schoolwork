package com.restaurant.dao.Impl;

import com.restaurant.JDBConnection;
import com.restaurant.dao.IBaseDAO;
import com.restaurant.entity.User;
import com.restaurant.util.Changed;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UserDaoImpl implements IBaseDAO {
    User user;

    public User getUser(int id) {
        user = new User();
        Connection conn = JDBConnection.getConn();
        String sql = "select * from user where id=? ";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "取出user数据出错！");
            e.printStackTrace();
        }
        try {
            rs.next();
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "取出user数据出错！");
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                ps.close();
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "关闭数据连接时出错！");
                e.printStackTrace();
            }
        }
        return user;
    }

    @Override
    public List getList() {//查询用户
        Connection conn = JDBConnection.getConn();
        String sql = "select * from user";
        PreparedStatement ps = null;
        ResultSet rs = null;
        List list = new ArrayList(); //将从数据库中的数据取出存入list集合
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            System.out.println("取出全部数据出错！");
            JOptionPane.showMessageDialog(null, "取出全部数据出错！");
            e.printStackTrace();
        }
        try {
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                list.add(user);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "取出全部数据出错！");
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                ps.close();
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "关闭数据连接时出错！");
                e.printStackTrace();
            }
            return list;
        }
    }

    @Override
    public void saveList(List list) {//添加用户
        String sql = "";
        Connection conn = JDBConnection.getConn();
        PreparedStatement ps = null;
        try {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                User user = (User) it.next();
                int id = user.getId();
                String username = user.getUsername();
                String password = user.getPassword();
                sql = "insert into user(id,username,password) values (?,?,?)";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, id);
                ps.setString(2, username);
                ps.setString(3, password);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("添加数据时出错！");
            JOptionPane.showMessageDialog(null, "添加数据时出错！");
            e.printStackTrace();
        } finally {
            try {
                ps.close();
                conn.close();
                //System.out.println("添加成功");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "关闭数据连接时出错！");
                e.printStackTrace();
            }
        }
    }

    @Override
    public void deleteList(int id) {
        String sql = "delete from user where id=?";
        Connection conn = JDBConnection.getConn();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
        } finally {
            try {
                ps.close();
                conn.close();
                //System.out.println("删除成功");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "关闭数据连接出错！");
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update(List list) {
        Connection conn = JDBConnection.getConn();
        PreparedStatement ps = null;
        Iterator it = list.iterator();
        int id = 0;
        int col = 0;
        String value = "";
        String sql = "";
        try {
            while (it.hasNext()) {
                Changed ch = (Changed) it.next();
                id = ch.getId();
                col = ch.getCol();
                value = ch.getValue();
                switch (col) {
                    case 1:
                        sql = "update user set username=? where id=?";
                        break;
                    case 2:
                        sql = "update user set password=? where id=?";
                        break;
                }
                ps = conn.prepareStatement(sql);
                ps.setString(1, value);
                ps.setInt(2, id);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "修改数据时出错！");
            e.printStackTrace();
        } finally {
            try {
                ps.close();
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "关闭数据连接时出错！");
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        UserDaoImpl userDao = new UserDaoImpl();

        /**
         * 检测插入
         */
       /*List<User> list = new ArrayList();
        User user = new User();
        user.setId(1000000002);
        user.setUsername("李四");
        user.setPassword("123456");
        list.add(user);
        userDao.saveList(list);*/
        /**
         * 检测删除
         */
        //userDao.deleteList(1000000002);
        /**
         * 检测修改
         */
        /* List<Changed> list = new ArrayList();
           Changed ch = new Changed();
           ch.setId(1000000002);
           ch.setCol(1);
           ch.setValue("李老板");
           list.add(ch);*/
         /*List<Changed> list = new ArrayList();
          Changed ch = new Changed();
          ch.setId(1000000002);
          ch.setCol(2);
          ch.setValue("123");
          list.add(ch);
          userDao.update(list);*/
        //System.out.println(userDao.getUser(1000000001));
        //System.out.println(userDao.getList());
    }
}
