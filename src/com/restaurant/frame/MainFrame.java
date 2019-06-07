package com.restaurant.frame;
/**
 * @author weitaixing
 * 系统主窗口
 */
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MainFrame extends JFrame {

	JPanel panel;
	JMenuBar bar;
	JMenu systemMenu,deskMenu,dishesMenu,orderMenu,checkoutMenu,helpMenu;
	JMenuItem emplManage,custManage,pwdManage,categoryManage,dishesManage,aboutmeManage,orderManage,checkoutManage,orderItemManage,
	deskManage;
	FlowLayout layout =  new FlowLayout();
	/**
	 * Launch the application.
	 */
	/**
	 * Create the frame.
	 */
	/**
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * �ܶ����û��д
	 */
	
	
	
	public MainFrame() {
		init();
		setSize(1366, 728);
		//setBounds(100, 100, 200, 200);//此处无用,setbounds表示位置和大小
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void init() {
		/**
		 * 
		 */
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
		dishesManage=new JMenuItem("菜品管理");
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
		/**
		 * 
		 */
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
		dishesMenu.add(dishesManage);
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
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 很多内容没实现
		 */
		panel.setLayout(layout);
		add(panel);
		setJMenuBar(bar);
	}
}
















