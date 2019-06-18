/*package com.restaurant.dao.Impl;

import com.restaurant.JDBConnection;
import com.restaurant.dao.IBaseDAO;
import com.restaurant.entity.Order;
import com.restaurant.util.Changed;
import com.restaurant.util.Changed2;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class OrderDaoImpl implements IBaseDAO {
    Order order;
    public List getOrder(List list){
        Connection conn = JDBConnection.getConn();
        //desk = new Desk();
        Iterator it = list.iterator();
        int col = 0;
        String obid = "";
        String sql = "";
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Order> list1=new ArrayList<>();
        try {
            while (it.hasNext()) {
                Changed2 ch2 = (Changed2) it.next();
                obid = ch2.getObid();
                col = ch2.getCol();
                switch (col) {
                    case 1:
                        sql = "select * from orderinfo where id=? ";
                        break;
                    case 2:
                        sql = "select * from orderinfo where orderno=?";
                        break;
                    case 3:
                        sql = "select * from orderinfo where deskid=?";
                        break;
                    case 4:
                        sql = "select * from orderinfo where createtime=? order by id";
                        break;
                    case 5:
                        sql = "select * from orderinfo where customerid=? ";
                        break;
                    case 7:
                        sql = "select * from orderinfo where status=? order by id ";
                        break;
                    case 8:
                        sql = "select * from orderinfo where number=? ";
                        break;
                }
            }
            ps = conn.prepareStatement(sql);
            if (col == 2 || col == 7) {
                ps.setString(1, obid);
            }else if(col==4){
                ps.setDate(1, java.sql.Date.valueOf(obid));
            }
            else {
                ps.setInt(1, Integer.parseInt(obid));
            }
            rs = ps.executeQuery();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "取出desk数据出错！");
            e.printStackTrace();
        }
        try {
            while (rs.next()) {
                order=new Order();
                order.setId(rs.getInt("id"));
                order.setOrderNo(rs.getString("orderno"));
                order.setDeskId(rs.getInt("deskid"));
                order.setCreatetime(rs.getDate("createtime"));
                order.setMoney(rs.getDouble("money"));
                order.setCustomerId(rs.getInt("customerid"));
                order.setStatus(rs.getString("status"));
                order.setNumber(rs.getInt("number"));
                list1.add(order);
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "取出order数据出错！");
            e.printStackTrace();
        }finally {
            try {
                rs.close();
                ps.close();
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "关闭数据连接时出错！");
                e.printStackTrace();
            }
        }
        return list1;
    }
    @Override
    public List getList() {
        Connection conn = JDBConnection.getConn();
        String sql = "select * from orderinfo order by id asc";
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
                order= new Order();
                order.setId(rs.getInt("id"));
                order.setOrderNo(rs.getString("orderno"));
                order.setDeskId(rs.getInt("deskid"));
                order.setCreatetime(rs.getDate("createtime"));
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
            return list;
        }
    }

    @Override
    public void saveList(List list) {
        String sql = "";
        Connection conn = JDBConnection.getConn();
        PreparedStatement ps = null;
        try {
            String orderno = order.getOrderNo();
            int deskID=order.getDeskId();
            Date createtime=order.getCreatetime();
            double money=order.getMoney();
            int customerID=order.getCustomerId();
            String status=order.getStatus();
            int number=order.getNumber();
            sql = "insert into orderinfo(orderno,deskid,createtime,money,customerid,status,,number) values (?,?,?,?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, orderno);
            ps.setInt(2,deskID);
            ps.setDate(3, (java.sql.Date) createtime);
            ps.setDouble(4,money);
            ps.setInt(5,customerID);
            ps.setString(6,status);
            ps.setInt(7,number);
            ps.executeUpdate();

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
        String sql = "delete from orderinfo where id=?";
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
                        sql = "update orderinfo set deskid=? where id=?";
                        break;
                    case 2:
                        sql = "update orderinfo set createtime=? where id=?";
                        break;
                    case 3:
                        sql = "update orderinfo set money=? where id=?";
                        break;
                    case 4:
                        sql = "update orderinfo set customerid=? where id=?";
                        break;
                    case 5:
                        sql = "update orderinfo set status=? where id=?";
                        break;
                    case 6:
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
}*/
