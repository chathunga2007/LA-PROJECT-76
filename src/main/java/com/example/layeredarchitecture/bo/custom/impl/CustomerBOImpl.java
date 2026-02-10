package com.example.layeredarchitecture.bo.custom.impl;

import com.example.layeredarchitecture.bo.custom.CustomerBO;
import com.example.layeredarchitecture.dao.DAOFactory;
import com.example.layeredarchitecture.dao.custom.CustomerDAO;
import com.example.layeredarchitecture.dto.CustomerDTO;
import com.example.layeredarchitecture.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO {

    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.CUSTOMER);

    @Override
    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException {
        ArrayList<Customer>customers=customerDAO.getAll();
        ArrayList<CustomerDTO>customerDTOs=new ArrayList<>();
        for(Customer customer:customers){
            CustomerDTO customerDTO=new CustomerDTO(customer.getId(),customer.getName(),customer.getAddress());
            customerDTOs.add(customerDTO);
        }
        return customerDTOs;
    }

    @Override
    public boolean saveCustomers(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        return customerDAO.save(new Customer(customerDTO.getId(), customerDTO.getName(), customerDTO.getAddress()));
    }

    @Override
    public boolean updateCustomers(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        return customerDAO.update(new Customer(customerDTO.getId(), customerDTO.getName(), customerDTO.getAddress()));
    }

    @Override
    public boolean existCustomers(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.exist(id);
    }

    @Override
    public boolean deleteCustomers(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.delete(id);
    }

    @Override
    public String generateNewCustomersID() throws SQLException, ClassNotFoundException {
        return customerDAO.generateNewID();
    }

    @Override
    public CustomerDTO searchCustomers(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
