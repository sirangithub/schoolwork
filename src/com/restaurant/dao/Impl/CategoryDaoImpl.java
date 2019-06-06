package com.restaurant.dao.Impl;

import com.restaurant.JDBConnection;
import com.restaurant.dao.IBaseDAO;
import com.restaurant.entity.Category;
import com.restaurant.util.Changed;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CategoryDaoImpl implements IBaseDAO {
    Category cate;

    @Override
    public List getList() {
        Connection conn = JDBConnection.getConn();
        String sql = "select * from category order by id asc";
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
                Category cate = new Category();
                cate.setId(rs.getInt("id"));
                cate.setName(rs.getString("name"));
                cate.setDescrib(rs.getString("describ"));
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
            Iterator it = list.iterator();
            while (it.hasNext()) {
                Category cate = (Category) it.next();
                int id = cate.getId();
                String name = cate.getName();
                String descib = cate.getDescrib();
                sql = "insert into category(name,describ) values(?,?)";
                ps = conn.prepareStatement(sql);
                ps = conn.prepareStatement(name);
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
                JOptionPane.showMessageDialog(null, "关闭数据连接时出错！");
                e.printStackTrace();
            }
        }
    }

    @Override
    public void deleteList(int id) {
        String sql = "delete from category where id=?";
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
                        sql = "update category set name=? where id=?";
                        break;
                    case 2:
                        sql="update category set describ=? where id=?";
                        break;
                }
                ps=conn.prepareStatement(sql);
                ps.setString(1,value);
                ps.setInt(2,id);
                ps.executeUpdate();
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "关闭数据连接出错！");
            e.printStackTrace();
        }

    }
}
