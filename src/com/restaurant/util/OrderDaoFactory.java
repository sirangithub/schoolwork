package com.restaurant.util;

import com.restaurant.dao.IBaseDAO;
import com.restaurant.dao.Impl.OrderDaoImpl;

public class OrderDaoFactory {
    synchronized public static IBaseDAO getDao(){
        IBaseDAO dao=null;
        if (dao==null) {
            dao=new OrderDaoImpl();
            return dao;
        }
        return dao;
    }
}
