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
    Employee employee;
    public Employee getEmployeeById(int id) {
        employee=new Employee();
        Connection conn=JDBConnection.getConn();
        String s1="select * from employee where id=?";
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            ps=conn.prepareStatement(s1);
            ps.setInt(1, id);
            rs=ps.executeQuery();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            JOptionPane.showMessageDialog(null, "取出employee全部数据出错");
            e.printStackTrace();
        }
        try {
            rs.next();
            employee.setId(rs.getInt("id"));
            employee.setName(rs.getString("name"));
            employee.setSex(rs.getString("sex"));
            employee.setBirthday(rs.getString("birthday"));
            employee.setIdentityID(rs.getString("identityID"));
            employee.setAddress(rs.getString("address"));
            employee.setTel(rs.getString("tel"));
            employee.setPosition(rs.getString("position"));
            employee.setFreeze(rs.getString("freeze"));
        } catch (SQLException e) {
            // TODO: handle exception
            JOptionPane.showMessageDialog(null, "取出employee数据出错");
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
        return employee;
    }
    public Employee getStaffByIdentityID(String identityID) {
        employee=new Employee();
        Connection conn=JDBConnection.getConn();
        String s1="select * from employee where identityID=?";
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            ps=conn.prepareStatement(s1);
            ps.setString(1, identityID);
            rs=ps.executeQuery();
        }catch (SQLException e) {
            // TODO Auto-generated catch block
            JOptionPane.showMessageDialog(null, "取出employee全部数据出错");
            e.printStackTrace();
        }
        try {
            rs.next();
            employee.setId(rs.getInt("id"));
            employee.setName(rs.getString("name"));
            employee.setSex(rs.getString("sex"));
            employee.setBirthday(rs.getString("birthday"));
            employee.setIdentityID(rs.getString("identityID"));
            employee.setAddress(rs.getString("address"));
            employee.setTel(rs.getString("tel"));
            employee.setPosition(rs.getString("position"));
            employee.setFreeze(rs.getString("freeze"));
        } catch (SQLException e) {
            // TODO: handle exception
            JOptionPane.showMessageDialog(null, "取出employee数据出错");
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
        return employee;
    }

    @Override
    public List getList() {
        // TODO Auto-generated method stub
        Connection conn = JDBConnection.getConn();
        String sql = "select * from employee order by id asc";
        PreparedStatement ps = null;
        ResultSet rs = null;
        List list = new ArrayList();
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
        }catch (SQLException e) {
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
                employee.setBirthday(rs.getString("birthday"));
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
        return list;
    }

    @Override
    public void saveList(List list) {
        // TODO Auto-generated method stub
        String s1 = "";
        Connection conn = JDBConnection.getConn();
        PreparedStatement ps = null;
        try {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                Employee employee = (Employee) it.next();
                int id=employee.getId();
                String name = employee.getName();
                String sex = employee.getSex();
                String birthday = employee.getBirthday();
                String identityID = employee.getIdentityID();
                String address = employee.getAddress();
                String tel = employee.getTel();
                String position = employee.getPosition();
                String freeze = employee.getFreeze();
                s1 = "insert into employee(name,sex,birthday,identityID,address,tel,position,freeze) values(?,?,?,?,?,?,?,?)";
                ps = conn.prepareStatement(s1);
                ps.setString(1, name);
                ps.setString(2, sex);
                ps.setString(3, birthday);
                ps.setString(4, identityID);
                ps.setString(5, address);
                ps.setString(6, tel);
                ps.setString(7, position);
                ps.setString(8, freeze);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("添加数据时出错！");
            JOptionPane.showMessageDialog(null, "添加数据时出错！");
            e.printStackTrace();
        }finally {
            try {
                ps.close();
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "关闭数据连接时出错！");
                e.printStackTrace();
            }
        }
    }

    @Override
    public void deleteList(List list) {
        // TODO Auto-generated method stub
        String str="delete from employee where id=?";
        int id=0;
        Iterator it=list.iterator();
        Connection conn=JDBConnection.getConn();
        PreparedStatement ps=null;
        try {
            while (it.hasNext()) {
                id=((Employee)it.next()).getId();
                ps=conn.prepareStatement(str);
                ps.setInt(1, id);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            // TODO: handle exception
        }finally {
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
        // TODO Auto-generated method stub
        Connection conn = JDBConnection.getConn();
        PreparedStatement ps = null;
        Iterator it = list.iterator();
        int id = 0;
        int col = 0;
        String value = "";
        String str = "";
        try {
            while (it.hasNext()) {
                Changed ch = (Changed) it.next();
                id = ch.getId();
                col = ch.getCol();
                value = ch.getValue();
                switch (col) {
                    case 1:
                        str = "update employee set name=? where id=?";
                        break;
                    case 2:
                        str = "update  employee set sex=? where id=?";
                        break;
                    case 3:
                        str = "update employee set birthday=? where id=?";
                        break;
                    case 4:
                        str = "update employee set identityID=? where id=?";
                        break;
                    case 5:
                        str = "update employee set address=? where id=?";
                        break;
                    case 6:
                        str = "update employee set tel=? where id=?";
                        break;
                    case 7:
                        str = "update employee set position =? where id=?";
                        break;
                    case 8:
                        str = "update employee set freeze=? where id=?";
                        break;
                }
                ps = conn.prepareStatement(str);
                ps.setString(1, value);
                ps.setInt(2, id);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "修改数据时出错！");
            e.printStackTrace();
        }finally {
            try {
                ps.close();
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "关闭数据连接时出错！");
                e.printStackTrace();
            }
        }
    }


}