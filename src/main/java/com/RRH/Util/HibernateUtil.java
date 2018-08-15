package com.RRH.Util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        // Singleton instance here.
        if(sessionFactory == null)
            sessionFactory = getNewSessionFactory();
        return sessionFactory;
    }

    public static SessionFactory getNewSessionFactory() {
        SessionFactory sessionFactory = null;
        try{
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch(Exception e) {
            System.err.println("*** HIBERNATE INITIALIZATION EXCEPTION!! ***");
            e.printStackTrace();
        }
        return sessionFactory;
    }
}
