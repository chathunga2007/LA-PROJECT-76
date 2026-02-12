package com.example.layeredarchitecture.bo.custom.impl;

import com.example.layeredarchitecture.bo.custom.PlaceOrderBO;
import com.example.layeredarchitecture.dao.custom.CustomerDAO;
import com.example.layeredarchitecture.dao.custom.ItemDAO;
import com.example.layeredarchitecture.dao.custom.OrderDAO;
import com.example.layeredarchitecture.dao.custom.OrderDetailsDAO;
import com.example.layeredarchitecture.dao.custom.impl.CustomerDAOImpl;
import com.example.layeredarchitecture.dao.custom.impl.ItemDAOImpl;
import com.example.layeredarchitecture.dao.custom.impl.OrderDAOImpl;
import com.example.layeredarchitecture.dao.custom.impl.OrderDetailsDAOImpl;
import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.dto.CustomerDTO;
import com.example.layeredarchitecture.dto.ItemDTO;
import com.example.layeredarchitecture.dto.OrderDTO;
import com.example.layeredarchitecture.dto.OrderDetailDTO;
import com.example.layeredarchitecture.entity.Customer;
import com.example.layeredarchitecture.entity.Item;
import com.example.layeredarchitecture.entity.Order;
import com.example.layeredarchitecture.entity.OrderDetails;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PlaceOrderBOImpl implements PlaceOrderBO {

    CustomerDAO customerDAO = new CustomerDAOImpl();
    ItemDAO itemDAO = new ItemDAOImpl();
    OrderDAO orderDAO = new OrderDAOImpl();
    OrderDetailsDAO orderDetailDAO = new OrderDetailsDAOImpl();

    @Override
    public CustomerDTO searchCustomers(String id) throws SQLException, ClassNotFoundException {
        Customer customer= customerDAO.search(id);
        return new CustomerDTO(customer.getId(),customer.getName(),customer.getAddress());
    }

    @Override
    public ItemDTO searchItems(String id) throws SQLException, ClassNotFoundException {
        Item item= itemDAO.search(id);
        return new ItemDTO(item.getCode(),item.getDescription(),item.getUnitPrice(),item.getQtyOnHand());
    }

    @Override
    public String generateNewOrderID() throws SQLException, ClassNotFoundException {
        return orderDAO.generateNewID();
    }

    @Override
    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException {
//        return customerDAO.getAll();
        ArrayList<Customer>customers=customerDAO.getAll();
        ArrayList<CustomerDTO>customerDTOs=new ArrayList<>();
        for(Customer customer:customers){
            CustomerDTO customerDTO=new CustomerDTO(customer.getId(),customer.getName(),customer.getAddress());
            customerDTOs.add(customerDTO);
        }
        return customerDTOs;
    }

    @Override
    public ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException {
//        return itemDAO.getAll();
        ArrayList<Item> items= itemDAO.getAll();
        ArrayList<ItemDTO> itemDTOs= new ArrayList<>();
        for(Item item:items){
            ItemDTO itemDTO= new ItemDTO(item.getCode(),item.getDescription(),item.getUnitPrice(),item.getQtyOnHand());
            itemDTOs.add(itemDTO);
        }
        return itemDTOs;
    }

    @Override
    public boolean existCustomers(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.exist(id);
    }

    @Override
    public boolean existItems(String code) throws SQLException, ClassNotFoundException {
        return itemDAO.exist(code);
    }

    @Override
    public boolean placeOrder(String orderId, LocalDate orderDate, String customerId, List<OrderDetailDTO> orderDetails) throws SQLException, ClassNotFoundException {
        /*Transaction*/
        Connection connection = null;
        connection = DBConnection.getDbConnection().getConnection();
        boolean b1 = orderDAO.exist(orderId);
        /*if order id already exist*/
        if (b1) {
            return false;
        }
        connection.setAutoCommit(false);
//        boolean b2 = orderDAO.save(new OrderDTO(orderId,orderDate,customerId));
        boolean b2 = orderDAO.save(new Order(orderId,orderDate,customerId));
        if (!b2) {
            connection.rollback();
            connection.setAutoCommit(true);
            return false;
        }

        for (OrderDetailDTO detail : orderDetails) {

            boolean b3 = orderDetailDAO.save(new OrderDetails(detail.getOrderId(),detail.getItemCode(),detail.getQty(),detail.getUnitPrice()));

            if (!b3) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }
//                //Search & Update Item
            ItemDTO item = findItem(detail.getItemCode());
            item.setQtyOnHand(item.getQtyOnHand() - detail.getQty());


//            boolean b4 = itemDAO.update(new ItemDTO(item.getCode(),item.getDescription(),item.getUnitPrice(), item.getQtyOnHand()));
            boolean b4 = itemDAO.update(new Item(item.getCode(),item.getDescription(),item.getUnitPrice(), item.getQtyOnHand()));

            if (!b4) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }
        }

        connection.commit();
        connection.setAutoCommit(true);
        return true;
    }

//    public ItemDTO findItem(String code) {
//        try {
//            return itemDAO.search(code);
//        } catch (SQLException e) {
//            throw new RuntimeException("Failed to find the Item " + code, e);
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    public ItemDTO findItem(String code) {
        try {
            Item item= itemDAO.search(code);
            return new ItemDTO(item.getCode(),item.getDescription(),item.getUnitPrice(),item.getQtyOnHand());
        } catch (SQLException e) {
            throw new RuntimeException("Failed to find the Item " + code, e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}