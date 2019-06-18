package com.restaurant.util;


import com.restaurant.entity.Desk;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DeskTableModel extends AbstractTableModel {
    private static List changeList = new ArrayList();
    private List list = new ArrayList();
    private String[] column = {"编号", "餐台编号", "座位数", "状态"};

    public DeskTableModel() {
        // TODO Auto-generated constructor stub
    }

    public DeskTableModel(List list) {
        this();
        setList(list);
    }

    public int getColumnCount() {
        return column.length;
    }

    public int getRowCount() {
        return list.size();
    }

    public Object getValueAt(int arg0, int arg1) {
        Desk desk = (Desk) list.get(arg0);
        return getPropertyValueByCol(desk, arg1);
    }

    public void addRow(int index, Desk desk) {
        if (index < 0 || index > list.size() - 1) {
            list.add(desk);
            fireTableRowsInserted(list.size(), list.size());
        } else {
            list.add(index + 1, desk);
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

    public boolean saveRow(int index, Desk desk) {
        if (index >= 0 && index < list.size()) {
            list.set(index, desk);
            fireTableRowsUpdated(index, index);
            return true;
        } else {
            return false;
        }
    }

    public Desk getRow(int index) {
        if (index >= 0 && index < list.size()) {
            return (Desk) list.get(index);
        } else {
            return null;
        }
    }

    public List getNewRow() {
        List list = new ArrayList();
        List listProduct = getList();
        Iterator it = listProduct.iterator();
        while (it.hasNext()) {
            Desk desk = new Desk();
            desk = (Desk) it.next();
            if (0 == desk.getId()) {
                list.add(desk);
            }
        }
        return list;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
        fireTableDataChanged();
    }

    public String getColumnName(int i) {
        return column[i];
    }

    public void setColumn(String[] column) {
        this.column = column;
    }

    public Object getPropertyValueByCol(Desk desk, int col) {
        switch (col) {
            case 0:
                return desk.getId();
            case 1:
                return desk.getNo();
            case 2:
                return desk.getSeating();
            case 3:
                return desk.getStatus();
        }
        return null;
    }

    public void setPropertyValueByCol(Desk desk, String value, int col) {
        switch (col) {
            case 1:
                desk.setNo(value);
                break;
            case 2:
                desk.setSeating(Integer.parseInt(value));
                break;
            case 3:
                desk.setStatus(value);
                break;
        }
        fireTableDataChanged();
    }

    public boolean isCellEditable(int row, int column) {
        return true;
    }

    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Desk desk = (Desk) list.get(rowIndex);
        setPropertyValueByCol(desk, aValue.toString(), columnIndex);
        this.fireTableCellUpdated(rowIndex, columnIndex);
    }

    public static List getChangeList() {
        return changeList;
    }

    public static void setChangeList(List changeList) {
        DeskTableModel.changeList = changeList;
    }


}
