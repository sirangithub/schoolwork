package com.restaurant.util;

import com.restaurant.dao.IBaseDAO;
import com.restaurant.dao.Impl.DeskDaoImpl;

public class DeskDaoFactory {
    synchronized public static IBaseDAO getDao(){
        IBaseDAO dao=null;
        if (dao==null) {
            dao=new DeskDaoImpl();
            return dao;
        }
        return dao;
    }
}
