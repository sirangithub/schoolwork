package com.restaurant.util;

import com.restaurant.dao.IBaseDAO;
import com.restaurant.dao.Impl.DishDaoImpl;

public class DishDaoFactory {
    synchronized public static IBaseDAO getDao() {
        IBaseDAO dao = null;
        if (dao == null) {
            dao = new DishDaoImpl();
            return dao;
        }
        return dao;
    }
}
