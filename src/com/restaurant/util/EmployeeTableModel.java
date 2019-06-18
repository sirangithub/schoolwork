package com.restaurant.util;

import com.restaurant.entity.Employee;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EmployeeTableModel extends AbstractTableModel {
    private static List changeList = new ArrayList();
    private List list = new ArrayList();
    private String[] column = {"编号", "用户名", "性别", "出生日期", "身份证号", "家庭住址", "电话", "职位", "是否在职"};

    public static List getChangeList() {
        return changeList;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
        fireTableDataChanged();
    }

    public EmployeeTableModel() {

    }

    public EmployeeTableModel(List list) {
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
        return column.length;
    }

    @Override
    public Object getValueAt(int arg0, int arg1) {
        Employee employee = (Employee) list.get(arg0);
        return getPropertyValueByCol(employee, arg1);
    }

    public void addRow(int index, Employee employee) {
        if (index < 0 || index > list.size() - 1) {
            list.add(employee);
            fireTableRowsInserted(list.size(), list.size());
        } else {
            list.add(index + 1, employee);
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

    public boolean saveRow(int index, Employee employee) {
        if (index >= 0 && index < list.size()) {
            list.set(index, employee);
            fireTableCellUpdated(index, index);
            return true;
        } else {
            return false;
        }
    }

    public Employee getRow(int index) {
        if (index >= 0 && index < list.size()) {
            return (Employee) list.get(index);
        } else {
            return null;
        }
    }

    public List getNewRow() {
        List list = new ArrayList();
        List listProduct = getList();
        Iterator it = listProduct.iterator();
        while (it.hasNext()) {
            Employee employee = new Employee();
            employee = (Employee) it.next();
            if (employee.getId() == 0) {
                list.add(employee);
            }
        }
        return list;
    }

    public Object getPropertyValueByCol(Employee employee, int col) {
        switch (col) {
            case 0:
                return employee.getId();
            case 1:
                return employee.getName();
            case 2:
                return employee.getSex();
            case 3:
                return employee.getBirthday();
            case 4:
                return employee.getIdentityID();
            case 5:
                return employee.getAddress();
            case 6:
                return employee.getTel();
            case 7:
                return employee.getPosition();
            case 8:
                return employee.getFreeze();
        }
        return null;
    }

    public void setPropertyValueByCol(Employee employee, String value, int col) {
        switch (col) {
            case 1:
                employee.setName(value);
                break;
            case 2:
                employee.setSex(value);
                break;
            case 3:
                employee.setBirthday(value);
                break;
            case 4:
                employee.setIdentityID(value);
                break;
            case 5:
                employee.setAddress(value);
                break;
            case 6:
                employee.setTel(value);
                break;
            case 7:
                employee.setPosition(value);
                break;
            case 8:
                employee.setFreeze(value);
                break;
        }
        fireTableDataChanged();
    }

    @Override
    public String getColumnName(int i) {
        return column[i] ;
    }

    public boolean isCellEditable(int row, int column) {
        return true;
    }

    public void setValueAt(Object avalue, int rowIndex, int columnIndex) {
        Employee employee = (Employee) list.get(rowIndex);
        setPropertyValueByCol(employee, avalue.toString(), columnIndex);
        this.fireTableCellUpdated(rowIndex, columnIndex);
    }
}
