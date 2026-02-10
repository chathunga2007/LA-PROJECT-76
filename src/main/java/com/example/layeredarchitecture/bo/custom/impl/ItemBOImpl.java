package com.example.layeredarchitecture.bo.custom.impl;

import com.example.layeredarchitecture.bo.custom.ItemBO;
import com.example.layeredarchitecture.dao.DAOFactory;
import com.example.layeredarchitecture.dao.custom.ItemDAO;
import com.example.layeredarchitecture.dto.ItemDTO;
import com.example.layeredarchitecture.entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public class ItemBOImpl implements ItemBO {

    ItemDAO itemDAO = (ItemDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ITEM);

    @Override
    public ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException {
        ArrayList<Item>items=itemDAO.getAll();
        ArrayList<ItemDTO>itemDTOS=new ArrayList<>();
        for(Item item:items){
            ItemDTO itemDTO=new ItemDTO(item.getCode(),item.getDescription(),item.getUnitPrice(),item.getQtyOnHand());
            itemDTOS.add(itemDTO);
        }
        return itemDTOS;
    }

    @Override
    public boolean saveItems(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
        return itemDAO.save(new Item(itemDTO.getCode(), itemDTO.getDescription(), itemDTO.getUnitPrice(), itemDTO.getQtyOnHand()));
    }

    @Override
    public boolean updateItems(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
        return itemDAO.update(new Item(itemDTO.getCode(), itemDTO.getDescription(), itemDTO.getUnitPrice(), itemDTO.getQtyOnHand()));
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
        return null;
    }
}
