package InsertsWithJDBC;

import java.sql.*;

public class Main {
    private static final String URL = "jdbc:mysql://localhost:3306/workbench";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "2285";

    private static final String INSERT = "INSERT INTO testtable(id, name, age,adult) "
            + " VALUES(?,?,?,?)";

    public static void main(String[] args) {
        registerDriver();

        Connection connection = null;
        PreparedStatement statement = null;


        try {
            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            statement = connection.prepareStatement(INSERT);
            for (int i = 10; i < 20; i++) {
                statement.setInt(1, i);
                statement.setString(2,"Some name"+i);
                statement.setInt(3,i);
                if (i>=18)
                    statement.setBoolean(4,true);
                statement.setBoolean(4,false);
                statement.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                statement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }


    private static void registerDriver() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loading success!");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
