package com.restaurant.util;

import com.restaurant.dao.IBaseDAO;
import com.restaurant.dao.Impl.EmployeeDaoImpl;

public class EmployeeDaoFactory {
    synchronized public static IBaseDAO getDao(){
        IBaseDAO dao=null;
        if (dao==null) {
            dao=new EmployeeDaoImpl();
            return dao;
        }
        return dao;
    }
}