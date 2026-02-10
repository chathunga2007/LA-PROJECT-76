package com.example.layeredarchitecture.bo.custom;

import com.example.layeredarchitecture.bo.custom.impl.CustomerBOImpl;
import com.example.layeredarchitecture.bo.custom.impl.ItemBOImpl;
import com.example.layeredarchitecture.bo.custom.impl.PlaceOrderBOImpl;

public class BOFactory {
    public static BOFactory boFactory;

    private BOFactory() {}

    public static BOFactory getInstance() {
        return boFactory == null ? new BOFactory() : boFactory;
    }

    public enum BOType {
        CUSTOMER, ITEM, PLACE_ORDER
    }

    public SuperBO getBO(BOFactory.BOType daoType) {
        switch (daoType) {
            case CUSTOMER:
                return new CustomerBOImpl();
            case ITEM:
                return new ItemBOImpl();
            case PLACE_ORDER:
                return new PlaceOrderBOImpl();
            default:
                return null;
        }
    }
}
