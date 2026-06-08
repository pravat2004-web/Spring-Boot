package org.example.dao;

import org.example.entity.Employee;
import java.sql.*;

public class DbUtil {

    private static String url = "jdbc:mysql://localhost:3306/employee_db";
    private static String user = "root";
    private static String password = "Pravat@123";

    private static Connection connection;

    public static void connectToDb() {

        try {
            connection = DriverManager.getConnection(url, user, password);

            System.out.println("Database Connected");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int insert(Employee emp) {

        String sql = "insert into employee values(?,?,?)";

        try {

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, emp.getId());
            ps.setString(2, emp.getName());
            ps.setString(3, emp.getDepartment());

            return ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void displayAll() {

        try {

            Statement st =
                    connection.createStatement();

            ResultSet rs =
                    st.executeQuery("select * from employee");

            while (rs.next()) {

                System.out.println(rs.getInt(1) + " | " + rs.getString(2) + " | " + rs.getString(3));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean deleteById(int id) {

        try {

            PreparedStatement ps = connection.prepareStatement("delete from employee where id=?");

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int update(Employee emp) {

        String sql = "update employee set name=?, department=? where id=?";

        try {

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, emp.getName());
            ps.setString(2, emp.getDepartment());
            ps.setInt(3, emp.getId());

            return ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void getById(int id) {

        try {

            PreparedStatement ps = connection.prepareStatement("select * from employee where id=?");

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                System.out.println(rs.getInt(1) + " | " + rs.getString(2) + " | " + rs.getString(3));

            } else {

                System.out.println("Employee Not Found");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}