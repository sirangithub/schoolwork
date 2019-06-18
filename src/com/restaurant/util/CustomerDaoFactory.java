package com.restaurant.util;

import com.restaurant.dao.IBaseDAO;
import com.restaurant.dao.Impl.CustomerDaoImpl;

public class CustomerDaoFactory {
    synchronized public static IBaseDAO getDao(){
        IBaseDAO dao=null;
        if (dao==null) {
            dao=new CustomerDaoImpl();
            return dao;
        }
        return dao;
    }
}
