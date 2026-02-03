package com.example.layeredarchitecture.bo.custom;

import com.example.layeredarchitecture.model.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBO {
    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException;
    public boolean saveCustomers(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;
    public boolean updateCustomers(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;
    public boolean existCustomers(String id) throws SQLException, ClassNotFoundException;
    public boolean deleteCustomers(String id) throws SQLException, ClassNotFoundException;
    public String generateNewCustomersID() throws SQLException, ClassNotFoundException;
    public CustomerDTO searchCustomers(String id) throws SQLException, ClassNotFoundException;
}
