package com.example.layeredarchitecture.bo.custom;

import com.example.layeredarchitecture.model.ItemDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemBO {
    public ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException;
    public boolean saveItems(ItemDTO itemDTO) throws SQLException, ClassNotFoundException;
    public boolean updateItems(ItemDTO itemDTO) throws SQLException, ClassNotFoundException;
    public boolean existItems(String code) throws SQLException, ClassNotFoundException;
    public boolean deleteItems(String code) throws SQLException, ClassNotFoundException;
    public String generateNewItemsID() throws SQLException, ClassNotFoundException;
    public ItemDTO searchItems(String code) throws SQLException, ClassNotFoundException;
}
