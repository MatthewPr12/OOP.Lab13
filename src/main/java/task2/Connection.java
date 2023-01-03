package task2;

import lombok.SneakyThrows;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Connection {
    private static Connection connection;
    private final java.sql.Connection jdbcConnection;
    @SneakyThrows
    private Connection(){
        jdbcConnection = DriverManager.getConnection("jdbc:sqlite:database.sqlite3");
    }

    @SneakyThrows
    public void executeQuery(String query){
        Statement stmt = jdbcConnection.createStatement();
        stmt.executeUpdate(query);
        stmt.close();
    }

    @SneakyThrows
    public String searchQuery(String query){
        PreparedStatement p;
        ResultSet rs;
        p = jdbcConnection.prepareStatement(query);
        rs = p.executeQuery();
        if(rs!=null){
            return rs.getString("data");
        }
        return null;
    }
    @SneakyThrows
    public PreparedStatement prepareStatement(String sql){
        return jdbcConnection.prepareStatement(sql);
    }


    @SneakyThrows
    public static Connection getInstance() {
        if(connection==null){
            connection = new Connection();
        }
        return connection;
    }

}
