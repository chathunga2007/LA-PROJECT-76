package com.example.layeredarchitecture.dao.custom.impl;

import com.example.layeredarchitecture.dao.CrudUtil;
import com.example.layeredarchitecture.dao.custom.ItemDAO;
import com.example.layeredarchitecture.entity.Item;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;

public class ItemDAOImpl implements ItemDAO {
    @Override
    public ArrayList<Item> getAll() throws SQLException , ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Item");
        ArrayList<Item> items = new ArrayList<>();
        while (rst.next()) {
            String code = rst.getString("code");
            String description = rst.getString("description");
            int quantity = rst.getInt("qtyOnHand");
            BigDecimal qtyOnHand = rst.getBigDecimal("unitPrice");

            Item entity = new Item(code, description, qtyOnHand, quantity);
            items.add(entity);

        }
        return items;
    }
    @Override
    public boolean save(Item entity) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO Item (code, description, unitPrice, qtyOnHand) VALUES (?,?,?,?)",entity.getCode(), entity.getDescription(), entity.getUnitPrice(), entity.getQtyOnHand());
    }
    @Override
    public boolean update(Item entity) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE Item SET description=?, unitPrice=?, qtyOnHand=? WHERE code=?",entity.getDescription(), entity.getUnitPrice(), entity.getQtyOnHand(), entity.getCode());
    }
    @Override
    public boolean exist(String code) throws SQLException, ClassNotFoundException {
        ResultSet rst= CrudUtil.execute("SELECT * FROM Item WHERE code=?",code);
        return rst.next();
    }
    @Override
    public boolean delete(String code) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM Item WHERE code=?",code);
    }
    @Override
    public Item search(String code) throws SQLException, ClassNotFoundException {
        ResultSet rst= CrudUtil.execute("SELECT * FROM Item WHERE code=?",code);
        if (rst.next()) {
            return new Item(rst.getString("code"),rst.getString("description"),rst.getBigDecimal("unitPrice"),rst.getInt("qtyOnHand"));
        }
        return null;
    }
    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT code FROM Item ORDER BY code DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("code");
            int newItemId = Integer.parseInt(id.replace("I00-", "")) + 1;
            return String.format("I00-%03d", newItemId);
        } else {
            return "I00-001";
        }
    }
}