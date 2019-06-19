package com.restaurant.frame;

import com.restaurant.dao.Impl.CustomerDaoImpl;
import com.restaurant.entity.Customer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CustomerAddDialog extends JDialog {
    Customer customer=null;
    CustomerDaoImpl customerDao=null;
   // List<Customer> customerList=null;
    public CustomerAddDialog(){
        super();
        setModal(true);
        setTitle("客户管理");
        setBounds(100, 100, 500, 475);
        final JPanel inputPanel = new JPanel();
        inputPanel.setLayout(null);
        getContentPane().add(inputPanel, BorderLayout.CENTER);
        customer=new Customer();
        customerDao=new CustomerDaoImpl();
        //customerList=customerDao.getList();
        JLabel nameLabel=new JLabel("用户名：");
        JTextField nameTxt=new JTextField(20);
        JLabel sexLable=new JLabel("性别：");
        JComboBox sexIDCombo=new JComboBox();
        sexIDCombo.addItem("男");
        sexIDCombo.addItem("女");
        JLabel comLabel=new JLabel("单位：");
        JTextField comTxt=new JTextField(20);
        JLabel telLabel=new JLabel("电话：");
        JTextField telTxt=new JTextField(20);
        inputPanel.add(nameLabel);
        inputPanel.add(nameTxt);
        inputPanel.add(sexLable);
        inputPanel.add(sexIDCombo);
        inputPanel.add(comLabel);
        inputPanel.add(comTxt);
        inputPanel.add(telLabel);
        inputPanel.add(telTxt);
        nameLabel.setBounds(120,20,160,30);
        nameTxt.setBounds(180,20,160,30);
        sexLable.setBounds(120,60,160,30);
        sexIDCombo.setBounds(180,60,160,30);
        comLabel.setBounds(120,100,160,30);
        comTxt.setBounds(180,100,160,30);
        telLabel.setBounds(120,140,160,30);
        telTxt.setBounds(180,140,160,30);
        final JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        JButton okBtn = new JButton("确定");
        JButton cancleBtn = new JButton("取消");
        buttonPanel.add(okBtn);
        buttonPanel.add(cancleBtn);
        okBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Customer customer = new Customer();
                CustomerDaoImpl customerDao = new CustomerDaoImpl();
                String str = (String) sexIDCombo.getSelectedItem();
                customer.setName(nameTxt.getText());
                customer.setCompany(comTxt.getText());
                customer.setSex(str);
                customer.setTel(telTxt.getText());
                customer.setCardID(telTxt.getText());
                customerDao.save(customer);
                setVisible(false);
            }
        });
        cancleBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                setVisible(false);
            }
        });
    }
}
