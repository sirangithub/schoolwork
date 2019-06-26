package com.restaurant.frame;

import com.restaurant.dao.Impl.CustomerDaoImpl;
import com.restaurant.dao.Impl.DeskDaoImpl;
import com.restaurant.entity.Desk;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.restaurant.util.Constant.ASUCCESS;
import static com.restaurant.util.Constant.CANCER;
import static com.restaurant.util.Constant.OK;

public class DeskAddDialog extends JDialog {
    Desk desk = null;
    DeskDaoImpl deskDao = null;

    public DeskAddDialog() {
        super();
        setModal(true);
        setTitle("菜品管理");
        setBounds(100, 100, 500, 475);
        final JPanel inputPanel = new JPanel();
        inputPanel.setLayout(null);
        getContentPane().add(inputPanel, BorderLayout.CENTER);
        desk = new Desk();
        deskDao = new DeskDaoImpl();
        JLabel dknLabel = new JLabel("餐台编号：");
        JTextField dknTxt = new JTextField(20);
        JLabel seLabel = new JLabel("座位数：");
        JTextField seTxt = new JTextField(20);

        inputPanel.add(dknLabel);
        inputPanel.add(dknTxt);
        inputPanel.add(seLabel);
        inputPanel.add(seTxt);

        dknLabel.setBounds(120, 20, 160, 30);
        dknTxt.setBounds(180, 20, 160, 30);
        seLabel.setBounds(120, 60, 160, 30);
        seTxt.setBounds(180, 60, 160, 30);

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
                Desk desk = new Desk();
                DeskDaoImpl deskDao=new DeskDaoImpl();
                desk.setNo(dknTxt.getText());
                desk.setSeating(Integer.parseInt(seTxt.getText()));
                deskDao.save(desk);
                setVisible(false);
                JOptionPane.showMessageDialog(null, ASUCCESS);
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
