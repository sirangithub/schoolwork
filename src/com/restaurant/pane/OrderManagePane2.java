package com.restaurant.pane;

import com.restaurant.dao.IBaseDAO;
import com.restaurant.dao.Impl.OrderDaoImpl;
import com.restaurant.entity.Order;
import com.restaurant.util.ChangeOrderEvent;
import com.restaurant.util.OrderDaoFactory;
import com.restaurant.util.OrderTableModel;

import javax.swing.*;
import javax.swing.table.DefaultTableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import static com.restaurant.util.Constant.*;

public class OrderManagePane2 extends JPanel {
    private JPanel panelTop = null;
    private JLabel labHeader = null;
    private JPanel panelBottom = null;
    private JButton add = null;
    private JButton delete = null;
    private JButton save = null;
    private JScrollPane scroll = null;
    private JTable table = null;
    private OrderTableModel model = null;
    private List listOrder = null;
    OrderDaoImpl orderDao=new OrderDaoImpl();
    public OrderTableModel getModel() {
        if (null == model) {
            model = new OrderTableModel(listOrder);
            // 给model添加一个监听,当修改的时候将触发该事件,代表事件的类是ChangeEvent
            model.addTableModelListener(new ChangeOrderEvent(model));
            return model;
        }
        return model;
    }
    public JLabel getLabHeader() {
        if (null == labHeader) {
            labHeader = new JLabel("Maintaince Order Information");
            return labHeader;
        }
        return labHeader;
    }
    public JTable getTable() {
        if (null == table) {
            table = new JTable(getModel());
            table.setRowSelectionAllowed(true);
            table.setBackground(Color.BLACK);
            table.setSelectionForeground(Color.YELLOW);
            table.setSelectionBackground(Color.RED);
            table.setForeground(Color.WHITE);
            DefaultTableColumnModel dcm = (DefaultTableColumnModel) table.getColumnModel();
            dcm.getColumn(0).setMinWidth(0);
            dcm.getColumn(0).setMaxWidth(0);
            return table;
        }
        return table;
    }
    public JScrollPane getPanTable() {
        if (null == scroll) {
            scroll = new JScrollPane();
            scroll.setViewportView(getTable());
            return scroll;
        }
        return scroll;
    }
    public JPanel getPanelTop() {
        if (null == panelTop) {
            panelTop = new JPanel();
            panelTop.setLayout(new FlowLayout(FlowLayout.CENTER));
            panelTop.add(getLabHeader());
            return panelTop;
        }
        return panelTop;
    }
    ////////////
    ////////////
    ///////////////
    public JPanel getPanelBottom() {
        if (null == panelBottom) {
            panelBottom = new JPanel();
            panelBottom.setLayout(new FlowLayout(FlowLayout.CENTER));
            panelBottom.add(getAdd());
            panelBottom.add(getDelete());
            panelBottom.add(getSave());
            return panelBottom;
        }
        return panelBottom;
    }
    ////////////
    /////////////
    //////////////
    public JButton getAdd() {

        if (null == add) {
            add = new JButton(ADD);
            add.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    addOrder();
                }
            });
            return add;
        }
        return add;
    }
    public JButton getDelete() {
        if (null == delete) {
            delete = new JButton(DELETE);
            delete.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    int[] rows = getTable().getSelectedRows();
                    if (rows.length > 0) {
                        int flag = JOptionPane.showConfirmDialog(null, SURE);
                        if (flag == JOptionPane.YES_OPTION)
                            deleteOrder();
                    } else
                        JOptionPane.showMessageDialog(null, OPTION);
                }
            });
            return delete;
        }
        return delete;
    }
    public JButton getSave() {
        if (null == save) {
            save = new JButton(SAVE);
            save.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    saveOrder();
                    JOptionPane.showMessageDialog(null, USUCCESS);
                }
            });
            return save;
        }
        return save;
    }
    public void addOrder() {
        Order order = new Order();
        getModel().addRow(getTable().getSelectedRow(), order);
    }
    public void saveOrder() {
        IBaseDAO dao = OrderDaoFactory.getDao();
        List changeList = getModel().getChangeList();
        // 如果有修改过就调用update方法
        if (changeList.size() > 0) {
            dao.update(changeList);
            changeList.clear();
        }
        List newRow = getModel().getNewRow();
        // 如果是新增就调用saveList,支持一次增加多行
        if (newRow.size() > 0) {
            dao.saveList(newRow);
            orderDao.ChangeDesk2(newRow);
            getModel().setList(dao.getList());
            getTable().updateUI();
            newRow.clear();
        }
    }
    public void deleteOrder() {

        int[] rows = getTable().getSelectedRows();
        ArrayList list = new ArrayList();
        IBaseDAO dao = OrderDaoFactory.getDao();
        for (int i = rows.length - 1; i >= 0; i--) {
            list.add(getModel().getRow(rows[i]));
            getModel().deleteRow(rows[i]);
        }
        dao.deleteList(list);
        orderDao.ChangeDesk(list);
        getTable().updateUI();
        list.clear();
    }
    public void initData() {

        listOrder = new ArrayList();
        IBaseDAO dao = OrderDaoFactory.getDao();
        listOrder = dao.getList();
    }

    public OrderManagePane2() {
        initData();
        this.setLayout(new BorderLayout());
        add(getPanelTop(), BorderLayout.NORTH);
        add(getPanelBottom(), BorderLayout.SOUTH);
        add(getPanTable(), BorderLayout.CENTER);
        this.setSize(new Dimension(1024,800));
    }
}
