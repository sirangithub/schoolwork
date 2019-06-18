package com.restaurant.pane;

import com.restaurant.dao.IBaseDAO;
import com.restaurant.entity.Employee;
import com.restaurant.util.ChangeEmployeeEvent;
import com.restaurant.util.EmployeeDaoFactory;
import com.restaurant.util.EmployeeTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class EmployeeManagePane2 extends JPanel {
    private JPanel panelTop = null;
    private JLabel labHeader = null;
    private JPanel panelBottom = null;
    private JButton add = null;
    private JButton save = null;
    private JButton delete = null;
    private JScrollPane scroll=null;
    private JTable table = null;
    private EmployeeTableModel model=null;
    private List listStaff=null;
    public EmployeeTableModel getModel() {
        if (null==model) {
            model=new EmployeeTableModel(listStaff);
            model.addTableModelListener(new ChangeEmployeeEvent(model));
            return model;
        }
        return model;
    }
    public JLabel getLabHeader() {
        if (labHeader==null) {
            labHeader=new JLabel("Maintaince Employee Information");
            return labHeader;
        }
        return labHeader;
    }
    public JTable getTable() {
        if (null==table) {
            table=new JTable(getModel());
            table.setEnabled(true);
            table.setRowSelectionAllowed(true);
            //table.setBackground(Color.white);
            table.setSelectionForeground(Color.white);
            table.setSelectionBackground(Color.GRAY);
            /**
             * 隐藏第一列id,不现实出来
             */
		   /*DefaultTableColumnModel dcm=(DefaultTableColumnModel) table.getColumnModel();
			dcm.getColumn(0).setMinWidth(0);
			dcm.getColumn(0).setMaxWidth(0);*/
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
    public JPanel getPaneTop() {
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
    public JButton getAdd() {
        if (null==add) {
            add=new JButton("添加");
            add.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    // TODO Auto-generated method stub
                    ////////////////////////////////////////此处为完成
                    addEmployee();
                }
            });
            return add;
        }
        return add;
    }
    public JButton getDelete() {
        if (null==delete) {
            delete=new JButton("删除");
            delete.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    // TODO Auto-generated method stub
                    int[] rows=getTable().getSelectedRows();
                    if (rows.length>0) {
                        int flag=JOptionPane.showConfirmDialog(null, "确定删除?");
                        if (flag==JOptionPane.YES_OPTION) {
                            deleteEmployee();
                        }
                    } else {
                        JOptionPane.showConfirmDialog(null, "请选择要删除的行!");
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
                    saveEmployee();
                    JOptionPane.showMessageDialog(null, "更新成功staff数据库成功");
                }
            });
            return save;
        }
        return save;
    }


    public void addEmployee() {
        Employee staff=new Employee();
        getModel().addRow(getTable().getSelectedRow(), staff);
    }
    public void saveEmployee() {
        IBaseDAO dao= EmployeeDaoFactory.getDao();
        List changeList=getModel().getChangeList();
        if (changeList.size()>0) {
            dao.update(changeList);
            changeList.clear();/////////////////////////////未知
        }
        List newRow=getModel().getNewRow();
        if (0<newRow.size()) {
            dao.saveList(newRow);
            getModel().setList(dao.getList());
            getTable().updateUI();
            newRow.clear();
        }
    }
    public void deleteEmployee() {
        int[] rows=getTable().getSelectedRows();
        ArrayList list=new ArrayList();
        IBaseDAO dao=EmployeeDaoFactory.getDao();
        for (int i = rows.length-1; i >=0; i--) {
            list.add(getModel().getRow(rows[i]));
            getModel().deleteRow(rows[i]);
            dao.deleteList(list);
            getTable().updateUI();
            list.clear();
        }
    }
    public void initData() {
        listStaff=new ArrayList();
        IBaseDAO dao=EmployeeDaoFactory.getDao();
        listStaff=dao.getList();
    }
    public EmployeeManagePane2() {
        initData();
        this.setLayout(new BorderLayout());
        ///////////////////////////////此处未完成
        add(getPaneTop(),BorderLayout.NORTH);
        add(getPanelBottom(), BorderLayout.SOUTH);
        add(getPanTable(),BorderLayout.CENTER);
        this.setSize(new Dimension(1024, 800));
    }

}
