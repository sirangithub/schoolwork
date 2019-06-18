/*package com.restaurant.dao.Impl;

import com.restaurant.JDBConnection;
import com.restaurant.dao.IBaseDAO;
import com.restaurant.entity.OrderItem;
import com.restaurant.util.Changed;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class OrderitemDaoImpl implements IBaseDAO {
    OrderItem orderItem;
    public OrderItem getOrderItemById(int id){
        orderItem = new OrderItem();
        Connection conn = JDBConnection.getConn();
        String sql = "select * from orderitem where id=? ";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "取出orderitem数据出错！");
            e.printStackTrace();
        }
        try {
            rs.next();
            orderItem.setId(rs.getInt("id"));
            orderItem.setOrderId(rs.getInt("orderid"));
            orderItem.setDishId(rs.getInt("dishid"));
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
        return orderItem;

    }
    @Override
    public List getList() {
        Connection conn = JDBConnection.getConn();
        String sql = "select * from orderitem order by id asc";
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
                orderItem= new OrderItem();
                orderItem.setId(rs.getInt("id"));
                orderItem.setOrderId(rs.getInt("orderid"));
                orderItem.setDishId(rs.getInt("dishID"));
                orderItem.setAmount(rs.getDouble("amount"));
                list.add(orderItem);
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
    public void saveList(List list) {
        String sql = "";
        Connection conn = JDBConnection.getConn();
        PreparedStatement ps = null;
        try {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                orderItem = (OrderItem) it.next();
                int orderID = orderItem.getOrderId();
                int dishID = orderItem.getDishId();
                double amount = orderItem.getAmount();
                sql = "insert into orderitem(orderid,dishid,amount) values(?,?,?)";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, orderID);
                ps.setInt(2, dishID);
                ps.setDouble(3, amount);
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
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "关闭数据连接是出错！");
                e.printStackTrace();
            }
        }
    }

    @Override
    public void deleteList(int id) {
        String sql = "delete from orderitem where id=?";
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
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "关闭数据连接是出错！");
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
                        sql = "update orderitem set orderid=? where id=?";
                        break;
                    case 2:
                        sql = "update orderitem set dishid=? where id=?";
                        break;
                    case 3:
                        sql = "update orderitem set amount=? where id=?";
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
                JOptionPane.showMessageDialog(null, "关闭数据连接出错！");
                e.printStackTrace();
            }
        }
    }
}*/
