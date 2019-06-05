package com.restaurant.dao;

import java.sql.SQLException;
import java.util.List;

/**
 * @author zhangrong
 * 接口，实现对实体类的增删改查
 */
public interface IBaseDAO {
    public List getList()throws SQLException;  //显示表所有信息
    public void saveList(List list)throws SQLException;  //增添
    public void  deleteList(int id)throws SQLException;  //删除
    public void update(List list)throws SQLException;  //修改
}
