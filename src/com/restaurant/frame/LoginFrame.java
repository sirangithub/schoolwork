package com.restaurant.frame;


import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.restaurant.frame.MainFrame;


import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

class UserLogin{
    String user;
    String password;
    public UserLogin(String user,String password) {
        this.user = user;
        this.password = password;
    }
    public boolean isLoginSuccess() {
        if (user.equals("admin")&&password.equals("888888")) {
            return true;
        }
        else {
            return false;
        }
    }
}
public class LoginFrame extends JFrame {
    private JPanel contentPane;
    private JTextField textField;
    private JTextField textField_1;
    static LoginFrame frame;
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    frame = new LoginFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public LoginFrame() {
        setTitle("\u767B\u9646");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        GridBagLayout gbl_contentPane = new GridBagLayout();
        gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
        gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
        gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        contentPane.setLayout(gbl_contentPane);

        JLabel label = new JLabel("\u7528\u6237:");
        GridBagConstraints gbc_label = new GridBagConstraints();
        gbc_label.insets = new Insets(0, 0, 5, 5);
        gbc_label.gridx = 3;
        gbc_label.gridy = 1;
        contentPane.add(label, gbc_label);

        textField = new JTextField();
        GridBagConstraints gbc_textField = new GridBagConstraints();
        gbc_textField.gridwidth = 6;
        gbc_textField.insets = new Insets(0, 0, 5, 5);
        gbc_textField.fill = GridBagConstraints.HORIZONTAL;
        gbc_textField.gridx = 4;
        gbc_textField.gridy = 1;
        contentPane.add(textField, gbc_textField);
        textField.setColumns(20);

        JLabel label_1 = new JLabel("\u5BC6\u7801:");
        GridBagConstraints gbc_label_1 = new GridBagConstraints();
        gbc_label_1.insets = new Insets(0, 0, 5, 5);
        gbc_label_1.gridx = 3;
        gbc_label_1.gridy = 3;
        contentPane.add(label_1, gbc_label_1);

        textField_1 = new JTextField();
        GridBagConstraints gbc_textField_1 = new GridBagConstraints();
        gbc_textField_1.gridwidth = 6;
        gbc_textField_1.insets = new Insets(0, 0, 5, 5);
        gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
        gbc_textField_1.gridx = 4;
        gbc_textField_1.gridy = 3;
        contentPane.add(textField_1, gbc_textField_1);
        textField_1.setColumns(20);

        JButton button = new JButton("\u786E\u5B9A");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                UserLogin userLogin = new UserLogin(textField.getText(), textField_1.getText());
                System.out.println(textField.getText()+textField_1.getText());
                if (userLogin.isLoginSuccess()) {
                    JOptionPane.showMessageDialog(null, "登陆成功");
                    frame.dispose();
                    MainFrame mf=new MainFrame();
                    mf.setTitle("餐饮管理系统");
                } else {
                    JOptionPane.showMessageDialog(null, "用户名或者密码错误!");
                    textField_1.setText("");
                }
            }
        });
        GridBagConstraints gbc_button = new GridBagConstraints();
        gbc_button.insets = new Insets(0, 0, 5, 5);
        gbc_button.gridx = 8;
        gbc_button.gridy = 4;
        contentPane.add(button, gbc_button);

        JButton button_1 = new JButton("\u53D6\u6D88");
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textField.setText("");
                textField_1.setText("");
            }
        });
        GridBagConstraints gbc_button_1 = new GridBagConstraints();
        gbc_button_1.insets = new Insets(0, 0, 5, 5);
        gbc_button_1.gridx = 8;
        gbc_button_1.gridy = 5;
        contentPane.add(button_1, gbc_button_1);
    }

}
