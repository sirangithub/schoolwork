package com.restaurant.util;

import com.restaurant.entity.Report;

import javax.swing.table.AbstractTableModel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ReportTableModel extends AbstractTableModel {
    private static List changelist = new ArrayList();
    private List list = new ArrayList();
    private String[] column = {"序号","桌号", "菜名", "价格", "数量"};

    public ReportTableModel() {

    }

    public ReportTableModel(List list) {
        this();
        setList(list);
    }

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return column.length;
    }

    @Override
    public Object getValueAt(int arg0, int arg1) {
        Report report = (Report) list.get(arg0);
        return getPropertyValueByCol(report, arg1);
    }

    public Object getPropertyValueByCol(Report report, int col) {
        switch (col) {
            case 0:
                return report.getId();
            case 1:
                return report.getDeskno();
            case 2:
                return report.getDishname();
            case 3:
                return report.getPrice();
            case 4:
                return report.getAmount();
        }
        return null;
    }

    public void setValueAt(Object avalue, int rowIndex, int columnIndex) {
        Report report = (Report) list.get(rowIndex);
        setPropertyValueByCol(report, avalue.toString(), columnIndex);
        this.fireTableCellUpdated(rowIndex, columnIndex);

    }

   public void setPropertyValueByCol(Report report, String value, int col) {
        switch (col) {
            case 1:
                report.setDeskno(value);
                break;
            case 2:
                report.setDishname(value);
                break;
            case 3:
                report.setPrice(Double.parseDouble(value));
                break;
            case 4:
                report.setAmount(Integer.parseInt(value));
                break;
        }
        fireTableDataChanged();
    }

    public void addRow(int index, Report report) {
        if (index < 0 || index > list.size() - 1) {
            list.add(report);
            fireTableRowsInserted(list.size(), list.size());
        } else {
            list.add(index + 1, report);
            fireTableRowsInserted(index, index);
        }
    }

    public boolean deleteRow(int index) {
        if (index >= 0 && index < list.size()) {
            list.remove(index);
            fireTableRowsDeleted(index, index);
            return true;
        } else {
            return false;
        }
    }

    public boolean saveRow(int index, Report report) {
        if (index >= 0 && index < list.size()) {
            list.set(index, report);
            fireTableCellUpdated(index, index);
            return true;
        } else {
            return false;
        }
    }

    public Report getRow(int index) {
        if (index >= 0 && index < list.size()) {
            return (Report) list.get(index);
        } else {
            return null;
        }
    }

    public List getNewRow() {/////////////////////
        List list = new ArrayList();///////////////////////////////////////////
        List listProduct = getList();///////////////////////////////////////////
        Iterator it = listProduct.iterator();
        while (it.hasNext()) {
            Report report = new Report();
            report = (Report) it.next();
            if (report.getId() == 0) {
                list.add(report);
            }
        }
        return list;
    }

    public boolean isCellEditable(int row, int column) {
        return true;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    @Override
    public String getColumnName(int i) {
        return column[i];
    }

    public void setColumn(String[] column) {
        this.column = column;
    }

    public static List getChangeList() {
        return changelist;
    }

    public static void setChangeList(List changeList) {
        ReportTableModel.changelist = changeList;
    }
}
