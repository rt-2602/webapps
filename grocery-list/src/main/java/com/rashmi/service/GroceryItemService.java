package com.rashmi.service;

import com.rashmi.model.GroceryData;
import com.rashmi.model.GroceryItem;

public interface GroceryItemService {

    void addItem(GroceryItem toAdd);

    void removeItem(int id);

    GroceryItem getItem(int id);

    void updateItem(GroceryItem toUpdate);

    GroceryData getData();
}
