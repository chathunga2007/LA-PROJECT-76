package com.example.layeredarchitecture.bo.custom.impl;

import com.example.layeredarchitecture.bo.custom.ItemBO;
import com.example.layeredarchitecture.dao.DAOFactory;
import com.example.layeredarchitecture.dao.custom.ItemDAO;
import com.example.layeredarchitecture.dto.ItemDTO;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemBOImpl implements ItemBO {

    ItemDAO itemDAO = (ItemDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ITEM);

    @Override
    public ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException {
        return itemDAO.getAll();
    }

    @Override
    public boolean saveItems(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
        return itemDAO.save(itemDTO);
    }

    @Override
    public boolean updateItems(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
        return itemDAO.update(itemDTO);
    }

    @Override
    public boolean existItems(String code) throws SQLException, ClassNotFoundException {
        return itemDAO.exist(code);
    }

    @Override
    public boolean deleteItems(String code) throws SQLException, ClassNotFoundException {
        return itemDAO.delete(code);
    }

    @Override
    public String generateNewItemsID() throws SQLException, ClassNotFoundException {
        return itemDAO.generateNewID();
    }

    @Override
    public ItemDTO searchItems(String code) throws SQLException, ClassNotFoundException {
        return itemDAO.search(code);
    }
}
