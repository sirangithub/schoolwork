package com.restaurant.util;

import com.restaurant.entity.Customer;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CustomerTableModel extends AbstractTableModel {
    private static List changeList= new ArrayList();
    private List list=new ArrayList();
    private String[] column={"编号","用户名","性别","单位","电话","贵宾卡号"};
    public static List getChangeList() {
        return changeList;
    }
    public static void setChangeList(List changeList) {
        CustomerTableModel.changeList = changeList;
    }
    public List getList() {
        return list;
    }
    public void setList(List list) {
        this.list = list;
    }
    public CustomerTableModel() {
        // TODO Auto-generated constructor stub
    }
    public CustomerTableModel(List list) {
        // TODO Auto-generated constructor stub
        this();
        setList(list);
    }
    public int getRowCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

    @Override
    public int getColumnCount() {
        // TODO Auto-generated method stub
        return column.length;////////////////////列数
    }
    public String getColumnName(int i) {
        return column[i];
    }
    public void setColumn(String[] column) {
        this.column=column;
    }
    public boolean isCellEditable(int row,int column) {
        return true;
    }
    public void addRow(int index, Customer customer) {
        if (index<0||index>list.size()-1) {
            list.add(customer);
            fireTableRowsInserted(list.size(), list.size());
        } else {
            list.add(index+1,customer);
            fireTableRowsInserted(index, index);
        }
    }
    public boolean deleteRow(int index) {
        if (index>=0&&index<list.size()) {
            list.remove(index);
            fireTableRowsDeleted(index, index);
            return true;
        } else {
            return false;
        }
    }
    public boolean saveRow(int index,Customer customer) {
        if (index>=0&&index<list.size()) {
            list.set(index, customer);
            fireTableCellUpdated(index, index);
            return true;
        } else {
            return false;
        }
    }
    public Customer getRow(int index) {
        if (index>=0&&index<list.size()) {
            return (Customer) list.get(index);
        } else {
            return null;
        }
    }
    public List getNewRow() {
        List list=new ArrayList();//////////////////////////////////////////////////////////////////////////////////
        List listProduct = getList();///////////////////////////////////////////////////////////////////////////////////
        Iterator it=listProduct.iterator();
        while (it.hasNext()) {
            Customer customer=new Customer();
            customer=(Customer) it.next();
            if (customer.getId()==0) {
                list.add(customer);
            }
        }
        return list;
    }
    public Object getPropertyValueByCol(Customer customer,int col) {
        switch (col) {
            case 0:
                return customer.getId();
            case 1:
                return customer.getName();
            case 2:
                return customer.getSex();
            case 3:
                return customer.getCompany();
            case 4:
                return customer.getTel();
            case 5:
                return customer.getCardID();
        }
        return null;
    }
    public void setPropertyValueByCol(Customer customer,String value,int col) {
        switch (col) {
            case 1:
                customer.setName(value);
                break;
            case 2:
                customer.setSex(value);
                break;
            case 3:
                customer.setCompany(value);
                break;
            case 4:
                customer.setTel(value);
                break;
            case 5:
                customer.setCardID(value);
                break;
        }
        fireTableDataChanged();
    }
    public void setValueAt(Object avalue,int rowIndex,int columnIndex) {
        Customer customer= (Customer) list.get(rowIndex);
        setPropertyValueByCol(customer, avalue.toString(), columnIndex);
        this.fireTableCellUpdated(rowIndex, columnIndex);
    }
    public Object getValueAt(int arg0, int arg1) {
        // TODO Auto-generated method stub
        Customer customer=(Customer) list.get(arg0);
        return getPropertyValueByCol(customer, arg1);
    }
}
