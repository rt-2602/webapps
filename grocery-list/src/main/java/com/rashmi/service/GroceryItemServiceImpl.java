package com.rashmi.service;

import com.rashmi.model.GroceryData;
import com.rashmi.model.GroceryItem;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
public class GroceryItemServiceImpl implements GroceryItemService {

    //== fields ==
    @Getter
    private final GroceryData data = new GroceryData();

    // == public methods ==
    @Override
    public void addItem(GroceryItem toAdd) {
        data.addItem(toAdd);
    }

    @Override
    public void removeItem(int id) {
        data.removeItem(id);
    }

    @Override
    public GroceryItem getItem(int id) {
        return data.getItem(id);
    }

    @Override
    public void updateItem(GroceryItem toUpdate) {
        data.updateItem(toUpdate);
    }

}
