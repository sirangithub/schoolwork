package com.restaurant.pane;

import com.restaurant.dao.IBaseDAO;
import com.restaurant.entity.Category;
import com.restaurant.util.CategoryDaoFactory;
import com.restaurant.util.CategoryTableModel;
import com.restaurant.util.ChangeCategoryEvent;

import javax.swing.*;
import javax.swing.table.DefaultTableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import static com.restaurant.util.Constant.*;

public class CategoryManagePane2 extends JPanel {
    private JPanel panelTop = null;
    private JLabel labHeader = null;
    private JPanel panelBottom = null;
    private JButton add = null;
    private JButton delete = null;
    private JButton save = null;
    private JScrollPane scroll = null;
    private JTable table = null;
    private CategoryTableModel model = null;
    private List listCategory = null;
    public CategoryTableModel getModel() {
        if (null == model) {
            model = new CategoryTableModel(listCategory);
            // 给model添加一个监听,当修改的时候将触发该事件,代表事件的类是ChangeEvent
            model.addTableModelListener(new ChangeCategoryEvent(model));
            return model;
        }
        return model;
    }
    public JLabel getLabHeader() {
        if (null == labHeader) {
            labHeader = new JLabel("Maintaince Category Information");
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
    public JButton getAdd() {
        /**
         * 点该按钮的时候调用addProduct()方法,在数据源(listProduct)将 增加一个元素,没设值前都是null.
         */
        if (null == add) {
            add = new JButton(ADD);
            add.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    addCategory();
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
                    /**
                     * 支持一次选中多行后删除
                     */
                    int[] rows = getTable().getSelectedRows();
                    if (rows.length > 0) {
                        int flag = JOptionPane.showConfirmDialog(null, SURE);
                        if (flag == JOptionPane.YES_OPTION)
                            deleteCategory();
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
                    saveCategory();
                    JOptionPane.showMessageDialog(null, USUCCESS);
                }
            });
            return save;
        }
        return save;
    }
    public void addCategory() {
        Category cust = new Category();
        getModel().addRow(getTable().getSelectedRow(), cust);
    }
    public void saveCategory() {
        IBaseDAO dao = CategoryDaoFactory.getDao();
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
    public void deleteCategory() {
        /**
         * 支持一次删除多行,先获得所有选中的行,然后按照行数取得Product实例,
         * 放进一个list,然后传给操作数据库的deleteList方法.
         */
        int[] rows = getTable().getSelectedRows();
        ArrayList list = new ArrayList();
        IBaseDAO dao = CategoryDaoFactory.getDao();
        for (int i = rows.length - 1; i >= 0; i--) {
            list.add(getModel().getRow(rows[i]));
            getModel().deleteRow(rows[i]);
        }
        dao.deleteList(list);
        getTable().updateUI();
        list.clear();
    }
    public void initData() {
        /**
         * 初始化数据源,从数据库里把数据拿出来,然后它会调用 getValueAt方法来一个单元格一个单元格来设值,让它显示出来.
         */
        listCategory = new ArrayList();
        IBaseDAO dao = CategoryDaoFactory.getDao();
        listCategory = dao.getList();
    }
    public CategoryManagePane2() {
        initData();
        this.setLayout(new BorderLayout());
        add(getPanelTop(), BorderLayout.NORTH);
        add(getPanelBottom(), BorderLayout.SOUTH);
        add(getPanTable(), BorderLayout.CENTER);
        this.setSize(new Dimension(1024,800));
    }
}
