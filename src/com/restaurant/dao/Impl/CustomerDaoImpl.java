package com.restaurant.dao.Impl;

import com.restaurant.JDBConnection;
import com.restaurant.dao.IBaseDAO;
import com.restaurant.entity.Category;
import com.restaurant.entity.Customer;
import com.restaurant.util.Changed;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/**
 * @author zhangrong
 * 实现客户业务逻辑类
 */



public class CustomerDaoImpl implements IBaseDAO{

    public List getList() {
        Connection coon = JDBConnection.getConn();
        String sql = "select * from customer order by id asc";
        PreparedStatement ps = null;
        ResultSet rs = null;
        List list = new ArrayList();
        try {
            ps = coon.prepareStatement(sql);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            System.out.println("取出全部数据出错！");
            JOptionPane.showMessageDialog(null, "取出全部数据出错！");
            e.printStackTrace();
        }
        try {
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setName(rs.getString("name"));
                customer.setSex(rs.getString("sex"));
                customer.setCompany(rs.getString("company"));
                customer.setTel(rs.getString("tel"));
                customer.setCardID(rs.getString("cardID"));
                list.add(customer);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "取出全部数据出错！");
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                ps.close();
                coon.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "关闭数据连接是出错！");
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
            Iterator it = list.iterator();
            while (it.hasNext()) {
                Customer customer = (Customer) it.next();
                String name = customer.getName();
                String sex = customer.getSex();
                String company = customer.getCompany();
                String tel = customer.getTel();
                String cardID = customer.getCardID();
                sql = "insert into customer(name,sex,company,tel,cardID) values(?,?,?,?,?)";
                ps = conn.prepareStatement(sql);
                ps.setString(1, name);
                ps.setString(2, sex);
                ps.setString(3, company);
                ps.setString(4, tel);
                ps.setString(5, cardID);
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
    public void deleteList(List list) {
        // TODO Auto-generated method stub
        String sql="delete from customer where id=?";
        int id=0;
        Iterator it=list.iterator();
        Connection conn=JDBConnection.getConn();
        PreparedStatement ps=null;
        try{
            while(it.hasNext()){
                id=((Customer)it.next()).getId();
                ps=conn.prepareStatement(sql);
                ps.setInt(1,id);
                ps.executeUpdate();
            }
        }catch (SQLException e){
        }finally {
            try {
                ps.close();
                conn.close();
            }catch (SQLException e){
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
                        sql = "update customer set name=? where id=?";
                        break;
                    case 2:
                        sql = "update customer set sex=? where id=?";
                        break;
                    case 3:
                        sql = "update customer set company=? where id=?";
                        break;
                    case 4:
                        sql = "update customer set tel=? where id=?";
                        break;
                    case 5:
                        sql = "update customer set cardID=? where id=?";
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

}

