package com.restaurant.util;

import com.restaurant.entity.Order;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class OrderTableModel extends AbstractTableModel{
        private static List changeList=new ArrayList();
        private List  list = new ArrayList();
        private String[] column={"编号","订单编号","餐台号","就餐日期时间","金额","客户编号","状态","就餐人数"};
        public OrderTableModel() {
            // TODO Auto-generated constructor stub
        }
        public OrderTableModel(List list) {
            // TODO Auto-generated constructor stub
            this();
            setList(list);
        }
        public static List getChangeList() {
            return changeList;
        }
        public static void setChangeList(List changeList) {
            OrderTableModel.changeList = changeList;
        }
        public List getList() {
            return list;
        }
        public void setList(List list) {
            this.list = list;
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
        public void addRow(int index, Order order) {
            if (index<0||index>list.size()-1) {
                list.add(order);
                fireTableRowsInserted(list.size(), list.size());
            } else {
                list.add(index+1,order);
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
        public boolean saveRow(int index,Order order) {
            if (index>=0&&index<list.size()) {
                list.set(index, order);
                fireTableCellUpdated(index, index);
                return true;
            } else {
                return false;
            }
        }
        public Order getRow(int index) {
            if (index>=0&&index<list.size()) {
                return (Order) list.get(index);
            } else {
                return null;
            }
        }
        public List getNewRow() {/////////////////////
            List list=new ArrayList();///////////////////////////////////////////
            List listProduct = getList();///////////////////////////////////////////
            Iterator it=listProduct.iterator();
            while (it.hasNext()) {
                Order order=new Order();
                order=(Order) it.next();
                if (order.getId()==0) {
                    list.add(order);
                }
            }
            return list;
        }
        public Object getPropertyValueByCol(Order order,int col) {
            switch (col) {
                case 0:
                    return order.getId();
                case 1:
                    return order.getOrderNo();
                case 2:
                    return order.getDeskId();
                case 3:
                    return order.getCreatetime();
                case 4:
                    return order.getMoney();
                case 5:
                    return order.getCustomerId();
                case 6:
                    return order.getStatus();
                case 7:
                    return order.getNumber();
            }
            return null;
        }
        public void setPropertyValueByCol(Order order,String value,int col) {
            switch (col) {
                case 1:
                    order.setOrderNo(value);
                    break;
                case 2:
                    order.setDeskId(Integer.parseInt(value));;
                    break;
                case 3:
                    order.setCreatetime(value);;
                    break;
                case 4:
                    order.setMoney(Double.parseDouble(value));;
                    break;
                case 5:
                    order.setCustomerId(Integer.parseInt(value));;
                    break;
                case 6:
                    order.setStatus(value);
                    break;
                case 7:
                    order.setNumber(Integer.parseInt(value));;
                    break;
            }
            fireTableDataChanged();
        }
        public boolean isCellEditable(int row,int column) {
            return true;
        }
        public void setValueAt(Object avalue,int rowIndex,int columnIndex) {
            Order order= (Order) list.get(rowIndex);
            setPropertyValueByCol(order, avalue.toString(), columnIndex);
            this.fireTableCellUpdated(rowIndex, columnIndex);
        }
        @Override
        public Object getValueAt(int arg0, int arg1) {
            Order order=(Order) list.get(arg0);
            return getPropertyValueByCol(order, arg1);
        }
}
