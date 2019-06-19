package com.restaurant.util;

import com.restaurant.dao.IBaseDAO;
import com.restaurant.dao.Impl.ReportDaoImpl;

public class ReportDaoFactory {
    synchronized public static IBaseDAO getDao() {
        IBaseDAO dao = null;
        if (dao == null) {
            dao = new ReportDaoImpl();
            return dao;
        }
        return dao;
    }
}
