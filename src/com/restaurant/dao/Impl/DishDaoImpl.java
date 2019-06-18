package com.restaurant.dao.Impl;

import com.restaurant.JDBConnection;
import com.restaurant.dao.IBaseDAO;
import com.restaurant.entity.Category;
import com.restaurant.entity.Dish;
import com.restaurant.util.Changed;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DishDaoImpl implements IBaseDAO{
    Category category;
    CategoryDaoImpl cdi;
    public Dish getDishById(int id) {
        Dish dish = new Dish();
        Connection conn = JDBConnection.getConn();
        String s1 = "select * from dish where id=?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(s1);
            ps.setInt(1, id);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "1.取出dish全部数据出错!");
            e.printStackTrace();
        }
        try {
            rs.next();
            dish.setId(rs.getInt("id"));

            int categoryid=rs.getInt("categoryId");
            cdi=new CategoryDaoImpl();
            category=new Category();
            category=cdi.getCategoryById(categoryid);
            dish.setCategory(category);

            dish.setName(rs.getString("name"));
            dish.setPic(rs.getString("pic"));
            dish.setCode(rs.getString("code"));
            dish.setUnit(rs.getString("unit"));
            dish.setPrice(rs.getDouble("price"));
            dish.setStatus(rs.getString("status"));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "2.取出dish数据出错!");
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                ps.close();
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "关闭数据连接时出错!");
                e.printStackTrace();
            }
        }
        return dish;
    }

    public Dish getDishByCode(String code) {
        Dish dish = new Dish();
        Connection conn = JDBConnection.getConn();
        String s1 = "select * from dish where code=?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(s1);
            ps.setString(1, code);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "1.取出dish全部数据出错!");
            e.printStackTrace();
        }
        try {
            rs.next();
            dish.setId(rs.getInt("id"));

            int categoryid=rs.getInt("categoryId");
            cdi=new CategoryDaoImpl();
            category=new Category();
            category=cdi.getCategoryById(categoryid);
            dish.setCategory(category);

            dish.setName(rs.getString("name"));
            dish.setPic(rs.getString("pic"));
            dish.setCode(rs.getString("code"));
            dish.setUnit(rs.getString("unit"));
            dish.setPrice(rs.getDouble("price"));
            dish.setStatus(rs.getString("status"));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "2.取出dish数据出错!");
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                ps.close();
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "关闭数据连接时出错!");
                e.printStackTrace();
            }
        }
        return dish;
    }

    @Override
    public List getList() {
        // TODO Auto-generated method stub
        Connection conn = JDBConnection.getConn();
        String s1 = "select * from dish order by id asc";
        PreparedStatement ps = null;
        ResultSet rs = null;
        List list = new ArrayList();

        category=new Category();
        cdi=new CategoryDaoImpl();
        try {
            ps = conn.prepareStatement(s1);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            /*System.out.println("取出dish数据出错！");*/
            JOptionPane.showMessageDialog(null, "取出dish全部数据出错!");
            e.printStackTrace();
        }
        try {
            while (rs.next()) {
                Dish dish = new Dish();
                dish.setId(rs.getInt(1));
                dish.setName(rs.getString(2));
                category=cdi.getCategoryById(rs.getInt(3));
                dish.setCategory(category);
                dish.setPic(rs.getString(4));
                dish.setCode(rs.getString(5));
                dish.setUnit(rs.getString(6));
                dish.setPrice(rs.getDouble(7));
                dish.setStatus(rs.getString(8));
                list.add(dish);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "取出全部数据出错!");
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                ps.close();
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "关闭数据连接时出错!");
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
                Dish dish= (Dish) it.next();
                int id=dish.getId();
                int categoryId=dish.getCategory().getId();
                String name = dish.getName();
                String pic=dish.getPic();
                String code=dish.getCode();
                String unit=dish.getUnit();
                double price=dish.getPrice();
                String status=dish.getStatus();
                s1 = "insert into dish(categoryId,name,pic,code,unit,price,status) values(?,?,?,?,?,?,?)";
                ps = conn.prepareStatement(s1);
                ps.setInt(1, categoryId);
                ps.setString(2, name);
                ps.setString(3, pic);
                ps.setString(4, code);
                ps.setString(5, unit);
                ps.setDouble(6, price);
                ps.setString(7, status);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("添加数据时出错！");
            JOptionPane.showMessageDialog(null, "添加数据时出错!");
            e.printStackTrace();
        } finally {
            try {
                ps.close();
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "关闭数据连接时出错!");
                e.printStackTrace();
            }
        }
    }
    @Override
    public void deleteList(List list) {
        // TODO Auto-generated method stub
        String str = "delete from dish where id=?";
        int id = 0;
        Iterator it = list.iterator();
        Connection conn = JDBConnection.getConn();
        PreparedStatement ps = null;
        try {
            while (it.hasNext()) {
                id = ((Dish) it.next()).getId();
                ps = conn.prepareStatement(str);
                ps.setInt(1, id);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
        } finally {
            try {
                ps.close();
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "关闭数据连接时出错!");
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
                        str = "update dish set categoryId=? where id=?";
                        break;
                    case 2:
                        str = "update dish set name=? where id=?";
                        break;
                    case 3:
                        str = "update dish set pic=? where id=?";
                        break;
                    case 4:
                        str = "update dish set code=? where id=?";
                        break;
                    case 5:
                        str = "update dish set unit=? where id=?";
                        break;
                    case 6:
                        str = "update dish set price=? where id=?";
                        break;
                    case 7:
                        str = "update dish set status=? where id=?";
                        break;
                }
                ps = conn.prepareStatement(str);
                ps.setString(1, value);
                ps.setInt(2, id);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "修改数据时出错!");
            e.printStackTrace();
        } finally {
            try {
                ps.close();
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "关闭数据连接时出错!");
                e.printStackTrace();
            }
        }
    }
    public boolean save(Dish dish){
        Connection conn = JDBConnection.getConn();
        PreparedStatement ps = null;
        int categoryId=dish.getCategory().getId();
        String name = dish.getName();
        String pic=dish.getPic();
        String code=dish.getCode();
        String unit=dish.getUnit();
        double price=dish.getPrice();
        String status=dish.getStatus();
        String s1 = "insert into dish(categoryId,name,pic,code,unit,price,status) values(?,?,?,?,?,?,?)";
        try {
            ps = conn.prepareStatement(s1);
            ps.setInt(1, categoryId);
            ps.setString(2, name);
            ps.setString(3, pic);
            ps.setString(4, code);
            ps.setString(5, unit);
            ps.setDouble(6, price);
            ps.setString(7, status);
            ps.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return true;
    }
}