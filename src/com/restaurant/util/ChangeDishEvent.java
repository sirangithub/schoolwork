package com.restaurant.util;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import java.util.List;

public class ChangeDishEvent implements TableModelListener {
    DishTableModel model = null;

    public ChangeDishEvent(DishTableModel model) {
        // TODO Auto-generated constructor stub
        this.model = model;
    }

    List list = model.getChangeList();
    int id = 0;
    String value = "";

    @Override
    public void tableChanged(TableModelEvent arg0) {
        // TODO Auto-generated method stub
        int row = arg0.getFirstRow();
        int col = arg0.getColumn();
        if (col != -1) {
            Changed cp = new Changed();
            id = ((Integer) model.getValueAt(row, 0)).intValue();
            if (id != 0) {
                value = model.getValueAt(row, col).toString();
                cp.setId(id);
                cp.setCol(col);
                cp.setValue(value);
                list.add(cp);
            }
        }
    }
}
