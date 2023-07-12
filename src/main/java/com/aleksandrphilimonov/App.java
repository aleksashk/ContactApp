package com.aleksandrphilimonov;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/postgres_db",
                "postgres",
                "password"
        )) {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(
                    "select " +
                            "c.first_name," +
                            "c.last_name," +
                            "count(pn.phone_number)" +
                            "from contact as c " +
                            "left join phone_number pn on pn.contact_id = c.id " +
                            "group by c.first_name, c.last_name;");
            while (rs.next()) {
                System.out.println(rs.getString("first_name") + "\t" +
                        rs.getString("last_name") + "\t" +
                        rs.getInt("count"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
