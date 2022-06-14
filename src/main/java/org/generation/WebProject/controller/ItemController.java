package org.generation.WebProject.controller;


import org.generation.WebProject.component.FileUploadUtil;
import org.generation.WebProject.controller.dto.ItemDto;
import org.generation.WebProject.repository.entity.Item;
import org.generation.WebProject.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

//STEP 1: Create ItemController with RestController and RequestMapping. Get all records from the database

@RestController  //inform Spring Boot
@RequestMapping("/item")  //http://localhost:8080/item/... //mapping to ItemController //"/item" is a resource
public class ItemController {

    //ItemController is dependent on ItemService to perform the save, delete, all, findItemById

    //In ItemController.java, implement the Controller to include an ItemService interface for REST API implementation.
    //Display all records from the database. (Note: add @CrossOrigin for all Mapping annotations)

    @Value("${image.folder}")
    private String imageFolder; //now imageFolder variable the value = productImages //getting the link from application.properties under Resources file

    final ItemService itemService;

    public ItemController( @Autowired ItemService itemService )
    {
        this.itemService = itemService; //can make use of itemService to access any methods in Service Layer
    }

    // 1) Create an API endpoint for GET HTTP Request from the client
    //@CrossOrigin handles CORS (Cross-origin resource sharing)
    //  Launch Postman or Thunder Client (VSC) to test the REST API.

    /*
    What is an API Endpoint?
    Simply put, an endpoint is one end of a communication channel. When an API interacts with another system, the touchpoints of this communication are considered endpoints. For APIs, an endpoint can include a URL of a server or service. Each endpoint is the location from which APIs can access the resources they need to carry out their function.

    APIs work using ‘requests’ and ‘responses.’ When an API requests information from a web application or web server, it will receive a response. The place that APIs send requests and where the resource lives, is called an endpoint.
     */

    @CrossOrigin //enables cross-origin resource sharing only for this specific method. By default, it allows all origins, all headers, and the HTTP methods specified in the @RequestMapping annotation.
    @GetMapping( "/all" ) //http://localhost:8080/item/all //mapping to GetMapping (when user make a GET request to "/all" endpoint)
    public Iterable<Item> getItems() {
        return itemService.all();  //call all items from database (List<Item> all();) from the itemService interface // In most cases, the Iterable will be an instance of Collection, such as a List or a Set. //Codecademy: Iterable, which is just a simplified interface for a collection in Java. Note that the Iterable has a type parameter, Item.
    }

    // 2) Create an API endpoint for GET HTTP Request from the client by Id
    //STEP 2: Display a record with an ID
    @CrossOrigin
    @GetMapping( "/{id}" ) //http://localhost:8080/item/5
    public Item findItemById( @PathVariable Integer id ) //@PathVariable annotation can be used to handle template variables in the request URL mapping, and set them as method parameters. //Used as long as the path includes the {id} variable in URL
    {
        return itemService.findById( id );
    }

    // 3) Create an API endpoint for DELETE HTTP Request from the client by Id
    //Step 3: Delete a record with an ID
    //Deleted item will also be removed from MySQL database table
    @CrossOrigin
    @DeleteMapping( "/{id}" )  //http://localhost:8080/item/5
    public void delete( @PathVariable Integer id )
    {

        itemService.delete( id );
    }


    // 4) Create an API endpoint for POST HTTP Request from the client
    //Step 4: Add a new record (see component package >FileUploadUtil Class)

    //@RequestParam is an annotation that can be used at the method parameter level. It allows us to parse query parameters and capture those parameters as method arguments. This is incredibly helpful because we can take values passed in from the HTTP request, parse them, and then bind the values to a controller method for further processing.
    @CrossOrigin
    @PostMapping("/add")  //http://localhost:8080/item/add  //
    public void save(  @RequestParam(name="name", required = true) String name, //'required = true' means the field is required to fill in, cannot be null //
                       @RequestParam(name="description", required = true) String description,
                       @RequestParam(name="imageUrl", required = true) String imageUrl,  // only the text 't-shirt1.jpg', etc ...
                       @RequestParam(name="style", required = true) String style,
                       @RequestParam(name="price", required = true) double price,
                       @RequestParam("imagefile") MultipartFile multipartFile) throws IOException { //actual image file

        //Prepare the fileName by cleaning up the path for saving the image file
        //Part 1 - upload the image file into the productImages folder in the server
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        FileUploadUtil.saveFile(imageFolder, fileName, multipartFile);  //pass in the 3 parameters from FileUploadUtil.java

        //Part 2 - save all the records in the MySQL database
        String fullPath = imageFolder + "/" + imageUrl;  // store image path (productImages) & imageURL (t-shirt1.jpg), ie. productImages/t-shirt1.jpg

        ItemDto itemDto = new ItemDto(name, description, fullPath, style, price);
        itemService.save(new Item(itemDto));
    }

    /*
    HTTP Method Annotation: Usage
    @GetMapping:	        Used to specify GET requests to retrieve resources
    @PostMapping:	        Used to specify POST requests to create new resources
    @PutMapping:	        Used to specify PUT requests to update existing resources
    @DeleteMapping:	        Used to specify DELETE requests to remove specific resources
     */




}
