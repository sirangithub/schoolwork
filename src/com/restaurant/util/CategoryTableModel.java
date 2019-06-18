package com.restaurant.util;

import com.restaurant.entity.Category;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CategoryTableModel extends AbstractTableModel {
    private static List changeList = new ArrayList();
    private List list = new ArrayList();
    private String[] column = {"编号", "名称", "描述"};

    public CategoryTableModel() {
        // TODO Auto-generated constructor stub
    }

    public CategoryTableModel(List list) {
        // TODO Auto-generated constructor stub
        this();
        setList(list);
    }

    @Override
    public int getRowCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

    @Override
    public int getColumnCount() {
        // TODO Auto-generated method stub
        return column.length;////////////////////列数
    }

    @Override
    public Object getValueAt(int arg0, int arg1) {
        // TODO Auto-generated method stub
        Category cate = (Category) list.get(arg0);
        return getPropertyValueByCol(cate, arg1);
    }

    public void addRow(int index, Category cate) {
        if (index < 0 || index > list.size() - 1) {
            list.add(cate);
            fireTableRowsInserted(list.size(), list.size());
        } else {
            list.add(index + 1, cate);
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

    public boolean saveRow(int index, Category cate) {
        if (index >= 0 && index < list.size()) {
            list.set(index, cate);
            fireTableCellUpdated(index, index);
            return true;
        } else {
            return false;
        }
    }

    public Category getRow(int index) {
        if (index >= 0 && index < list.size()) {
            return (Category) list.get(index);
        } else {
            return null;
        }
    }

    public List getNewRow() {
        List list = new ArrayList();
        List listProduct = getList();
        Iterator it = listProduct.iterator();
        while (it.hasNext()) {
            Category cate = new Category();
            cate = (Category) it.next();
            if (cate.getId() == 0) {
                list.add(cate);
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

    public Object getPropertyValueByCol(Category cate, int col) {
        switch (col) {
            case 0:
                return cate.getId();
            case 1:
                return cate.getName();
            case 2:
                return cate.getDescrib();
        }
        return null;
    }

    public void setPropertyValueByCol(Category cate, String value, int col) {
        switch (col) {
            case 1:
                cate.setName(value);
                break;
            case 2:
                cate.setDescrib(value);
                break;
        }
        fireTableDataChanged();
    }

    public boolean isCellEditable(int row, int column) {
        return true;
    }

    /**
     *
     */
    @Override
    public void setValueAt(Object avalue, int rowIndex, int columnIndex) {
        Category cate = (Category) list.get(rowIndex);
        setPropertyValueByCol(cate, avalue.toString(), columnIndex);
        this.fireTableCellUpdated(rowIndex, columnIndex);
    }

    public static List getChangeList() {
        return changeList;
    }

    public static void setChangeList(List changeList) {
        CategoryTableModel.changeList = changeList;
    }

}