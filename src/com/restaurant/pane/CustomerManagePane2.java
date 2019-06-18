package com.restaurant.pane;

import com.restaurant.dao.IBaseDAO;
import com.restaurant.entity.Customer;
import com.restaurant.util.ChangeCustomerEvent;
import com.restaurant.util.CustomerDaoFactory;
import com.restaurant.util.CustomerTableModel;

import javax.swing.*;
import javax.swing.table.DefaultTableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class CustomerManagePane2 extends JPanel {
    private JPanel panelTop = null;
    private JLabel labHeader = null;
    private JPanel panelBottom = null;
    private JButton add = null;
    private JButton delete = null;
    private JButton save = null;
    private JScrollPane scroll = null;
    private JTable table = null;
    private CustomerTableModel model = null;
    private List listCustomer = null;

    public CustomerTableModel getModel() {
        if (null == model) {
            model = new CustomerTableModel(listCustomer);
            // 给model添加一个监听,当修改的时候将触发该事件,代表事件的类是ChangeEvent
            model.addTableModelListener(new ChangeCustomerEvent(model));
            return model;
        }
        return model;
    }

    public JLabel getLabHeader() {
        if (null == labHeader) {
            labHeader = new JLabel("Maintaince Customer Information");
            return labHeader;
        }
        return labHeader;
    }

    public JTable getTable() {
        if (null == table) {
            table = new JTable(getModel());
            table.setEnabled(true);
            table.setRowSelectionAllowed(true);
            table.setBackground(Color.BLACK);
            table.setSelectionForeground(Color.YELLOW);
            table.setSelectionBackground(Color.RED);
            table.setForeground(Color.WHITE);
            /**
             * 隐藏第一列ID,不显示出来
             */
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

    ////////////////////////////////
    //////////////////////////////////
    //////////////////////////////
    public JButton getAdd() {
        if (null == add) {
            add = new JButton("添加");
            add.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    addCustomer();
                }
            });
            return add;
        }
        return add;
    }

    public JButton getDelete() {
        if (null == delete) {
            delete = new JButton("删除");
            delete.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    /**
                     * 支持一次选中多行后删除
                     */
                    int[] rows = getTable().getSelectedRows();
                    if (rows.length > 0) {
                        int flag = JOptionPane.showConfirmDialog(null, "确定删除?");
                        if (flag == JOptionPane.YES_OPTION)
                            deleteCustomer();
                    } else
                        JOptionPane.showMessageDialog(null, "请选择要删除的行！");
                }
            });
            return delete;
        }
        return delete;
    }

    public JButton getSave() {
        if (null == save) {
            save = new JButton("保存");
            save.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    saveCustomer();
                    JOptionPane.showMessageDialog(null, "更新成功！");
                }
            });
            return save;
        }
        return save;
    }


    //////////////
    /////////////////
    ///////////////
    public void addCustomer() {
        Customer customer = new Customer();
        getModel().addRow(getTable().getSelectedRow(), customer);
    }

    public void saveCustomer() {
        IBaseDAO dao = CustomerDaoFactory.getDao();
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
            getModel().setList(dao.getList());
            getTable().updateUI();
            newRow.clear();
        }
    }

    public void deleteCustomer() {

        int[] rows = getTable().getSelectedRows();
        ArrayList list = new ArrayList();
        IBaseDAO dao = CustomerDaoFactory.getDao();
        for (int i = rows.length - 1; i >= 0; i--) {
            list.add(getModel().getRow(rows[i]));
            getModel().deleteRow(rows[i]);
        }
        dao.deleteList(list);
        getTable().updateUI();
        list.clear();
    }

    public void initData() {

        listCustomer = new ArrayList();
        IBaseDAO dao = CustomerDaoFactory.getDao();
        listCustomer = dao.getList();
    }

    public CustomerManagePane2() {
        initData();
        this.setLayout(new BorderLayout());
        add(getPanelTop(), BorderLayout.NORTH);
        add(getPanelBottom(), BorderLayout.SOUTH);
        add(getPanTable(), BorderLayout.CENTER);
        this.setSize(new Dimension(1024, 800));
    }
}
