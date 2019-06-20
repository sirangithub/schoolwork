package com.restaurant.frame;

import com.restaurant.dao.Impl.EmployeeDaoImpl;
import com.restaurant.entity.Employee;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.restaurant.util.Constant.*;

public class EmployeeAddDialog extends JDialog {
    Employee employee = null;
    EmployeeDaoImpl employeeDao = null;

    public EmployeeAddDialog() {
        super();
        setModal(true);
        setTitle("员工管理");
        setBounds(100, 100, 500, 475);
        final JPanel inputPanel = new JPanel();
        inputPanel.setLayout(null);
        getContentPane().add(inputPanel, BorderLayout.CENTER);
        employee = new Employee();
        employeeDao = new EmployeeDaoImpl();
        JLabel nameLabel = new JLabel("用户名：");
        JTextField nameTxt = new JTextField(20);
        JLabel sexLable = new JLabel("性别：");
        JComboBox sexIDCombo = new JComboBox();
        sexIDCombo.addItem(MALE);
        sexIDCombo.addItem(FEMALE);
        JLabel birthLabel = new JLabel("出生日期：");
        JTextField birthTxt = new JTextField(20);
        JLabel indLabel = new JLabel("身份证号：");
        JTextField indTxt = new JTextField(20);
        JLabel addrLabel = new JLabel("家庭住址：");
        JTextField addrTxt = new JTextField(20);
        JLabel telLabel = new JLabel("电话：");
        JTextField telTxt = new JTextField(20);
        JLabel poLabel = new JLabel("职位：");
        JComboBox poIDCombo = new JComboBox();
        poIDCombo.addItem(WAITER);
        poIDCombo.addItem(CASHIER);
        poIDCombo.addItem(CLEANER);
        poIDCombo.addItem(SECYRUTY_STAFF);
        poIDCombo.addItem(COOK);
        poIDCombo.addItem(DOTR);
        JLabel frLabel = new JLabel("是否在职：");
        JComboBox frIDCombo = new JComboBox();
        frIDCombo.addItem(ON_FREEZE);
        frIDCombo.addItem(OFF_FREEZE);

        inputPanel.add(nameLabel);
        inputPanel.add(nameTxt);
        inputPanel.add(sexLable);
        inputPanel.add(sexIDCombo);
        inputPanel.add(birthLabel);
        inputPanel.add(birthTxt);
        inputPanel.add(indLabel);
        inputPanel.add(indTxt);
        inputPanel.add(addrLabel);
        inputPanel.add(addrTxt);
        inputPanel.add(telLabel);
        inputPanel.add(telTxt);
        inputPanel.add(poLabel);
        inputPanel.add(poIDCombo);
        inputPanel.add(frLabel);
        inputPanel.add(frIDCombo);

        nameLabel.setBounds(120, 20, 160, 30);
        nameTxt.setBounds(180, 20, 160, 30);
        sexLable.setBounds(120, 60, 160, 30);
        sexIDCombo.setBounds(180, 60, 160, 30);
        birthLabel.setBounds(120, 100, 160, 30);
        birthTxt.setBounds(180, 100, 160, 30);
        indLabel.setBounds(120, 140, 160, 30);
        indTxt.setBounds(180, 140, 160, 30);
        addrLabel.setBounds(120, 180, 160, 30);
        addrTxt.setBounds(180, 180, 160, 30);
        telLabel.setBounds(120, 220, 160, 30);
        telTxt.setBounds(180, 220, 160, 30);
        poLabel.setBounds(120, 260, 160, 30);
        poIDCombo.setBounds(180, 260, 160, 30);
        frLabel.setBounds(120, 300, 160, 30);
        frIDCombo.setBounds(180, 300, 160, 30);

        final JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        JButton okBtn = new JButton(OK);
        JButton cancleBtn = new JButton(CANCER);
        buttonPanel.add(okBtn);
        buttonPanel.add(cancleBtn);
        okBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Employee employee = new Employee();
                EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
                String str1 = (String) sexIDCombo.getSelectedItem();
                String str2 = (String) poIDCombo.getSelectedItem();
                String str3 = (String) frIDCombo.getSelectedItem();
                employee.setName(nameTxt.getText());
                employee.setSex(str1);
                employee.setBirthday(birthTxt.getText());
                employee.setIdentityID(indTxt.getText());
                employee.setAddress(addrTxt.getText());
                employee.setTel(telTxt.getText());
                employee.setPosition(str2);
                employee.setFreeze(str3);
                employeeDao.save(employee);
                setVisible(false);
                JOptionPane.showMessageDialog(null,ASUCCESS);
            }
        });
        cancleBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                setVisible(false);
            }
        });
    }
}
