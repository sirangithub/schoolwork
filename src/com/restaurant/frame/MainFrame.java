package com.restaurant.frame;

import com.restaurant.dao.IBaseDAO;
import com.restaurant.pane.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    JPanel panel;
    JMenuBar bar;
    JMenu systemMenu,deskMenu,dishesMenu,orderMenu,checkoutMenu,reportMenu,helpMenu;
    JMenuItem emplManage,custManage,pwdManage,categoryManage,dishesManege,
    aboutmeManage,orderManage,checkoutManage,orderItemManage,deskManage,reportManage;
    FlowLayout layout=new FlowLayout();

    EmployeeManagePane2 employeeManaPane2;
    CustomerManagePane2 customerManaPane2;
    CategoryManagePane2 cateManaPane2;
    DishManagePane2 dishManaPane2;
    DeskManagePane2 deskManaPane2;
    OrderManagePane2 orderManaPane2;
    OrderDishes orderDishes;
    ReportManagePane2 reportManaPane2;
    Check check;

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
        reportMenu=new JMenu("近期盈利情况");
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
        reportManage=new JMenuItem("盈利报表");
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
        bar.add(reportMenu);
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
        reportMenu.add(reportManage);
        /**
         *
         */
        helpMenu.add(aboutmeManage);
        /**
         *
         */
        panel=new JPanel();

        emplManage.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                employeeManaPane2=new EmployeeManagePane2();
                panel.removeAll();
                panel.add("ty",employeeManaPane2);
                panel.validate();
                repaint();
            }
        });
        custManage.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                customerManaPane2=new CustomerManagePane2();
                panel.removeAll();
                panel.add("客户管理",customerManaPane2);
                panel.validate();
                repaint();
            }
        });

        categoryManage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cateManaPane2=new CategoryManagePane2();
                panel.removeAll();
                panel.add("cmg",cateManaPane2);
                panel.validate();
                repaint();
            }
        });
        dishesManege.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dishManaPane2=new DishManagePane2();
                panel.removeAll();
                panel.add("jk",dishManaPane2);
                panel.validate();
                repaint();
            }
        });
        deskManage.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                deskManaPane2=new DeskManagePane2();
                panel.removeAll();
                panel.add("dmp",deskManaPane2);
                panel.validate();
                repaint();
            }
        });
        orderManage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                orderManaPane2=new OrderManagePane2();
                panel.removeAll();;
                panel.add("omp",orderManaPane2);
                panel.validate();
                repaint();
            }
        });
        orderItemManage.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                orderDishes=new OrderDishes();
                panel.removeAll();
                panel.add("od",orderDishes);
                panel.validate();
                repaint();
            }
        });
        checkoutManage.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                check=new Check();
                panel.removeAll();
                panel.add("che",check);
                panel.validate();
                repaint();
            }
        });
        reportManage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                reportManaPane2=new ReportManagePane2();
                panel.removeAll();
                panel.add("rep",reportManaPane2);
                panel.validate();
                repaint();
            }
        });
        panel.setLayout(layout);
        add(panel);
        setJMenuBar(bar);
    }
}
