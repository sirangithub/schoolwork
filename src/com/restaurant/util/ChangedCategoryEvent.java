/*package com.restaurant.util;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import java.util.List;
import java.util.ArrayList;

/**
 * @author zhangrong
 * 监听table被改动的事件，主要目的用来记录被修改过的值，这样做可以一次任意行地修改值，修改一个单元格的值就记录一次
 * 主要id、新值、列数
 */

/*public class ChangedCategoryEvent {
    CategoryTableModel model=null;
    public ChangedCategoryEvent(CategoryTableModel model){
        this.model=model;
    }
    List list=model.getChangelist();
    int id=0;
    String value="";
    public void tableChanged(TableModelEvent arg0){
        int row=arg0.getFirstRow();
        int col=arg0.getColumn();
        if(col!=-1){
            Changed cp=new Changed();
            id=(Integer)model.getValueAt(row,0).intValue();
            if(id!=0){
                value=model.getValueAt(row,col).toString();
                cp.setId(id);
                cp.setCol(col);
                cp.setValue(value);
                list.add(cp);
            }
        }
    }
}*/
