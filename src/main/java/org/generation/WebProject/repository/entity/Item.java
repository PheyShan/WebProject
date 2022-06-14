//Task 2: Dependencies Injection using Spring Framework

package org.generation.WebProject.repository.entity;

//This is in the Model component (MVC Design Pattern)
//Item Class object will be used to map up with the Item Table from the database

//We are using the same name for Class Object and Table name, as well as the name naming convention for the attributes

import org.generation.WebProject.controller.dto.ItemDto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//Annotations are important
@Entity  //need to inform so that Spring Boot will use it to do mapping // To inform Hibernate to map to a table with this class object, without the annotation, won't work
public class Item {

    //Class attributes

    //Attribute names in Class must be the same as the Database table

    //We need to identify which attribute is the id (Primary/Composite Key), and how the id is generated
    //Annotation shall always be placed on top of particular attribute //@Id has to be put above 'private Integer id' only
    @Id  //tells the ORM that this field (id) will be used to uniquely identify a single entry in our relational database
    @GeneratedValue(strategy = GenerationType.IDENTITY) // tells the ORM that the developer will not be supplying the value for this field themselves. Instead, it should be “auto-generated” by the database (auto-increment). Typically, an @Id field for an entity will be auto-generated in this way, so that we can leverage the database to guarantee that the ID will always be unique.
    private Integer id;   //In Spring Boot, using Wrapper Class Integer (Object) instead int (Primitive Data type) as we need to pass in id (argument) to a class method findItemById
    private String name;
    private String description;
    private String imageUrl;
    private String style;
    private double price; //Primitive Data type (no need Wrapper Class unless there is argument to pass in later)

    //Constructor
    public Item() {} //constructor to save

    //overload the constructor (Constructor overloading: a technique of having more than one constructor with different parameter lists)
    public Item(ItemDto itemDto) { //constructor to retrieve //this ItemDto is sent by Service Layer instead of Controller as Controller cannot directly send to Model component
        this.name = itemDto.getName();
        this.description = itemDto.getDescription();
        this.imageUrl = itemDto.getImageUrl();
        this.style = itemDto.getStyle();
        this.price = itemDto.getPrice();
    }

    //Getter & Setter
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }



    //toString()
    @Override
    public String toString()
    {
        return "Item{" + "id=" + id + ", name='" + name + '\'' + ", description='" + description + '\'' + ", imageUrl='"
                + imageUrl + '\'' + ",style='" + style + '\'' + ", price='" + price + '}';
    }

}
