package com.restaurant.dao.Impl;

import com.restaurant.JDBConnection;
import com.restaurant.dao.IBaseDAO;
import com.restaurant.entity.Report;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ReportDaoImpl implements IBaseDAO {
    @Override
    public List getList() {
        Connection conn = JDBConnection.getConn();
        String sql = "select report.id,deskno,dishname,price,amount from report,dish\n" +
                "where report.dishname=dish.name order by report.id desc";
        PreparedStatement ps = null;
        ResultSet rs = null;
        List list = new ArrayList();
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "取出全部数据出错");
            e.printStackTrace();
        }
        try {
            while (rs.next()) {
                Report report = new Report();
                report.setId(rs.getInt("id"));
                report.setDeskno(rs.getString("deskno"));
                report.setDishname(rs.getString("dishname"));
                report.setPrice(rs.getDouble("price"));
                report.setAmount(rs.getInt("amount"));
                list.add(report);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "取出全部数据出错");
            e.printStackTrace();
        }finally {
            try{
                rs.close();
                ps.close();
                conn.close();
            }catch (SQLException e){
                JOptionPane.showMessageDialog(null, "关闭数据库连接出错");
                e.printStackTrace();
            }
        }
        return list;
    }

    @Override
    public void saveList(List list) {


    }

    @Override
    public void deleteList(List list) {
        String str="delete from report where id=?";
        int id=0;
        Iterator it=list.iterator();
        Connection conn=JDBConnection.getConn();
        PreparedStatement ps=null;
        try {
            while (it.hasNext()) {
                id=((Report)it.next()).getId();
                ps=conn.prepareStatement(str);
                ps.setInt(1, id);
                ps.executeUpdate();
            }
        } catch (Exception e) {
        }finally {
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

    }

    public static void main(String [] args){
        IBaseDAO iBaseDAO=new ReportDaoImpl();
        System.out.println(iBaseDAO.getList());
    }
}
