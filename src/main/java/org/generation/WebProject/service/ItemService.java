package org.generation.WebProject.service;

//Do/Create Interface first!

//Interface as a guide on what are the methods that are available that the controller can call and perform action.

//Service layer is to be developed by developer A (will need to produce Interface and provide to developer B); controller layer is to be developed by developer B. Developer B will be accessing the Interface document to know what are the methods available for him/her to call.

import java.util.List;
import org.generation.WebProject.repository.entity.Item;


public interface ItemService {

    //will show what are the methods for this Item Service Interface(showing 5 methods in this example - all(), save() [add(), edit()], delete(), findById())
    //e.g. 1) provides/return all list items from the database
    List<Item> all();  //Read all item from database //List is an Interface (eg. arraylist), Item is class object, all() is a method

    Item save(Item item);   //save method is for 2 purposes - Create new item & Update existing item //this method is used for both add item and edit item

    void delete(int itemId);  //Delete item from database - based on item Id

    Item findById(int itemId); //Read an item from database - based on item Id


}
