package jm.task.core.jdbc.util;

import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private Connection connect;

    private final String url = "jdbc:mysql://localhost:3306/test_db";
    private final String user = "root";
    private final String password = "root";

    private Util() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connect = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            throw  new SQLException(e);
        }
    }

    private static  class Holder {
        private static final Util INSTANCE;
        static  {
            try {
                INSTANCE = new Util();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public static  Util getInstance() {
        return Holder.INSTANCE;
    }

    public Connection getConnect() {
        return connect;
    }
}
