package com.rashmi.controller;

import com.rashmi.model.GroceryData;
import com.rashmi.model.GroceryItem;
import com.rashmi.service.GroceryItemService;
import com.rashmi.util.AttributeNames;
import com.rashmi.util.Mappings;
import com.rashmi.util.ViewNames;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Slf4j
@Controller
public class GroceryItemController {

    // == fields ==
    private final GroceryItemService groceryItemService;

    //== constructor ==

    @Autowired
    public GroceryItemController(GroceryItemService groceryItemService) {
        this.groceryItemService = groceryItemService;
    }

    // == model attribute ==
    @ModelAttribute
    public GroceryData todoData(){
        return groceryItemService.getData();
    }

    //== handler methods ==

    @GetMapping(Mappings.HOME)
    public String home(){
        return ViewNames.HOME;
    }


    //http://localhost:8080/grocery-list/items
    @GetMapping(Mappings.ITEMS)
    public String items(){
        return ViewNames.ITEMS_LIST;
    }

    @GetMapping(Mappings.ADD_Item)
    public String addEditItem(@RequestParam(required = false, defaultValue = "-1") int id,
                              Model model){
        log.info("editing id = {}", id);
        GroceryItem groceryItem = groceryItemService.getItem(id);

        if(groceryItem == null){
            groceryItem = new GroceryItem("","", LocalDate.now());
        }

        model.addAttribute(AttributeNames.GROCERY_ITEM, groceryItem);
        return ViewNames.ADD_ITEM;
    }

    @PostMapping(Mappings.ADD_Item)
    public String processItem(@ModelAttribute(AttributeNames.GROCERY_ITEM) GroceryItem groceryItem){
        log.info("todoItem from form={}", groceryItem);

        if(groceryItem.getId() == 0){
            groceryItemService.addItem(groceryItem);
        }else{
            groceryItemService.updateItem(groceryItem);
        }
        return "redirect:/" + Mappings.ITEMS;
    }

    @GetMapping(Mappings.DELETE_ITEM)
    public String deleteItem(@RequestParam int id){
        log.info("Deleting item with id = {}", id);
        groceryItemService.removeItem(id);
        return "redirect:/" + Mappings.ITEMS;
    }

    @GetMapping(Mappings.VIEW_ITEM)
    public String viewItem(@RequestParam int id, Model model){
        GroceryItem groceryItem = groceryItemService.getItem(id);
        model.addAttribute(AttributeNames.GROCERY_ITEM, groceryItem);
        return ViewNames.VIEW_ITEM;
    }


}
