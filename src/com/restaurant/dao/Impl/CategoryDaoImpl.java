package com.restaurant.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;

import com.restaurant.JDBConnection;
import com.restaurant.dao.IBaseDAO;
import com.restaurant.entity.Category;
import com.restaurant.util.Changed;

public class CategoryDaoImpl implements IBaseDAO {
    Category cate;
    public Category getCategoryById(int id) {
        cate = new Category();
        Connection conn = JDBConnection.getConn();
        String s1 = "select * from category where id=?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        /* List list = new ArrayList(); */
        try {
            ps = conn.prepareStatement(s1);
            ps.setInt(1, id);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "1.取出category全部数据出错!");
            e.printStackTrace();
        }
        try {
            while (rs.next()) {
                cate.setId(rs.getInt("id"));
                cate.setName(rs.getString("name"));
                cate.setDescrib(rs.getString("describ"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "2.取出category数据出错!");
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
        return cate;
    }

    public Category getCategoryByName(String name){
        cate = new Category();
        Connection conn = JDBConnection.getConn();
        String s1 = "select * from category where name=?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        /* List list = new ArrayList(); */
        try {
            ps = conn.prepareStatement(s1);
            ps.setString(1, name);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "1.取出category全部数据出错!");
            e.printStackTrace();
        }
        try {
            rs.next();
            cate.setId(rs.getInt("id"));
            cate.setName(rs.getString("name"));
            cate.setDescrib(rs.getString("describ"));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "2.取出category数据出错!");
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
        return cate;
    }
    @Override
    public List getList() {
        // TODO Auto-generated method stub
        Connection conn = JDBConnection.getConn();
        String s1 = "select * from category order by id asc";
        PreparedStatement ps = null;
        ResultSet rs = null;
        List list = new ArrayList();
        try {
            ps = conn.prepareStatement(s1);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            System.out.println("取出全部数据出错！");
            JOptionPane.showMessageDialog(null, "取出全部数据出错!");
            e.printStackTrace();
        }
        try {
            while (rs.next()) {
                Category cate = new Category();
                cate.setId(rs.getInt(1));
                cate.setName(rs.getString(2));
                cate.setDescrib(rs.getString(3));
                list.add(cate);
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
                Category cate = (Category) it.next();
                int id = cate.getId();
                String name = cate.getName();
                String describ = cate.getDescrib();
                s1 = "insert into category(name,describ) values(?,?)";
                ps = conn.prepareStatement(s1);
                ps.setString(1, name);
                ps.setString(2, describ);
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
        String str = "delete from category where id=?";
        int id = 0;
        Iterator it = list.iterator();
        Connection conn = JDBConnection.getConn();
        PreparedStatement ps = null;
        try {
            while (it.hasNext()) {
                id = ((Category) it.next()).getId();
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
                        str = "update category set name=? where id=?";
                        break;
                    case 2:
                        str = "update category set describ=? where id=?";
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
}
