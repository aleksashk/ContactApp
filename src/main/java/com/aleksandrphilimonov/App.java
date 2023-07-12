package com.aleksandrphilimonov;

import java.lang.ref.PhantomReference;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Long contactId = 1L;
        try (Connection connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/postgres_db",
                "postgres",
                "password"
        )) {
            PreparedStatement statement = connection.prepareStatement("select " +
                    "c.id," +
                    "c.first_name," +
                    "c.last_name," +
                    "count(pn.phone_number)" +
                    "from contact as c " +
                    "left join phone_number pn on pn.contact_id = c.id " +
                    "where c.id=?" +
                    "group by c.id, c.first_name, c.last_name;");
            statement.setLong(1, contactId);
            ResultSet rs = statement.executeQuery();
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
