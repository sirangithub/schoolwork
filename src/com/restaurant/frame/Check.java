package com.restaurant.frame;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;

import org.omg.PortableServer.ID_ASSIGNMENT_POLICY_ID;

import com.restaurant.JDBConnection;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Check extends JPanel {
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    PreparedStatement ps=null;
    ResultSet rs=null;
    double sum;
    double shishou;
    int id;
    double money=0;
    Connection conn=null;
    /**
     * Create the panel.
     */
    public Check() {
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
        gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        setLayout(gridBagLayout);

        JLabel label = new JLabel("\u8BF7\u8F93\u5165\u8981\u7ED3\u8D26\u7684\u684C\u4F4D\u53F7\u7801");
        GridBagConstraints gbc_label = new GridBagConstraints();
        gbc_label.insets = new Insets(0, 0, 5, 5);
        gbc_label.gridx = 5;
        gbc_label.gridy = 1;
        add(label, gbc_label);

        textField = new JTextField();
        GridBagConstraints gbc_textField = new GridBagConstraints();
        gbc_textField.insets = new Insets(0, 0, 5, 5);
        gbc_textField.fill = GridBagConstraints.HORIZONTAL;
        gbc_textField.gridx = 5;
        gbc_textField.gridy = 2;
        add(textField, gbc_textField);
        textField.setColumns(10);

        JButton button = new JButton("\u786E\u5B9A");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                conn=JDBConnection.getConn();//
                id=Integer.parseInt(textField.getText());
                String sql="select sum(amount*price) from (select orderitem.id,orderitem.name,orderitem.amount from orderitem where orderitem.id=?)as a left join dish b on b.name=a.name";
                try {
                    ps=conn.prepareStatement(sql);
                    ps.setInt(1, id);
                    rs=ps.executeQuery();
                    while (rs.next()) {
                        sum=rs.getInt(1);
                    }
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                System.out.println(sum+"\t"+id);
                textField_1.setText(""+sum);
            }
        });
        GridBagConstraints gbc_button = new GridBagConstraints();
        gbc_button.insets = new Insets(0, 0, 5, 5);
        gbc_button.gridx = 5;
        gbc_button.gridy = 3;
        add(button, gbc_button);

        JButton button_1 = new JButton("\u53D6\u6D88");
        GridBagConstraints gbc_button_1 = new GridBagConstraints();
        gbc_button_1.insets = new Insets(0, 0, 5, 5);
        gbc_button_1.gridx = 5;
        gbc_button_1.gridy = 4;
        add(button_1, gbc_button_1);

        JLabel label_1 = new JLabel("\u5E94\u6536");
        GridBagConstraints gbc_label_1 = new GridBagConstraints();
        gbc_label_1.insets = new Insets(0, 0, 5, 5);
        gbc_label_1.gridx = 5;
        gbc_label_1.gridy = 5;
        add(label_1, gbc_label_1);

        textField_1 = new JTextField();
        GridBagConstraints gbc_textField_1 = new GridBagConstraints();
        gbc_textField_1.insets = new Insets(0, 0, 5, 5);
        gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
        gbc_textField_1.gridx = 5;
        gbc_textField_1.gridy = 6;
        add(textField_1, gbc_textField_1);
        textField_1.setColumns(10);

        JLabel label_2 = new JLabel("\u5B9E\u6536");
        GridBagConstraints gbc_label_2 = new GridBagConstraints();
        gbc_label_2.insets = new Insets(0, 0, 5, 5);
        gbc_label_2.gridx = 5;
        gbc_label_2.gridy = 7;
        add(label_2, gbc_label_2);

        textField_2 = new JTextField();
        GridBagConstraints gbc_textField_2 = new GridBagConstraints();
        gbc_textField_2.insets = new Insets(0, 0, 5, 5);
        gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
        gbc_textField_2.gridx = 5;
        gbc_textField_2.gridy = 8;
        add(textField_2, gbc_textField_2);
        textField_2.setColumns(10);

        JLabel label_3 = new JLabel("\u627E\u96F6");
        GridBagConstraints gbc_label_3 = new GridBagConstraints();
        gbc_label_3.insets = new Insets(0, 0, 5, 5);
        gbc_label_3.gridx = 5;
        gbc_label_3.gridy = 9;
        add(label_3, gbc_label_3);

        textField_3 = new JTextField();
        GridBagConstraints gbc_textField_3 = new GridBagConstraints();
        gbc_textField_3.insets = new Insets(0, 0, 5, 5);
        gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
        gbc_textField_3.gridx = 5;
        gbc_textField_3.gridy = 10;
        add(textField_3, gbc_textField_3);
        textField_3.setColumns(10);

        JButton button_2 = new JButton("\u786E\u5B9A");
        button_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                conn=JDBConnection.getConn();//
                String kong="空";///
                String str="update desk set status=? where id=?";
                try {
                    ps=conn.prepareStatement(str);
                    ps.setString(1, kong);
                    ps.setInt(2, id);
                    ps.executeUpdate();
                } catch (SQLException e2) {
                    // TODO Auto-generated catch block
                    e2.printStackTrace();
                }
                ////
                String string="update orderinfo set status=? where deskId=?";
                try {
                    ps=conn.prepareStatement(string);
                    ps.setString(1, kong);
                    ps.setInt(2, id);
                    ps.executeUpdate();
                } catch (SQLException e2) {
                    // TODO Auto-generated catch block
                    e2.printStackTrace();
                }
                String string1="update orderinfo set money=? where deskId=?";
                try {
                    ps=conn.prepareStatement(string1);
                    ps.setDouble(1, money);
                    ps.setInt(2, id);
                    ps.executeUpdate();
                } catch (SQLException e2) {
                    // TODO Auto-generated catch block
                    e2.printStackTrace();
                }
                ////
                shishou=Double.parseDouble(textField_2.getText());
                System.out.println(shishou);
                textField_3.setText(""+(shishou-sum));
                String sql="delete from orderitem where id=?";
                try {
                    ps=conn.prepareStatement(sql);
                    ps.setInt(1, id);/////////////////////////////
                    ps.executeUpdate();
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });
        GridBagConstraints gbc_button_2 = new GridBagConstraints();
        gbc_button_2.insets = new Insets(0, 0, 5, 5);
        gbc_button_2.gridx = 5;
        gbc_button_2.gridy = 11;
        add(button_2, gbc_button_2);

        JButton button_3 = new JButton("\u6E05\u9664");
        button_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textField.setText("");
                textField_1.setText("");
                textField_2.setText("");
                textField_3.setText("");
            }
        });
        GridBagConstraints gbc_button_3 = new GridBagConstraints();
        gbc_button_3.insets = new Insets(0, 0, 0, 5);
        gbc_button_3.gridx = 5;
        gbc_button_3.gridy = 12;
        add(button_3, gbc_button_3);

    }

}
