package com.restaurant.dao.Impl;

import com.restaurant.JDBConnection;
import com.restaurant.dao.IBaseDAO;
import com.restaurant.entity.Desk;
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

import static com.restaurant.util.Constant.*;

/**
 * @author zhangrong
 * 实现餐台业务逻辑类
 */
public class DeskDaoImpl implements IBaseDAO {
    Desk desk = new Desk();

    public List getDesk(List list) {
        Connection conn = JDBConnection.getConn();
        desk = new Desk();
        Iterator it = list.iterator();
        int col = 0;
        String obid = "";
        String sql = "";
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Desk> list1=new ArrayList<>();
        try {
            while (it.hasNext()) {
                Changed2 ch2 = (Changed2) it.next();
                obid = ch2.getObid();
                col = ch2.getCol();
                switch (col) {
                    case 1:
                        sql = "select * from desk where id=? ";
                        break;
                    case 2:
                        sql = "select * from desk where no=?";
                        break;
                    case 3:
                        sql = "select * from desk where seating=? order by id";
                        break;
                    case 4:
                        sql = "select * from desk where seating>=? order by id";
                        break;
                    case 5:
                        sql = "select * from desk where status=? order by id";
                        break;
                }
            }
            ps = conn.prepareStatement(sql);
            if (col == 2 || col == 5) {
                ps.setString(1, obid);
            } else {
                ps.setInt(1, Integer.parseInt(obid));
            }
            rs = ps.executeQuery();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "取出desk数据出错！");
            e.printStackTrace();
        }
        try {
            while (rs.next()) {
                desk.setId(rs.getInt("id"));
                desk.setNo(rs.getString("no"));
                desk.setSeating(rs.getInt("seating"));
                desk.setStatus(rs.getString("status"));
                list1.add(desk);
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "取出desk数据出错！");
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
        String sql = "select * from desk order by id";
        PreparedStatement ps = null;
        ResultSet rs = null;
        List list = new ArrayList();
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            System.out.println("取出全部数据出错！");
            JOptionPane.showMessageDialog(null, "取出全部数据出错");
            e.printStackTrace();
        }
        try {
            while (rs.next()) {
                Desk desk = new Desk();
                desk.setId(rs.getInt("id"));
                desk.setNo(rs.getString("no"));
                desk.setSeating(rs.getInt("seating"));
                desk.setStatus(rs.getString("status"));
                list.add(desk);
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
                Desk desk = (Desk) it.next();
                String no = desk.getNo();
                int seating = desk.getSeating();
                String status = desk.getStatus();
                sql = "insert into desk(no,seating,status) values(?,?,?)";
                ps = conn.prepareStatement(sql);
                ps.setString(1, no);
                ps.setInt(2, seating);
                ps.setString(3, status);
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
        String sql = "delete from desk where id=?";
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
                JOptionPane.showMessageDialog(null, "关闭数据连接时出错！");
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
                        sql = "update desk set no=? where id=?";
                        break;
                    case 2:
                        sql = "update desk set seating=? where id=?";
                        break;
                    case 3:
                        sql = "update desk set status=? where id=?";
                        break;
                }
                ps = conn.prepareStatement(sql);
                if (col == 2) {
                    ps.setInt(1, Integer.parseInt(value));
                } else {
                    ps.setString(1, value);
                }
                ps.setInt(2, id);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "修改书籍时出错！");
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

    public static void main(String[] arg) {
        DeskDaoImpl deskDao = new DeskDaoImpl();
        /**
         * 测试添加
         */
        /*List<Desk> list=new ArrayList();
        Desk desk=new Desk();
        desk.setNo("1");
        desk.setSeating(8);
        desk.setStatus(BOOKED);
        list.add(desk);
        deskDao.saveList(list);*/
        /**
         * 测试删除
         */
        //deskDao.deleteList(1201);
        /**
         * 测试修改
         */
        /*List<Changed> list = new ArrayList<>();
        Changed ch = new Changed();
        ch.setId(1201);*/
        /*ch.setCol(1);
        ch.setValue("12");*/
        /*ch.setCol(2);
        ch.setValue("12");*/
        /*ch.setCol(3);
        ch.setValue(UNBOOKED);*/
        /*list.add(ch);
        deskDao.update(list);*/
        /**
         * 测试条件查询
         */
        Changed2 ch2=new Changed2();
        List<Changed2> list= new ArrayList<>();
        /*ch2.setObid("1201");
        ch2.setCol(1);*/
        /*ch2.setObid("1");
        ch2.setCol(2);*/
        /*ch2.setObid("12");
        ch2.setCol(3);*/
        ch2.setObid("8");
        ch2.setCol(4);
        /*ch2.setObid(UNBOOKED);
        ch2.setCol(5);*/
        list.add(ch2);
        System.out.println(deskDao.getDesk(list));
        //System.out.println(deskDao.getList());

    }
}