package com.alevel.moboperator.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public final class HibernateHelper {
    private HibernateHelper() {
    }

    public static SessionFactory createSessionFactory() {
        return new Configuration().configure().buildSessionFactory();
    }

}
