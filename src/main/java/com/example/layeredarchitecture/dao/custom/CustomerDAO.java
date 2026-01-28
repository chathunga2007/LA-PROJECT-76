package com.example.layeredarchitecture.dao.custom;

import com.example.layeredarchitecture.model.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerDAO {
    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException;

    public void saveCustomers(String id, String name, String address) throws SQLException, ClassNotFoundException;

    public void updateCustomers(String id, String name, String address) throws SQLException, ClassNotFoundException;

    public boolean exitCustomers(String id) throws SQLException, ClassNotFoundException;

    public void deleteCustomers(String id) throws SQLException, ClassNotFoundException;

    public String generateNewID() throws SQLException, ClassNotFoundException;
}
