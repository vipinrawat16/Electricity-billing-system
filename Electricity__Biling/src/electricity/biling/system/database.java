package electricity.biling.system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class database {
    Connection connection;
    Statement statement;

    public database() {
        try {
            // Load the JDBC driver (correct the driver class name)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the connection
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/Bill_System", "root", "Vipin@123");

            // Create a statement
            statement = connection.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    // Getter for the Statement
    public Statement getStatement() {
        return statement;
    }

    // Method to close resources (call this when done with the database)
    public void close() {
        try {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
