package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.dao.custom.OrderDetailsDAO;
import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.OrderDetailDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderDetailsDAOImpl implements OrderDetailsDAO {

    @Override
    public boolean saveOrderDetails(OrderDetailDTO orderDetailDTO) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement stm = connection.prepareStatement("INSERT INTO OrderDetails (oid, itemCode, unitPrice, qty) VALUES (?,?,?,?)");
        stm.setString(1, orderDetailDTO.getOrderId());
        stm.setString(2, orderDetailDTO.getItemCode());
        stm.setBigDecimal(3, orderDetailDTO.getUnitPrice());
        stm.setInt(4, orderDetailDTO.getQty());
        return stm.executeUpdate() > 0;
    }
}
