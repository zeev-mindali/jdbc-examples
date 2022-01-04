package com.tav.books.dal;

import com.tav.books.model.Book;
import com.tav.books.util.JDBCUtil;
import com.tav.books.util.ObjectExtractionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAL implements CrudDAL<Long, Book> {
    public static final BookDAL instance = new BookDAL();

    private BookDAL() {
        try {
            connection = JDBCUtil.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to establish connection with database");
        }
    }

    private final Connection connection;

    public Long create(final Book book) {
        try {
            String sqlStatement = "INSERT INTO book (title, price) VALUES(?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setFloat(2, book.getPrice());
            preparedStatement.executeUpdate();
            ResultSet generatedKeysResult = preparedStatement.getGeneratedKeys();

            if (!generatedKeysResult.next()) {
                throw new RuntimeException("Failed to retrieve auto-incremented id");
            }

            return generatedKeysResult.getLong(1);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to create a new book");
        }
    }

    public Book read(final Long id) {
        try {
            String sqlStatement = "SELECT * FROM book WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, id);
            ResultSet result = preparedStatement.executeQuery();

            if (!result.next()) { return null; }

            return ObjectExtractionUtil.resultToBook(result);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to create a new book");
        }
    }


    public void update(final Book book) {
        try {
            String sqlStatement = "UPDATE book SET title = ?, price = ? WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setFloat(2, book.getPrice());
            preparedStatement.setLong(3, book.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to create a new book");
        }
    }

    public void delete(final Long id) {
        try {
            String sqlStatement = "DELETE * FROM book WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to create a new book");
        }
    }

    @Override
    public List<Book> readAll() {
        try {
            String sqlStatement = "SELECT * FROM book";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement, Statement.RETURN_GENERATED_KEYS);
            ResultSet result = preparedStatement.executeQuery();

            List<Book> books = new ArrayList<>();

            while (result.next()) {
                books.add(ObjectExtractionUtil.resultToBook(result));
            }

            return books;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to create a new book");
        }
    }

}
