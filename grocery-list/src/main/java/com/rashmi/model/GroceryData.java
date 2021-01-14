package com.rashmi.model;

import lombok.NonNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

public class GroceryData {

    // == fields ==
    private static int idValue = 1;

    private final List<GroceryItem> items = new ArrayList<>();

    //== constructors ==
    public GroceryData(){

        // add some dummy data, using the addItem method so it sets id field
        addItem(new GroceryItem("first", "first details", LocalDate.now()));
        addItem(new GroceryItem("second", "second details", LocalDate.now()));
        addItem(new GroceryItem("third", "third details", LocalDate.now()));
        addItem(new GroceryItem("fourth", "fourth details", LocalDate.now()));

    }

    // == public methods ==
    public List<GroceryItem> getItems(){
        return Collections.unmodifiableList(items);
    }

    public void addItem(@NonNull GroceryItem toAdd){

        /*commenting to use Lombok, @NonNull annotation
        if(toAdd == null){
            throw new NullPointerException("toAdd is a required parameter.");
        }*/

        toAdd.setId(idValue);
        items.add(toAdd);
        idValue++;
    }

    public void removeItem(int id){
        ListIterator<GroceryItem> itemIterator = items.listIterator();

        while (itemIterator.hasNext()){
            GroceryItem item = itemIterator.next();

            if(item.getId() == id){
                itemIterator.remove();
                break;
            }
        }
    }

    public GroceryItem getItem(int id){
        for(GroceryItem item : items){
            if(item.getId() == id){
                return item;
            }
        }
        return null;
    }

    public void updateItem(@NonNull GroceryItem toUpdate){
        ListIterator<GroceryItem> itemIterator = items.listIterator();

        while(itemIterator.hasNext()){
            GroceryItem item = itemIterator.next();

            if(item.equals(toUpdate)){
                itemIterator.set(toUpdate);
                break;
            }
        }
    }
}
