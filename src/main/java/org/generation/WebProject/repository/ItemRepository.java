// Task 2: Using Spring Data JPA to access MySQL
// Refer further notes in readme file - overall CrudRepository chart (https://docs.google.com/document/d/1rlKbrSsvSxvvkbt4_1rX4aVbf0T7dDlT/edit#)

/*
Codecademy:Spring Data JPA uses repositories to interact the model with the database. A repository is a data access and manipulation layer that wraps around your data model. A repository will come with methods to call on instances of your data model like .save(), or .findAll(), or .findById(), enabling a no-code solution to interacting with a data model in a Spring Boot application.

When developers build APIs that interact with or manage an underlying data model, there are usually some common functionalities that they want to enable. An API that manages a data model should be able to Create, Read, Update, and Delete instances of the model. For this reason, these kinds of APIs are called CRUD APIs.

Since this kind of functionality is so common, Spring Data JPA comes with a special kind of repository interface that gives you full CRUD functionality for your model. To use it, an application developer simply imports the repository interface, tells it what model it should wrap around, and it is good to use!
 */


package org.generation.WebProject.repository;

//The ItemRepository created is to extend the CRUDRepository provided by Spring Data JPA package
//Spring provides CrudRepository implementation class automatically at runtime, therefore we just need to extend the CrudRepository interface to access its class method.

import org.springframework.data.repository.CrudRepository;
import org.generation.WebProject.repository.entity.Item;

public interface ItemRepository extends CrudRepository<Item, Integer> // Type parameter:<Class name, id type> //can replace CrudRepository with PagingAndSortingRepository, JpaRepository, etc (if functionality in CrudRepository is not to be used)
{
    //Not only the ItemRepository inherit all the methods from the CrudRepository Interface, ItemRepository can also have its own methods (DO NOT NEED now cz sufficient methods provided from CRUDRepository)

    //So now I can use the ItemRepository interface to perform basic CRUD operation

    //ItemServiceMySQL Class Object is class A. ItemServiceMySQL Class needs to retrieve all the items from the database and send it back to the Controller. ItemServiceMySQL Class Object depend on the ItemRepository Interface to perform the action. Therefore, we need to “inject” the ItemRepository object to the ItemServiceMySQL Class Object. CrudRepository is class B.

    //** IMPORTANT: https://www.javatpoint.com/spring-boot-crud-operations **

    /*
    What is the difference between CrudRepository and JpaRepository?
    CrudRepository mainly provides CRUD operations. PagingAndSortingRepository provide methods to perform pagination and sorting of records. JpaRepository provides JPA related methods such as flushing the persistence context and deleting of records in batch.
     */

    /*
    When we are building APIs, we want our models to provide four basic types of functionality. The model must be able to Create, Read, Update, and Delete resources.

    In a REST environment, CRUD often corresponds to the HTTP methods POST, GET, PUT, and DELETE, respectively. These are the fundamental elements of a persistent storage system.
     */

    /* CODECADEMY:
Create plants in the database by making POST requests and the .save method of CrudRepository
Read plants in the database by making GET requests and the .findAll() and .findById() methods of CrudRepository
Update plants in the database by using getters and setters to check and modify fields in the Plant entity, and the .save method of CrudRepository
Delete plants in the database by using the .delete method of the CrudRepository.
     */





}
