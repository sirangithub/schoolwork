package com.restaurant.frame;

import com.restaurant.dao.Impl.CustomerDaoImpl;
import com.restaurant.dao.Impl.DeskDaoImpl;
import com.restaurant.dao.Impl.OrderDaoImpl;
import com.restaurant.entity.Customer;
import com.restaurant.entity.Desk;
import com.restaurant.entity.Order;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import static com.restaurant.util.Constant.ASUCCESS;
import static com.restaurant.util.Constant.CANCER;
import static com.restaurant.util.Constant.OK;

public class OrderAddDialog extends JDialog {
    Order order=null;
    OrderDaoImpl orderDao=null;
    DeskDaoImpl deskDao=null;
    List<Desk> desks=null;
    CustomerDaoImpl customerDao=null;
    List<Customer> customers=null;
    public OrderAddDialog(){
        super();
        setModal(true);
        setTitle("开台管理");
        setBounds(100, 100, 500, 475);
        final JPanel inputPanel = new JPanel();
        inputPanel.setLayout(null);
        getContentPane().add(inputPanel, BorderLayout.CENTER);
        order=new Order();
        orderDao=new OrderDaoImpl();
        deskDao=new DeskDaoImpl();
        customerDao=new CustomerDaoImpl();
        desks=deskDao.getList();
        customers=customerDao.getList();
        JLabel dknLabel=new JLabel("餐台编号：");
        JComboBox dknIDCombo=new JComboBox();
        for(Desk desk:desks){
            dknIDCombo.addItem(desk.getNo());
        }
        JLabel cusLabel=new JLabel("客户编号:");
        JComboBox cusIDCombo=new JComboBox();
        for(Customer customer:customers){
            cusIDCombo.addItem(customer.getCardID());
        }
       /* JLabel nuLabel=new JLabel("就餐人数:");
        JComboBox nuIDCombo=new JComboBox();
        String no= String.valueOf(dknIDCombo.getSelectedItem());
        int snum=deskDao.getDeskByNo(no).getSeating();
        for(int i=1;i<=snum;i++){
            nuIDCombo.addItem(i);
        }*/

        inputPanel.add(dknLabel);
        inputPanel.add(dknIDCombo);
        inputPanel.add(cusLabel);
        inputPanel.add(cusIDCombo);
       /* inputPanel.add(nuLabel);
        inputPanel.add(nuIDCombo);*/

        dknLabel.setBounds(120, 20, 160, 30);
        dknIDCombo.setBounds(180, 20, 160, 30);
        cusLabel.setBounds(120, 60, 160, 30);
        cusIDCombo.setBounds(180, 60, 160, 30);
        /*nuLabel.setBounds(120, 100, 160, 30);
        nuIDCombo.setBounds(180, 100, 160, 30);*/

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
                Order order=new Order();
                OrderDaoImpl orderDao=new OrderDaoImpl();
                String no=(String) dknIDCombo.getSelectedItem();
                String cno=(String)cusIDCombo.getSelectedItem();
               // List<Order> list=new ArrayList<Order>();
                //int num= (int) nuIDCombo.getSelectedItem();
                //order.setDeskId(Integer.parseInt(no));
                //order.setCustomerId(Integer.parseInt(cno));
                //order.setNumber(num);
                //list.add(order);
               // orderDao.save(order);
               // orderDao.ChangeDesk2(list);

                setVisible(false);
                //JOptionPane.showMessageDialog(null, ASUCCESS);
                OrderAddDialog2 orderAddDialog2=new OrderAddDialog2(no,cno);
                orderAddDialog2.setVisible(true);

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


    public static void main(String[] args){
        OrderAddDialog orderAddDialog=new OrderAddDialog();
        orderAddDialog.setVisible(true);
    }
}
