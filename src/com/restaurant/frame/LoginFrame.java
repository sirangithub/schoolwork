package com.restaurant.frame;
/**
 * 危泰星
 */
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LoginFrame extends JPanel {
    private static final long serialVersionUID = 1834511718758119719L;
    static final int WIDTH = 300;
    static final int HEIGHT = 200;
    JFrame loginframe;
    public void add(Component c,GridBagConstraints constraints,int x,int y,int w,int h) {
        constraints.gridx = x;
        constraints.gridy = y;
        constraints.gridwidth=w;
        constraints.gridheight=h;
        add(c,constraints);
    }
    public LoginFrame() {
        // TODO Auto-generated constructor st

        loginframe = new JFrame("欢迎进入餐饮管理系统");
        loginframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GridBagLayout lay=new GridBagLayout();
        setLayout(lay);
        loginframe.add(this,BorderLayout.WEST);
        loginframe.setSize(WIDTH, HEIGHT);
        Toolkit kit=Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;
        int x = (width-WIDTH)/2;
        int y= (height-HEIGHT)/2;
        loginframe.setLocation(x,y);
        JButton ok=new JButton("提交");
        JButton cancel = new JButton("取消");
        JLabel title=new JLabel("欢迎进入餐饮管理系统");
        JLabel name=new JLabel("用户名");
        JLabel password=new JLabel("密码");
        final JTextField nameinput=new JTextField(15);
        final JTextField passwordinput=new JTextField(15);
        GridBagConstraints constraints=new GridBagConstraints();
        constraints.fill=GridBagConstraints.NONE;
        constraints.anchor=GridBagConstraints.EAST;
        constraints.weightx=3;
        constraints.weighty=4;
        add(title,constraints,0,0,3,1);
        add(name, constraints, 0, 1, 1, 1);
        add(password, constraints, 0, 2, 1, 1);
        add(nameinput, constraints, 2, 1, 1, 1);
        add(passwordinput, constraints, 2, 2, 1, 1);
        add(ok, constraints, 0, 3, 1, 1);
        add(cancel,constraints,2,3,1,1);
        loginframe.setVisible(true);
    }
    public static void main(String[] args) {
        LoginFrame login=new LoginFrame();
    }
}
