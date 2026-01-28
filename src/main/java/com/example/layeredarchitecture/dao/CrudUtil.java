package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.db.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CrudUtil {
    public static <T> T execute(String sql, Object... obj) throws SQLException, ClassNotFoundException {
        Connection conn = DBConnection.getDbConnection().getConnection();
        PreparedStatement ptsm = conn.prepareStatement(sql);

        for (int i = 0; i < obj.length; i++) {
            ptsm.setObject(i + 1, obj[i]);
        }

        if (sql.startsWith("select") || sql.startsWith("SELECT")) {
            return (T) ptsm.executeQuery();
        } else {
            return (T) (Boolean) (ptsm.executeUpdate() > 0);
        }
    }
}
