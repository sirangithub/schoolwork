package com.restaurant.util;

import com.restaurant.dao.Impl.CategoryDaoImpl;
import com.restaurant.entity.Category;
import com.restaurant.entity.Dish;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DishTableModel extends AbstractTableModel {
    /**
     *
     * changeList用来存放被修改过的数据值,这样做是为了一次修改多行多值, 保存的对象是ChangedProduct,只记录被修改过的值.
     */
    private static List changeList = new ArrayList();
    private List list = new ArrayList();
    private String[] column = { "编号", "类别", "名称","图片","代码", "单位","单价","状态"};
    public DishTableModel() {
    }
    public DishTableModel(List list) {
        this();
        setList(list);
    }
    public int getColumnCount() {
        return column.length;
    }
    public int getRowCount() {
        return list.size();
    }
    /**
     * getValueAt方法就是使得数据在Table显示出来,给每个单元格设值
     */
    public Object getValueAt(int arg0, int arg1) {
        Dish dish = (Dish) list.get(arg0);
        return getPropertyValueByCol(dish, arg1);
    }
    public void addRow(int index, Dish dish) {
        if (index < 0 || index > list.size() - 1) {
            list.add(dish);
            fireTableRowsInserted(list.size(), list.size());
        } else {
            list.add(index + 1, dish);
            fireTableRowsInserted(index, index);
        }
    }
    public boolean deleteRow(int index) {
        if (index >= 0 && index < list.size()) {
            list.remove(index);
            fireTableRowsDeleted(index, index);
            return true;
        } else
            return false;
    }
    public boolean saveRow(int index,  Dish dish) {
        if (index >= 0 && index < list.size()) {
            list.set(index, dish);
            fireTableRowsUpdated(index, index);
            return true;
        } else
            return false;
    }
    public Dish getRow(int index) {
        if (index >= 0 && index < list.size()) {
            return (Dish) list.get(index);
        } else
            return null;
    }
    public List getNewRow() {
        List list = new ArrayList();
        List listDish = getList();
        Iterator it = listDish.iterator();
        while (it.hasNext()) {
            Dish dish = new Dish();
            dish = (Dish) it.next();
            if (dish.getId() == 0) {
                list.add(dish);
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
    public Object getPropertyValueByCol(Dish dish, int col) {
        switch (col) {
            case 0:
                return dish.getId();
            case 1:
                return dish.getCategory().getId();
            case 2:
                return dish.getName();
            case 3:
                return dish.getPic();
            case 4:
                return dish.getCode();
            case 5:
                return dish.getUnit();
            case 6:
                return dish.getPrice();
            case 7:
                return dish.getStatus();
        }
        return null;
    }
    public void setPropertyValueByCol(Dish dish, String value, int col) {
        switch (col) {
            case 1:
                CategoryDaoImpl cdi=new CategoryDaoImpl();
                Category category=new Category();
                category=cdi.getCategoryById(Integer.parseInt(value));
                dish.setCategory(category);;
                break;
            case 2:
                dish.setName(value);
                break;
            case 3:
                dish.setPic(value);
                break;
            case 4:
                dish.setCode(value);
                break;
            case 5:
                dish.setUnit(value);
                break;
            case 6:
                dish.setPrice(Integer.parseInt(value));
                break;
            case 7:
                dish.setStatus(value);
        }
        fireTableDataChanged();
    }
    public boolean isCellEditable(int row, int column) {
        return true;
    }
    /**
     * setValueAt方法是使增加或修改值的时候生效,aValue就是你在单元格填的值, 要把这些值保存到数据源中
     */
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Dish dish = (Dish) list.get(rowIndex);
        setPropertyValueByCol(dish, aValue.toString(), columnIndex);
        this.fireTableCellUpdated(rowIndex, columnIndex);
    }
    public static List getChangeList() {
        return changeList;
    }
    public static void setChangeList(List changeList) {
        DishTableModel.changeList = changeList;
    }
}
