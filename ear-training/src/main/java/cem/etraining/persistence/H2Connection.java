package cem.etraining.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class H2Connection {

    public static Connection load() throws SQLException {
        return DriverManager.getConnection("jdbc:h2:~/database/ear-training", "sa", "");
    }
}
