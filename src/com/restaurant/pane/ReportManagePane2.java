package com.restaurant.pane;

import com.restaurant.dao.IBaseDAO;
import com.restaurant.dao.Impl.ReportDaoImpl;
import com.restaurant.util.ChangeReportEvent;
import com.restaurant.util.ReportDaoFactory;
import com.restaurant.util.ReportTableModel;

import javax.swing.*;
import javax.swing.table.DefaultTableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import static com.restaurant.util.Constant.*;

public class ReportManagePane2 extends JPanel {
    private JPanel panelTop = null;
    private JLabel labHeader = null;
    private JLabel labBottom=null;
    private JPanel panelBottom = null;
    private JButton add = null;
    private JButton delete = null;
    private JButton save = null;
    private JScrollPane scroll = null;
    private JTable table = null;
    private ReportTableModel model = null;
    private List listReport = null;
    ReportDaoImpl reportDao=new ReportDaoImpl();

    public ReportTableModel getModel() {
        if (null == model) {
            model = new ReportTableModel(listReport);
            // 给model添加一个监听,当修改的时候将触发该事件,代表事件的类是ChangeEvent
            model.addTableModelListener(new ChangeReportEvent(model));
            return model;
        }
        return model;
    }

    public JLabel getLabHeader() {
        if (null == labHeader) {
            labHeader = new JLabel("Maintaince Report Information");
            return labHeader;
        }
        return labHeader;
    }
    public JLabel getSum(){
        if(null==labBottom){
            labBottom=new JLabel("最近预计盈利："+reportDao.getAllMoney());
            return labBottom;
        }
        return labBottom;
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

    public JPanel getPanelBottom() {
        if (null == panelBottom) {
            panelBottom = new JPanel();
            panelBottom.setLayout(new FlowLayout(FlowLayout.CENTER));
            panelBottom.add(getSum());
            //panelBottom.add(getAdd());
            //panelBottom.add(getDelete());
            //panelBottom.add(getSave());
            return panelBottom;
        }
        return panelBottom;
    }

   /* public JButton getDelete() {
        if (null == delete) {
            delete = new JButton(DELETE);
            delete.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    int[] rows = getTable().getSelectedRows();
                    if (rows.length > 0) {
                        int flag = JOptionPane.showConfirmDialog(null, SURE);
                        if (flag == JOptionPane.YES_OPTION)
                            deleteReport();
                    } else
                        JOptionPane.showMessageDialog(null, OPTION);
                }
            });
            return delete;
        }
        return delete;
    }*/

    public void deleteReport() {

        int[] rows = getTable().getSelectedRows();
        ArrayList list = new ArrayList();
        IBaseDAO dao = ReportDaoFactory.getDao();
        for (int i = rows.length - 1; i >= 0; i--) {
            list.add(getModel().getRow(rows[i]));
            getModel().deleteRow(rows[i]);
        }
        dao.deleteList(list);
        getTable().updateUI();
        list.clear();
    }

    public void initData() {
        listReport = new ArrayList();
        IBaseDAO dao = ReportDaoFactory.getDao();
        listReport = dao.getList();
    }
    public ReportManagePane2(){
        initData();
        this.setLayout(new BorderLayout());
        add(getPanelTop(), BorderLayout.NORTH);
        add(getPanelBottom(), BorderLayout.SOUTH);
        add(getPanTable(), BorderLayout.CENTER);
        this.setSize(new Dimension(1024,800));
    }
}
