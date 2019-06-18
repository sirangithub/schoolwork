package com.restaurant.dao;

import java.sql.SQLException;
import java.util.List;

/**
 * @author zhangrong
 * 接口，实现对实体类的增删改查
 */
public interface IBaseDAO {
    public List getList();  //显示表所有信息
    public void saveList(List list);  //增添
    public void  deleteList(List list);  //删除
    public void update(List list);  //修改
}
