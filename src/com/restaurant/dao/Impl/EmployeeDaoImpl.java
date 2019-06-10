package com.restaurant.dao.Impl;

import com.restaurant.JDBConnection;
import com.restaurant.dao.IBaseDAO;
import com.restaurant.entity.Employee;
import com.restaurant.util.Changed;
import com.restaurant.util.Changed2;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import static com.restaurant.util.Constant.*;

/**
 * @author zhangrong
 * 实现员工业务逻辑类
 */

public class EmployeeDaoImpl implements IBaseDAO {
    public Employee getEmployee(List list) {  //需修改
        Connection conn = JDBConnection.getConn();
        Employee employee = new Employee();
        Iterator it=list.iterator();
        int col=0;
        String obid="";
        String sql="";
        //String sql = "select * from employee where id=? ";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            while(it.hasNext()){
                Changed2 ch=(Changed2)it.next();
                obid=ch.getObid();
                col=ch.getCol();
                switch (col){
                    case 1:
                        sql="select * from employee where id=?";
                        break;
                    case 2:
                        sql="select * from employee where name=?";
                        break;
                    case 3:
                        sql="select * from employee where sex=?";
                        break;
                    case 4:
                        sql="select * from employee where identityID=?";
                        break;
                    case 5:
                        sql="select * from employee where position=?";
                        break;
                    case 6:
                        sql="select * from employee where freeze=?";
                        break;

                }
            }
            ps = conn.prepareStatement(sql);
            if(col==1){
                ps.setInt(1, Integer.parseInt(obid));
            }else {
                ps.setString(1,obid);
            }
            rs = ps.executeQuery();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "取出employee数据出错！");
            e.printStackTrace();
        }
        try {
            rs.next();
            employee.setId(rs.getInt("id"));
            employee.setName(rs.getString("name"));
            employee.setSex(rs.getString("sex"));
            employee.setBirthday(rs.getDate("birthday"));
            employee.setIdentityID(rs.getString("identityID"));
            employee.setAddress(rs.getString("address"));
            employee.setTel(rs.getString("tel"));
            employee.setPosition(rs.getString("position"));
            employee.setFreeze(rs.getString("freeze"));
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
        return employee;
    }

    @Override
    public List getList() {
        Connection conn = JDBConnection.getConn();
        String sql = "select * from employee";
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
                Employee employee = new Employee();
                employee.setId(rs.getInt("id"));
                employee.setName(rs.getString("name"));
                employee.setSex(rs.getString("sex"));
                employee.setBirthday(rs.getDate("birthday"));
                employee.setIdentityID(rs.getString("identityID"));
                employee.setAddress(rs.getString("address"));
                employee.setTel(rs.getString("tel"));
                employee.setPosition(rs.getString("position"));
                employee.setFreeze(rs.getString("freeze"));
                list.add(employee);
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
                Employee employee = (Employee) it.next();
                int id = employee.getId();
                String name = employee.getName();
                String sex = employee.getSex();
                Date birthday = employee.getBirthday();
                String identityID = employee.getIdentityID();
                String address = employee.getAddress();
                String tel = employee.getTel();
                String position = employee.getPosition();
                String freeze = employee.getFreeze();
                sql = "insert into employee(id,name,sex,birthday,identityID,address,tel,position,freeze)" +
                        "values(?,?,?,?,?,?,?,?,?)";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, id);
                ps.setString(2, name);
                ps.setString(3, sex);
                ps.setDate(4, (java.sql.Date) birthday);
                ps.setString(5, identityID);
                ps.setString(6, address);
                ps.setString(7, tel);
                ps.setString(8, position);
                ps.setString(9, freeze);
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
        String sql = "delete from employee where id=?";
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
                        sql = "update  employee set name=? where id=?";
                        break;
                    case 2:
                        sql = "update  employee set sex=? where id=?";
                        break;
                    case 3:
                        sql = "update employee set birthday=? where id=?";
                        break;
                    case 4:
                        sql = "update employee set identityID=? where id=?";
                        break;
                    case 5:
                        sql = "update employee set address=? where id=?";
                        break;
                    case 6:
                        sql = "update employee set tel=? where id=?";
                        break;
                    case 7:
                        sql = "update employee set position =? where id=?";
                        break;
                    case 8:
                        sql = "update employee set freeze=? where id=?";
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

    public static SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    public static void main(String[] args)throws ParseException {
        EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
        Employee employee = new Employee();
        /**
         * 检测插入
         */
        /* List<Employee>list=new ArrayList();
        employee.setId(2000000001);
        employee.setName("小李");
        employee.setSex(MALE);
        Date date=new Date();
        employee.setBirthday(new java.sql.Date(date.getTime()));
        employee.setIdentityID("430196198104052215");
        employee.setAddress("花园小区");
        employee.setTel("13700008888");
        employee.setPosition(WAITER);
        employee.setFreeze(ON_FREEZE);
        list.add(employee);
        employeeDao.saveList(list);*/
        /**
         * 检测删除
         */
        //employeeDao.deleteList(2000000001);
        /**
         * 检测修改
         */
        /*List<Changed> list = new ArrayList();
        Changed ch = new Changed();
        ch.setId(2000000001);
        ch.setCol(1);
        ch.setValue("李明");
        list.add(ch);*/
        /*List<Changed> list = new ArrayList();
        Changed ch = new Changed();
        ch.setId(2000000001);
        ch.setCol(2);
        ch.setValue(FEMALE);
        list.add(ch);*/
        /*List<Changed> list = new ArrayList();
        Changed ch = new Changed();
        ch.setId(2000000001);
        ch.setCol(3);
        String datestr="1996-06-07";
        Date date=sdf.parse(datestr);
        ch.setValue(String.valueOf(new java.sql.Date(date.getTime())));
        list.add(ch);*/
        /*List<Changed> list = new ArrayList();
        Changed ch = new Changed();
        ch.setId(2000000001);
        ch.setCol(4);
        ch.setValue("430196199606072165");
        list.add(ch);*/
        /*List<Changed> list = new ArrayList();
        Changed ch = new Changed();
        ch.setId(2000000001);
        ch.setCol(5);
        ch.setValue("花园居民小区");
        list.add(ch);*/
        /*List<Changed> list = new ArrayList();
        Changed ch = new Changed();
        ch.setId(2000000001);
        ch.setCol(6);
        ch.setValue("13701028868");
        list.add(ch);*/
        /*List<Changed> list = new ArrayList();
        Changed ch = new Changed();
        ch.setId(2000000001);
        ch.setCol(7);
        ch.setValue(CASHIER);
        list.add(ch);*/
        /*List<Changed> list = new ArrayList();
        Changed ch = new Changed();
        ch.setId(2000000001);
        ch.setCol(8);
        ch.setValue(OFF_FREEZE);
        list.add(ch);
        employeeDao.update(list);*/
        /*List<Changed2> list=new ArrayList<>();
        Changed2 ch2=new Changed2();
        ch2.setObid("2000000001");
        ch2.setCol(1);
        list.add(ch2);*/
        /*List<Changed2> list=new ArrayList<>();
        Changed2 ch2=new Changed2();
        ch2.setObid("李明");
        ch2.setCol(2);
        list.add(ch2);*/
        /*List<Changed2> list=new ArrayList<>();
        Changed2 ch2=new Changed2();
        ch2.setObid("女");
        ch2.setCol(3);
        list.add(ch2);*/
        /*List<Changed2> list=new ArrayList<>();
        Changed2 ch2=new Changed2();
        ch2.setObid("430196199606072165");
        ch2.setCol(4);
        list.add(ch2);*/
        /*List<Changed2> list=new ArrayList<>();
        Changed2 ch2=new Changed2();
        ch2.setObid(CASHIER);
        ch2.setCol(5);
        list.add(ch2);*/
        /*List<Changed2> list=new ArrayList<>();
        Changed2 ch2=new Changed2();
        ch2.setObid(OFF_FREEZE);
        ch2.setCol(6);
        list.add(ch2);*/
        //System.out.println(employeeDao.getEmployee(list));
        //System.out.println(employeeDao.getEmployee(2000000001));
        //System.out.println(employeeDao.getList());
    }
}
