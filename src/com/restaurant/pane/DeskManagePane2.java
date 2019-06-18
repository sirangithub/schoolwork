package com.restaurant.pane;

import com.restaurant.dao.IBaseDAO;
import com.restaurant.entity.Desk;
import com.restaurant.util.ChangeDeskEvent;
import com.restaurant.util.DeskDaoFactory;
import com.restaurant.util.DeskTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class DeskManagePane2 extends JPanel {
    private JPanel panelTop = null;
    private JLabel labHeader = null;
    private JPanel panelBottom = null;
    private JButton add = null;
    private JButton save = null;
    private JButton delete = null;
    private JScrollPane scroll=null;
    private JTable table = null;
    private DeskTableModel model=null;
    private List listDesk=null;
    public DeskTableModel getModel() {
        if (model==null) {
            model=new DeskTableModel(listDesk);
            model.addTableModelListener(new ChangeDeskEvent(model));
            return model;
        }
        return model;
    }
    public JLabel getLabHeader() {
        if (null==labHeader) {
            labHeader=new JLabel("Maintaince Desk Information");
            return labHeader;
        }
        return labHeader;
    }
    public JTable getTable() {
        if (null==table) {
            table=new JTable(getModel());
            table.setEnabled(true);
            table.setRowSelectionAllowed(true);
            table.setBackground(Color.BLACK);
            table.setSelectionForeground(Color.YELLOW);
            table.setSelectionBackground(Color.RED);
            table.setForeground(Color.WHITE);
            /**
             *
             */
            return table;
        }
        return table;
    }
    public JScrollPane getPanTable() {
        if (null==scroll) {
            scroll=new JScrollPane();
            scroll.setViewportView(getTable());
            return scroll;
        }
        return scroll;
    }
    public JPanel getPanelTop() {
        if (null==panelTop) {
            panelTop=new JPanel();
            panelTop.setLayout(new FlowLayout(FlowLayout.CENTER));
            panelTop.add(getLabHeader());
            return panelTop;
        }
        return panelTop;
    }
    public JPanel getPanelBottom() {
        if (null==panelBottom) {
            panelBottom=new JPanel();
            panelBottom.setLayout(new FlowLayout(FlowLayout.CENTER));
            panelBottom.add(getAdd());
            panelBottom.add(getDelete());
            panelBottom.add(getSave());
            return panelBottom;
        }
        return panelBottom;
    }
    public void initData() {
        listDesk=new ArrayList();
        IBaseDAO dao= DeskDaoFactory.getDao();
        listDesk=dao.getList();
    }
    public DeskManagePane2() {
        initData();
        this.setLayout(new BorderLayout());
        add(getPanelTop(), BorderLayout.NORTH);
        add(getPanelBottom(), BorderLayout.SOUTH);
        add(getPanTable(), BorderLayout.CENTER);
        this.setSize(new Dimension(1024,800));
    }
    public void addDesk() {
        Desk desk=new Desk();
        getModel().addRow(getTable().getSelectedRow(), desk);
    }
    public void saveDesk() {
        IBaseDAO dao=DeskDaoFactory.getDao();
        List changeList=getModel().getChangeList();
        if (changeList.size()>0) {
            dao.update(changeList);
            changeList.clear();
        }
        List newRow=getModel().getNewRow();
        if (newRow.size()>0) {
            dao.saveList(newRow);
            getModel().setList(dao.getList());
            getTable().updateUI();
            newRow.clear();
        }
    }
    public void deleteDesk() {
        int[] rows=getTable().getSelectedRows();
        ArrayList list=new ArrayList();
        IBaseDAO dao=DeskDaoFactory.getDao();
        for (int i = rows.length-1; i >=0; i--) {
            list.add(getModel().getRow(rows[i]));
            getModel().deleteRow(rows[i]);
        }
        dao.deleteList(list);
        getTable().updateUI();
        list.clear();
    }
    public JButton getAdd() {
        if (null==add) {
            add=new JButton("添加");
            add.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    // TODO Auto-generated method stub
                    addDesk();
                }
            });
            return add;
        }
        return add;
    }
    public JButton getDelete() {
        if (null==delete) {
            delete=new JButton("删除");
            delete.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    // TODO Auto-generated method stub
                    int[] rows=getTable().getSelectedRows();
                    if (rows.length>0) {
                        int flag=JOptionPane.showConfirmDialog(null, "确定删除?");
                        if (flag==JOptionPane.YES_OPTION) {
                            deleteDesk();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "请选择要删除的行");
                    }
                }
            });
            return delete;
        }
        return delete;
    }
    public JButton getSave() {
        if (null==save) {
            save=new JButton("保存");
            save.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    // TODO Auto-generated method stub
                    saveDesk();
                    JOptionPane.showMessageDialog(null, "更新成功");
                }
            });
            return save;
        }
        return save;
    }
}
