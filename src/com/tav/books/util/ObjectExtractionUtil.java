package com.tav.books.util;

import com.tav.books.model.Book;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ObjectExtractionUtil {
    public static Book resultToBook(ResultSet result) throws SQLException {
        return new Book(
                result.getLong("id"),
                result.getString("title"),
                result.getFloat("price")
        );
    }
}
