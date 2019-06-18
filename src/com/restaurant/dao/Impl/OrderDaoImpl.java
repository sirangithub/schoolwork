package com.restaurant.dao.Impl;

import com.restaurant.JDBConnection;
import com.restaurant.dao.IBaseDAO;
import com.restaurant.entity.Order;
import com.restaurant.util.Changed;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class OrderDaoImpl implements IBaseDAO {
    Order order;
    public Order getOrderById(int id) {
        order=new Order();
        Connection conn=JDBConnection.getConn();
        String s1="select * from orderinfo where id=?";
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            ps=conn.prepareStatement(s1);
            ps.setInt(1, id);
            rs=ps.executeQuery();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "取出order全部数据出错");
            e.printStackTrace();
        }
        try {
            rs.next();
            order.setId(rs.getInt("id"));
            order.setOrderNo(rs.getString("orderNo"));
            order.setDeskId(rs.getInt("deskId"));
            order.setCreatetime(rs.getString("crestetime"));
            order.setMoney(rs.getDouble("money"));
            order.setCustomerId(rs.getInt("customerId"));
            order.setStatus(rs.getString("status"));
            order.setNumber(rs.getInt("number"));

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "取出order数据出错");
            e.printStackTrace();
        }finally {
            try {
                rs.close();
                ps.close();
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                JOptionPane.showMessageDialog(null, "关闭数据连接时出错");
                e.printStackTrace();
            }
        }
        return order;
    }
    @Override
    public List getList() {
        Connection conn = JDBConnection.getConn();
        String sql = "select * from orderinfo order by id asc";
        PreparedStatement ps = null;
        ResultSet rs = null;
        List list = new ArrayList();
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
                order= new Order();
                order.setId(rs.getInt("id"));
                order.setOrderNo(rs.getString("orderNo"));
                order.setDeskId(rs.getInt("deskId"));
                order.setCreatetime(rs.getString("createtime"));
                order.setMoney(rs.getDouble("money"));
                order.setCustomerId(rs.getInt("customerId"));
                order.setStatus(rs.getString("status"));
                order.setNumber(rs.getInt("number"));
                list.add(order);
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
        }
        return list;
    }

    @Override
    public void saveList(List list) {
        String sql = "";
        Connection conn = JDBConnection.getConn();
        PreparedStatement ps = null;
        try {
            Iterator it=list.iterator();
            while (it.hasNext()) {
                Order order=(Order) it.next();
                int id=order.getId();
                String orderNo = order.getOrderNo();
                int deskId=order.getDeskId();
                String createtime=order.getCreatetime();
                double money=order.getMoney();
                int customerId=order.getCustomerId();
                String status=order.getStatus();
                int number=order.getNumber();
                sql = "insert into orderinfo(orderNo,deskId,createtime,money,customerId,status,number) values (?,?,?,?,?,?,?)";
                ps = conn.prepareStatement(sql);
                ps.setString(1, orderNo);
                ps.setInt(2,deskId);
                ps.setString(3, createtime);
                ps.setDouble(4,money);
                ps.setInt(5,customerId);
                ps.setString(6,status);
                ps.setInt(7,number);
                ps.executeUpdate();
            }
        }catch (SQLException e) {
            System.out.println("添加数据时出错！");
            JOptionPane.showMessageDialog(null, "添加数据时出错！");
            e.printStackTrace();
        }finally {
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
    public void deleteList(List list) {
        String str="delete from orderinfo where id=?";
        int id=0;
        Iterator it=list.iterator();
        Connection conn=JDBConnection.getConn();
        PreparedStatement ps=null;
        try {
            while (it.hasNext()) {
                id=((Order)it.next()).getId();
                ps=conn.prepareStatement(str);
                ps.setInt(1, id);
                ps.executeUpdate();
            }
        } catch (Exception e) {
        }finally {
            try {
                ps.close();
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                JOptionPane.showMessageDialog(null, "关闭数据连接时出错");
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
                        sql = "update orderinfo set orderNo=? where id=?";
                        break;
                    case 2:
                        sql = "update orderinfo set deskId=? where id=?";
                        break;
                    case 3:
                        sql = "update orderinfo set createtime=? where id=?";
                        break;
                    case 4:
                        sql = "update orderinfo set money=? where id=?";
                        break;
                    case 5:
                        sql = "update orderinfo set customerId=? where id=?";
                        break;
                    case 6:
                        sql = "update orderinfo set status=? where id=?";
                        break;
                    case 7:
                        sql = "update orderinfo set number=? where id=?";
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
        }
    }
}
