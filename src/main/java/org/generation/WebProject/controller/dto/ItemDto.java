package org.generation.WebProject.controller.dto;

//Data Transfer Object (DTO) for Item
//In the Controller component (MVC), this class object is going to receive the info/data that is sent from the client (through the HTTP POST request)

//Controller will then call the required method (save method) to perform Create and pass the info/data to the Service layer

//PS: DTO is an object that carries data between processes. When you're working with a remote interface, each call it is expensive. As a result you need to reduce the number of calls. The solution is to create a Data Transfer Object that can hold all the data for the call.

public class ItemDto {

    private String name;
    private String description;
    private String imageUrl;
    private String style;
    private double price;

    //no need id here because cannot ask user to create, have to create from backend via Auto Increment in SQL database table

    //Constructor
    public ItemDto(String name, String description, String imageUrl, String style, double price) {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.style = style;
        this.price = price;
    }

    //Getter & Setter
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


}
