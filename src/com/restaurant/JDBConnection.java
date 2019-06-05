package com.restaurant;

import java.sql.*;

/**
 * @author zhangrong
 * 数据库驱动器
 */

public class JDBConnection {
    private static Connection conn;
    private static final String DRIVER="com.mysql.cj.jdbc.Driver";
    private static final String URL="jdbc:mysql://localhost:3306/restaurant?useUnicode=true" +
                                     "&characterEncoding=utf-8&serverTimezone=UTC&useSSL=false";
    private static final String USERNAME="root";
    private static final String PASSWORD="amy19990203";
    static {
        try {
            Class.forName(DRIVER);
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        System.out.print(getConn());
    }
    public static Connection getConn(){
        Connection conn=null;
        try{
            conn= DriverManager.getConnection(URL,USERNAME,PASSWORD);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return conn;
    }
    public static void close(Connection conn, Statement st, ResultSet rs){
        try{
            if(rs!=null){
                rs.close();
            }
            if(st!=null){
                st.close();
            }
            if(conn!=null){
                conn.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
