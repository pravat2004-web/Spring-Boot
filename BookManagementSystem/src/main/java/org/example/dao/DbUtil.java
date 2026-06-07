package org.example.dao;

import org.example.entity.Book;

import java.sql.*;

public class DbUtil {

    private static String url = "jdbc:mysql://localhost:3306/book_db";
    private static String user = "root";
    private static String password = "Pravat@123";

    private static Connection connection = null;

    public static void connectToDb() {

        try {
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Database Connected");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int insert(Book book) {

        String sql = "insert into book values(?,?,?)";

        try {

            PreparedStatement ps =
                    connection.prepareStatement(sql);

            ps.setInt(1, book.getId());
            ps.setString(2, book.getTitle());
            ps.setString(3, book.getAuthor());

            return ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void displayAll() {

        String sql = "select * from book";

        try {

            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                System.out.println(
                        rs.getInt(1) + " | " +
                                rs.getString(2) + " | " +
                                rs.getString(3));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deleteById(int id) {

        try {

            PreparedStatement ps =
                    connection.prepareStatement(
                            "delete from book where id=?");

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int update(Book book) {

        String sql = "update book set title=?, author=? where id=?";

        try {

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setInt(3, book.getId());

            return ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void getById(int id) {

        try {

            PreparedStatement ps = connection.prepareStatement("select * from book where id=?");

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                System.out.println(rs.getInt(1) + " | " + rs.getString(2) + " | " + rs.getString(3));

            } else {

                System.out.println("Book Not Found");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}