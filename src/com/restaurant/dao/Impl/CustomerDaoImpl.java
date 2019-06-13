package com.restaurant.dao.Impl;

import com.restaurant.JDBConnection;
import com.restaurant.dao.IBaseDAO;
import com.restaurant.entity.Customer;
import com.restaurant.util.Changed;
import com.restaurant.util.Changed2;

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

import static com.restaurant.util.Constant.*;

public class CustomerDaoImpl implements IBaseDAO {
    Customer customer;

    public List getCustomer(List list) {
        Connection conn = JDBConnection.getConn();
        customer = new Customer();
        Iterator it = list.iterator();
        int col = 0;
        String obid = "";
        String sql = "";
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Customer> list1 = new ArrayList<>();
        try {
            while (it.hasNext()) {
                Changed2 ch = (Changed2) it.next();
                obid = ch.getObid();
                col = ch.getCol();
                switch (col) {
                    case 1:
                        sql = "select * from customer where id=?";
                        break;
                    case 2:
                        sql = "select * from customer where name=? order by id asc";
                        break;
                    case 3:
                        sql = "select * from customer where sex=? order by id asc";
                        break;
                    case 4:
                        sql = "select * from customer where cardID=?";
                        break;

                }
            }
            ps = conn.prepareStatement(sql);
            if (col == 1) {
                ps.setInt(1, Integer.parseInt(obid));
            } else {
                ps.setString(1, obid);
            }
            rs = ps.executeQuery();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "取出customer数据出错！");
            e.printStackTrace();
        }
        try {
            while (rs.next()) {
                customer.setId(rs.getInt("id"));
                customer.setName(rs.getString("name"));
                customer.setSex(rs.getString("sex"));
                customer.setCompany(rs.getString("company"));
                customer.setTel(rs.getString("tel"));
                customer.setCardID(rs.getString("cardID"));
                list1.add(customer);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "取出Customer数据出错！");
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
        return list1;
    }

    @Override
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
    public void deleteList(int id) {
        String sql = "delete from customer where id=?";
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

    public static void main(String[] args) {
        CustomerDaoImpl customerDao = new CustomerDaoImpl();
        /**
         * 检测插入
         */
        /*List<Customer> list=new ArrayList<>();
        Customer customer=new Customer();
        customer.setName("唐经理");
        customer.setSex(FEMALE);
        customer.setCompany("长沙学院");
        customer.setTel("13774587263");
        customer.setCardID("627245027");
        list.add(customer);
        customerDao.saveList(list);*/
        /**
         * 检测删除
         */
        //customerDao.deleteList(11);
        /**
         * 检测修改
         */
        //Customer customer=new Customer();
        /*List<Changed> list=new ArrayList<>();
        Changed ch=new Changed();
        ch.setId(301);
        ch.setValue("许董事长");
        ch.setCol(1);
        list.add(ch);
        customerDao.update(list);*/
        /*List<Changed> list=new ArrayList<>();
        Changed ch=new Changed();
        ch.setId(301);
        ch.setValue(FEMALE);
        ch.setCol(2);*/
        /*List<Changed> list=new ArrayList<>();
        Changed ch=new Changed();
        ch.setId(301);
        ch.setValue("长沙学院");
        ch.setCol(3);*/
        /*List<Changed> list=new ArrayList<>();
        Changed ch=new Changed();
        ch.setId(301);
        ch.setValue("13474589263");
        ch.setCol(4);*/
        /*List<Changed> list=new ArrayList<>();
        Changed ch=new Changed();
        ch.setId(301);*/
        /*ch.setValue("A627245030");
        ch.setCol(5);*/
        /*list.add(ch);
        customerDao.update(list);*/
        /**
         * 条件查询测试
         */
        /*List<Changed2> list=new ArrayList<>();
        Changed2 ch2=new Changed2();*/
        /*ch2.setObid("1");
        ch2.setCol(1);*/
        /*ch2.setObid("许琼方");
        ch2.setCol(2);*/
        /*ch2.setObid(FEMALE);
        ch2.setCol(3);*/
        /*ch2.setObid("627245030");
        ch2.setCol(4);*/
        //list.add(ch2);
        //System.out.println(customerDao.getCustomer(list));
        //System.out.println(customerDao.getList());
    }
}
