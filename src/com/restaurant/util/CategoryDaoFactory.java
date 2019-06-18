package com.restaurant.util;

import com.restaurant.dao.IBaseDAO;
import com.restaurant.dao.Impl.CategoryDaoImpl;

public class CategoryDaoFactory {
    synchronized public static IBaseDAO getDao() {
        IBaseDAO dao = null;
        if (dao == null) {
            dao = new CategoryDaoImpl();
            return dao;
        }
        return dao;
    }
}
