package jm.task.core.jdbc.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;



public class Util {

    private static final String URL = "jdbc:mysql://localhost:3306/test_db";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private static final SessionFactory SESSION_FACTORY;

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    static {
        try{
            Configuration configuration = new Configuration();
            configuration.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
            configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/test_db");
            configuration.setProperty("hibernate.connection.username", "root");
            configuration.setProperty("hibernate.connection.password", "root");
            StandardServiceRegistryBuilder ssBuild = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties());
                    SESSION_FACTORY = configuration.buildSessionFactory(ssBuild.build());

        } catch (Exception ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() {
        return SESSION_FACTORY.openSession();
    }

}
