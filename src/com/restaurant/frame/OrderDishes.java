package com.restaurant.frame;

import com.restaurant.JDBConnection;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import javax.swing.JTextField;



import java.awt.Insets;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

import static com.restaurant.util.Constant.*;

public class OrderDishes extends JPanel {
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    double sum=-1;
    Connection conn=null;
    public OrderDishes() {
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0};
        gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        setLayout(gridBagLayout);

        JLabel label = new JLabel("\u684C\u4F4D\u53F7\u7801:");
        GridBagConstraints gbc_label = new GridBagConstraints();
        gbc_label.insets = new Insets(0, 0, 5, 5);
        gbc_label.gridx = 1;
        gbc_label.gridy = 1;
        add(label, gbc_label);

        textField = new JTextField();
        GridBagConstraints gbc_textField = new GridBagConstraints();
        gbc_textField.insets = new Insets(0, 0, 5, 0);
        gbc_textField.fill = GridBagConstraints.HORIZONTAL;
        gbc_textField.gridx = 3;
        gbc_textField.gridy = 1;
        add(textField, gbc_textField);
        textField.setColumns(20);

        JLabel label_1 = new JLabel("\u83DC\u54C1\u540D\u79F0:");
        GridBagConstraints gbc_label_1 = new GridBagConstraints();
        gbc_label_1.insets = new Insets(0, 0, 5, 5);
        gbc_label_1.gridx = 1;
        gbc_label_1.gridy = 3;
        add(label_1, gbc_label_1);

        textField_1 = new JTextField();
        GridBagConstraints gbc_textField_1 = new GridBagConstraints();
        gbc_textField_1.insets = new Insets(0, 0, 5, 0);
        gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
        gbc_textField_1.gridx = 3;
        gbc_textField_1.gridy = 3;
        add(textField_1, gbc_textField_1);
        textField_1.setColumns(20);

        JLabel label_2 = new JLabel("\u83DC\u54C1\u6570\u91CF:");
        GridBagConstraints gbc_label_2 = new GridBagConstraints();
        gbc_label_2.insets = new Insets(0, 0, 5, 5);
        gbc_label_2.gridx = 1;
        gbc_label_2.gridy = 5;
        add(label_2, gbc_label_2);

        textField_2 = new JTextField();
        GridBagConstraints gbc_textField_2 = new GridBagConstraints();
        gbc_textField_2.insets = new Insets(0, 0, 5, 0);
        gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
        gbc_textField_2.gridx = 3;
        gbc_textField_2.gridy = 5;
        add(textField_2, gbc_textField_2);
        textField_2.setColumns(20);

        JButton button = new JButton("\u786E\u5B9A");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {////////////////////////////////////////点菜按钮

               //String man="满";
                PreparedStatement ps=null;//
                ResultSet rs=null;
                conn= JDBConnection.getConn();//
                int id=0;
                String name="";
                int amount=0;
                id=Integer.parseInt(textField.getText());
                System.out.println(id);
                name=textField_1.getText();
                amount=Integer.parseInt(textField_2.getText());
                /////////////////
                String str="update desk set status=? where no=?";
                try {
                    ps=conn.prepareStatement(str);
                    ps.setString(1, DTM);
                    ps.setInt(2, id);
                    ps.executeUpdate();
                } catch (SQLException e2) {
                    // TODO Auto-generated catch block
                    e2.printStackTrace();
                }

                String string="update orderinfo set status=? where deskId=?";
                try {
                    ps=conn.prepareStatement(string);
                    ps.setString(1, DTM);
                    ps.setInt(2, id);
                    ps.executeUpdate();
                } catch (SQLException e2) {
                    // TODO Auto-generated catch block
                    e2.printStackTrace();
                }
                //////////////////
                String sql="insert into orderitem(id,name,amount) values(?,?,?)";
                try {
                    ps=conn.prepareStatement(sql);
                    ps.setInt(1, id);
                    ps.setString(2, name);
                    ps.setInt(3, amount);
                    ps.executeUpdate();
                    JOptionPane.showMessageDialog(null, "提交成功");
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    JOptionPane.showMessageDialog(null, "输入点菜信息错误");
                    e1.printStackTrace();
                }
                /////////////
                String sql1="select sum(amount*price) from (select orderitem.id,orderitem.name,orderitem.amount from orderitem where orderitem.id=?)as a left join dish b on b.name=a.name";
                try {
                    ps=conn.prepareStatement(sql1);
                    ps.setInt(1, id);
                    rs=ps.executeQuery();
                    while (rs.next()) {
                        sum=rs.getInt(1);
                    }
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                String string1="update orderinfo set money=? where deskId=?";
                try {
                    System.out.println(sum+"\t"+id);
                    ps=conn.prepareStatement(string1);
                    ps.setDouble(1, sum);
                    ps.setInt(2, id);
                    ps.executeUpdate();
                } catch (SQLException e3) {
                    // TODO Auto-generated catch block
                    e3.printStackTrace();
                }finally {
                    try {
                        ps.close();
                        conn.close();
                    } catch (SQLException e1) {
                        // TODO Auto-generated catch block
                        JOptionPane.showMessageDialog(null, "关闭数据连接时出错");
                        e1.printStackTrace();
                    }
                }
                ////////////////////////
                textField.setText("");
                textField_1.setText("");
                textField_2.setText("");
            }
        });
        GridBagConstraints gbc_button = new GridBagConstraints();
        gbc_button.insets = new Insets(0, 0, 0, 5);
        gbc_button.gridx = 2;
        gbc_button.gridy = 11;
        add(button, gbc_button);

        JButton button_1 = new JButton("\u53D6\u6D88");
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textField.setText("");
                textField_1.setText("");
                textField_2.setText("");
            }
        });
        GridBagConstraints gbc_button_1 = new GridBagConstraints();
        gbc_button_1.gridx = 3;
        gbc_button_1.gridy = 11;
        add(button_1, gbc_button_1);

    }

}
