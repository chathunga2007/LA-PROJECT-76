package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.dao.custom.OrderDAO;
import java.sql.*;
import java.time.LocalDate;

public class OrderDAOImpl implements OrderDAO {

    @Override
    public String generateNewOrderId() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT oid FROM `Orders` ORDER BY oid DESC LIMIT 1;");
        return rst.next() ? String.format("OID-%03d", (Integer.parseInt(rst.getString("oid").replace("OID-", "")) + 1)) : "OID-001";
    }

    @Override
    public boolean existsOrder(String orderId) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT oid FROM `Orders` WHERE oid=?",  orderId);
        return rst.next();
    }

    @Override
    public boolean saveOrder(String orderId, LocalDate orderDate, String customerId) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO `Orders` (oid, date, customerID) VALUES (?,?,?)", orderId, orderDate, customerId);
    }
}