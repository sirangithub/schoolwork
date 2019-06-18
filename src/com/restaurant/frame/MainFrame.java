package com.restaurant.frame;

import com.restaurant.dao.IBaseDAO;
import com.restaurant.pane.CategoryManagePane2;
import com.restaurant.pane.DeskManagePane2;
import com.restaurant.pane.DishManagePane2;
import com.restaurant.pane.EmployeeManagePane2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    JPanel panel;
    JMenuBar bar;
    JMenu systemMenu,deskMenu,dishesMenu,orderMenu,checkoutMenu,helpMenu;
    JMenuItem emplManage,custManage,pwdManage,categoryManage,dishesManege,
    aboutmeManage,orderManage,checkoutManage,orderItemManage,deskManage;
    FlowLayout layout=new FlowLayout();
    /**
     *
     *
     *
     */
    DishManagePane2 dishManaPane2;
    EmployeeManagePane2 employeeManaPane2;
    DeskManagePane2 deskManaPane2;
    CategoryManagePane2 cateManaPane2;

    /**
     *
     *
     *
     */

    MainFrame(){
        init();
        setBounds(100,100,200,200);
        setSize(1366,738);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void init(){
        bar=new JMenuBar();
        systemMenu=new JMenu("系统管理");
        deskMenu=new JMenu("餐台管理");
        dishesMenu=new JMenu("菜品管理");
        orderMenu=new JMenu("业务管理");
        checkoutMenu=new JMenu("结转管理");
        helpMenu=new JMenu("帮助");
        /**
         *
         */
        emplManage=new JMenuItem("员工管理");
        custManage=new JMenuItem("客户管理");
        pwdManage=new JMenuItem("修改密码");
        /**
         *
         */
        deskManage=new JMenuItem("餐台管理");
        /**
         *
         */
        categoryManage=new JMenuItem("分类管理");
        dishesManege=new JMenuItem("菜品管理");
        /**
         *
         */
        orderManage=new JMenuItem("开台管理");
        orderItemManage=new JMenuItem("点菜管理");
        /**
         *
         */
        checkoutManage=new JMenuItem("结账管理");
        /**
         *
         */
        aboutmeManage=new JMenuItem("关于我们");
        /**
         *
         */
        bar.add(systemMenu);
        bar.add(deskMenu);
        bar.add(dishesMenu);
        bar.add(orderMenu);
        bar.add(checkoutMenu);
        bar.add(helpMenu);

        systemMenu.add(emplManage);
        systemMenu.add(custManage);
        systemMenu.add(pwdManage);
        /**
         *
         */
        deskMenu.add(deskManage);
        /**
         *
         */
        dishesMenu.add(categoryManage);
        dishesMenu.add(dishesManege);
        /**
         *
         */
        orderMenu.add(orderManage);
        orderMenu.add(orderItemManage);
        /**
         *
         */
        checkoutMenu.add(checkoutManage);
        /**
         *
         */
        helpMenu.add(aboutmeManage);
        /**
         *
         */
        panel=new JPanel();



        /**
         *
         *
         *
         *
         */
        employeeManaPane2=new EmployeeManagePane2();
        deskManaPane2=new DeskManagePane2();
        cateManaPane2=new CategoryManagePane2();
        dishManaPane2=new DishManagePane2();
        /**
         *
         *
         *
         *
         */
        emplManage.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                panel.removeAll();
                panel.add("ty",employeeManaPane2);
                panel.validate();
                repaint();
            }
        });
        deskManage.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                panel.removeAll();
                panel.add("dmp",deskManaPane2);
                panel.validate();
                repaint();
            }
        });
        categoryManage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.removeAll();
                panel.add("cmg",cateManaPane2);
                panel.validate();
                repaint();
            }
        });
        dishesManege.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.removeAll();
                panel.add("jk",dishManaPane2);
                panel.validate();
                repaint();
            }
        });
        /**
         *
         */
        panel.setLayout(layout);
        add(panel);
        setJMenuBar(bar);
    }
}
