package com.example.layeredarchitecture.bo.custom;

import com.example.layeredarchitecture.dto.CustomerDTO;
import com.example.layeredarchitecture.dto.ItemDTO;
import com.example.layeredarchitecture.dto.OrderDetailDTO;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface PlaceOrderBO extends SuperBO {
    public String generateNewOrderID() throws SQLException, ClassNotFoundException;

    public CustomerDTO searchCustomers(String id) throws SQLException, ClassNotFoundException;

    public ItemDTO searchItems(String code) throws SQLException, ClassNotFoundException;

    public boolean existCustomers(String id) throws SQLException, ClassNotFoundException;

    public boolean existItems(String code) throws SQLException, ClassNotFoundException;

    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException;

    public ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException;

    public ItemDTO findItem(String code);

    public boolean placeOrder(String orderId, LocalDate orderDate, String customerId, List<OrderDetailDTO> orderDetails) throws SQLException, ClassNotFoundException;
}
