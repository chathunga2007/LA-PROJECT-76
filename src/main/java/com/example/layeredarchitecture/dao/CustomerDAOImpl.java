package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.dao.custom.CustomerDAO;
import com.example.layeredarchitecture.model.CustomerDTO;
import java.sql.*;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Customer");
        ArrayList<CustomerDTO> customers = new ArrayList<>();
        while (rst.next()) {
            String id = rst.getString("id");
            String name = rst.getString("name");
            String address = rst.getString("address");
            CustomerDTO customerDTO = new CustomerDTO(id, name, address);
            customers.add(customerDTO);
        }
        return customers;
    }

    @Override
    public void saveCustomers(String id, String name, String address) throws SQLException, ClassNotFoundException {
        CrudUtil.execute("INSERT INTO Customer (id,name, address) VALUES (?,?,?)", id, name, address);
    }

    @Override
    public void updateCustomers(String id, String name, String address) throws SQLException, ClassNotFoundException {
        CrudUtil.execute("UPDATE Customer SET name=?,address=? WHERE id=?",  name, address, id);
    }

    @Override
    public boolean exitCustomers(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst =  CrudUtil.execute("SELECT id FROM Customer WHERE id=?", id);
        return rst.next();
    }

    @Override
    public void deleteCustomers(String id) throws SQLException, ClassNotFoundException {
        CrudUtil.execute("DELETE FROM Customer WHERE id=?", id);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT id FROM Customer ORDER BY id DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("id");
            int newCustomerId = Integer.parseInt(id.replace("C00-", "")) + 1;
            return String.format("C00-%03d", newCustomerId);
        } else {
            return "C00-001";
        }
    }
}