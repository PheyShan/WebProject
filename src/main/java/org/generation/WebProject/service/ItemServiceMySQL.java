package org.generation.WebProject.service;

import org.generation.WebProject.repository.ItemRepository;
import org.generation.WebProject.repository.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// @Service annotation is important in Spring Boot: to inform Spring Boot that this is service class
// From the ItemServiceMySQL Class, set up the various services that are necessary to perform for the Item (e.g. list all item, save item, delete item, and find item by Id) through the Repository layer for the CRUD operation to the database (Will implement the constructor and methods in the next lesson).
@Service
public class ItemServiceMySQL implements ItemService {

    /*
    Dependency Injection
    -transferring the task of creating the object to someone else

    Normally how we create an instance object of another class
    ItemServiceMySQL depends on the CRUDRepository Class to perform the CRUD operation
    Previous way we did in OOP:
    //We are creating the instance object inside the ItemServiceMySQL class

    ItemServiceMySQL myService = new CrudRepository();      //CANNOT do this because we don't have direct access to CrudRepository class, only access to interface.

    //ItemServiceMySQL is dependent on CrudRepository

    //1) Adhere to the abstraction (hiding detail) principle, where we only have the access to the interface of the Class object. We have no direct access to the Class Object itself

    //2) DI - IOC: Inversion of Control: The creating of the instance object is done by another object instead of in the ItemServiceMySQL Class //This states that a class should not configure its dependencies statically but should be configured by some other class from OUTSIDE (instead of within ItemServiceMySQL class)

    (https://www.freecodecamp.org/news/a-quick-intro-to-dependency-injection-what-it-is-and-when-to-use-it-7578c84fa88f/)
    Why should I use dependency injection?

    Let’s say we have a car class which contains various objects such as wheels, engine, etc.

    Here the car class is responsible for creating all the dependency objects. Now, what if we decide to ditch MRFWheels in the future and want to use Yokohama Wheels?

    We will need to recreate the car object with a new Yokohama dependency. But when using dependency injection (DI), we can change the Wheels at runtime (because dependencies can be injected at runtime rather than at compile time).

    You can think of DI as the middleman in our code who does all the work of creating the preferred wheels object and providing it to the Car class.

    It makes our Car class independent from creating the objects of Wheels, Battery, etc.

    Libraries and Frameworks that implement DI:
    -Spring (Java)
    -Google Guice (Java)
    -Dagger (Java and Android)
    -Castle Windsor (.NET)
    -Unity(.NET)

     */

    //The dependency instance object will be injected through the constructor

    private final ItemRepository itemRepository;   //create attribute
    //Constructor
    public ItemServiceMySQL(@Autowired ItemRepository itemRepository) {  //itemRepository is created outside the class

        //Injecting an instance object of the CrudRepository object
        //We are able to make use of this.itemRepository to access the properties and methods from the CrudRepository object
        this.itemRepository = itemRepository;
    }

    @Override //override all the methods in ItemService interface
    public Item save(Item item) {
        //access the database with the connection and perform Insert query
//        return null; //will update later
        return itemRepository.save(item);  //CrudRepository object
    }

    @Override
    public void delete(int itemId) {
        itemRepository.deleteById(itemId);
    }

    @Override
    public List<Item> all() {
        List<Item> result = new ArrayList<>();
        itemRepository.findAll().forEach(result :: add); //loop through each element and add into the result
//        return null; //will update later
        return result;
    }

    @Override
    public Item findById(int itemId) {   //for those who want to update product info or delete a product through an Id

        Optional<Item> item = itemRepository.findById(itemId);  //Class Optional<T>: A container object which may or may not contain a non-null value. If a value is present, isPresent() will return true and get() will return the value.  //item is an object
        Item itemResponse = item.get();
//        return null; //will update later
        return itemResponse;
    }



}
