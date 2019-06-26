package com.restaurant.dao.Impl;

import java.sql.*;
import java.util.*;

import javax.swing.JOptionPane;

import com.restaurant.JDBConnection;
import com.restaurant.dao.IBaseDAO;
import com.restaurant.entity.Desk;
import com.restaurant.util.Changed;

import static com.restaurant.util.Constant.*;


public class DeskDaoImpl implements IBaseDAO {
    Desk desk;

    public Desk getDeskById(int id) {
        desk = new Desk();
        Connection conn = JDBConnection.getConn();
        String s1 = "select * from desk where id=?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(s1);
            ps.setInt(1, id);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            JOptionPane.showMessageDialog(null, "取出desk全部数据出错");
            e.printStackTrace();
        }
        try {
            rs.next();
            desk.setId(rs.getInt("id"));
            desk.setNo(rs.getString("no"));
            desk.setSeating(rs.getInt("seating"));
            desk.setStatus(rs.getString("status"));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            JOptionPane.showMessageDialog(null, "取出desk数据出错");
            e.printStackTrace();
        } finally {
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
        return desk;
    }

    public Desk getDeskByNo(String no) {
        desk = new Desk();
        Connection conn = JDBConnection.getConn();
        String s1 = "select * from desk where no=?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(s1);
            ps.setString(1, no);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            JOptionPane.showMessageDialog(null, "取出desk全部数据出错");
            e.printStackTrace();
        }
        try {
            rs.next();
            desk.setId(rs.getInt("id"));
            desk.setNo(rs.getString("no"));
            desk.setSeating(rs.getInt("seating"));
            desk.setStatus(rs.getString("status"));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            JOptionPane.showMessageDialog(null, "取出desk数据出错");
            e.printStackTrace();
        } finally {
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
        return desk;
    }

    @Override
    public List getList() {
        // TODO Auto-generated method stub
        Connection conn = JDBConnection.getConn();
        String s1 = "select * from desk order by no asc";
        PreparedStatement ps = null;
        ResultSet rs = null;
        List list = new ArrayList();
        try {
            ps = conn.prepareStatement(s1);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
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
            // TODO: handle exception
            JOptionPane.showMessageDialog(null, "取出全部数据出错");
            e.printStackTrace();
        } finally {
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
                Desk desk = (Desk) it.next();
                //int id = desk.getId();
                String no = desk.getNo();
                int seating = desk.getSeating();
                String status = UNBOOKED;
                //getClass();
                s1 = "insert into desk(no,seating,status) values(?,?,?)";
                ps = conn.prepareStatement(s1);
                ps.setString(1, no);
                ps.setInt(2, seating);
                ps.setString(3, status);
                ps.executeUpdate();
            }
        }catch (SQLIntegrityConstraintViolationException e){
            JOptionPane.showMessageDialog(null,"对不起，您无法重复添加");
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO: handle exception
            System.out.println("添加数据时出错");
            JOptionPane.showMessageDialog(null, "添加数据时出错");
            e.printStackTrace();
        }
        finally {
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
    public void deleteList(List list) {
        // TODO Auto-generated method stub
        String str = "delete from desk where id=?";
        int id = 0;
        String status;
        Iterator it = list.iterator();
        Connection conn = JDBConnection.getConn();
        PreparedStatement ps = null;
        try {
            while (it.hasNext()) {
                id = ((Desk) it.next()).getId();
                status = ((Desk) it.next()).getStatus();
                if (status.equals(UNBOOKED)) {
                    ps = conn.prepareStatement(str);
                    ps.setInt(1, id);
                    ps.executeUpdate();
                }else {
                    JOptionPane.showMessageDialog(null,"对不起，您无法删除已预订的");
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
        } finally {
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
                        str = "update desk set no=? where id=?";
                        break;
                    case 2:
                        str = "update desk set seating=? where id=?";
                        break;
                    case 3:
                        //str="update desk set status=? where id=?";
                        JOptionPane.showMessageDialog(null, "对不起，您无法直接修改餐台状态");
                        break;
                }
                if (col != 3) {
                    if (col!=1){
                        ps = conn.prepareStatement(str);
                        ps.setString(1, value);
                        ps.setInt(2, id);
                        ps.executeUpdate();
                    }
                    if(col==1&&isCanBeUpdate(id)){
                        ps = conn.prepareStatement(str);
                        ps.setString(1, value);
                        ps.setInt(2, id);
                        ps.executeUpdate();
                    }

                }
            }
        } catch (SQLException e) {
            // TODO: handle exception
            JOptionPane.showMessageDialog(null, "修改数据时出错");
            e.printStackTrace();
        } finally {
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

    public boolean isCanBeUpdate(int id) {  //判断能否更新
        Connection conn = JDBConnection.getConn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from desk where id=?";
        String s = "";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            JOptionPane.showMessageDialog(null, "取出desk全部数据出错");
            e.printStackTrace();
        }
        try {
            rs.next();
            s = rs.getString("status");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            JOptionPane.showMessageDialog(null, "取出desk全部数据出错");
            e.printStackTrace();
        } finally {
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
        if (s.equals(UNBOOKED)) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null,"对不起，您无法修改已经被预定的餐台号");
            return false;
        }
    }public boolean save(Desk desk){
        Connection conn = JDBConnection.getConn();
        PreparedStatement ps = null;
        String no = desk.getNo();
        int seating = desk.getSeating();
        String status=UNBOOKED;
        String sql = "insert into desk(no,seating,status) values(?,?,?)";
        try {
            ps=conn.prepareStatement(sql);
            ps.setString(1,no);
            ps.setInt(2,seating);
            ps.setString(3,status);
            ps.executeUpdate();
        }catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            try {
                ps.close();
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "关闭数据连接时出错!");
                e.printStackTrace();
            }
        }
        return true;
    }
}

