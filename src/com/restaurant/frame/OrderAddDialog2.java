package com.restaurant.frame;

import com.restaurant.dao.Impl.DeskDaoImpl;
import com.restaurant.dao.Impl.OrderDaoImpl;
import com.restaurant.entity.Desk;
import com.restaurant.entity.Order;
import java.util.List;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static com.restaurant.util.Constant.ASUCCESS;
import static com.restaurant.util.Constant.CANCER;
import static com.restaurant.util.Constant.OK;

public class OrderAddDialog2 extends JDialog {
    Desk desk = new Desk();
    DeskDaoImpl deskDao = new DeskDaoImpl();

    public OrderAddDialog2(String no, String cno) {
        super();
        setModal(true);
        setTitle("添加就餐人数");
        setBounds(100, 100, 500, 475);
        final JPanel inputPanel = new JPanel();
        inputPanel.setLayout(null);
        getContentPane().add(inputPanel, BorderLayout.CENTER);
        JLabel nuLabel = new JLabel("就餐人数:");
        JComboBox nuIDCombo = new JComboBox();
        //String no= String.valueOf(dknIDCombo.getSelectedItem());
        int snum = deskDao.getDeskByNo(no).getSeating();
        for (int i = 1; i <= snum; i++) {
            nuIDCombo.addItem(i);
        }
        inputPanel.add(nuLabel);
        inputPanel.add(nuIDCombo);

        nuLabel.setBounds(120, 20, 160, 30);
        nuIDCombo.setBounds(180, 20, 160, 30);

        final JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        JButton okBtn = new JButton(OK);
        JButton cancleBtn = new JButton(CANCER);
        buttonPanel.add(okBtn);
        //buttonPanel.add(cancleBtn);

        okBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Order order=new Order();
                OrderDaoImpl orderDao=new OrderDaoImpl();
                List<Order> list=new ArrayList<Order>();
                int num= (int) nuIDCombo.getSelectedItem();
                order.setDeskId(Integer.parseInt(no));
                order.setCustomerId(Integer.parseInt(cno));
                order.setNumber(num);
                list.add(order);
                orderDao.save(order);
                orderDao.ChangeDesk2(list);
                setVisible(false);

            }

        });
    }
}
