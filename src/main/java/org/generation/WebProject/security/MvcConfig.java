//Task 1: Setup URL Routing and Templates for Web Application

//This Class is to perform action on URL Routing and mapping when a HTTP request comes in
//E.g. if user key in localhost:8080 in the browser, browser will send a HTTP GET
// request to the server (back-end). The back-end will need to handle which HTML to
// response back to the browser (client) - index.html

package org.generation.WebProject.security;

//The web application is based on Spring MVC (package that we have already installed as the dependencies)
//It is a package/module in the Spring framework that deals with the Model-View-Controller pattern
// 1) Deal with the View component first  -> Spring MVC (class) provides the functionality to route http request to their respective template (html).

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;


@Configuration
public class MvcConfig implements WebMvcConfigurer {

    //because we have implemented WebMvcConfigurer, so we can do our own implementation of the method on the WebMvcConfigurer interface

    @Value("${image.folder}")
    private String imageFolder; //now imageFolder variable the value = productImages //getting the link from application.properties under Resources file

    public void addViewControllers(ViewControllerRegistry registry) {
        //For routing, make use of Spring Boot library 'WebMvcConfigurer' to create directory path/route of View ("/.."), then return a view ("..") back to front-end/client
        //Map the browser's URL to a specific View (HTML) inside resources/templates directory
        //We can register view that create a direct mapping between the URL and the view name (.html)
        //addViewControllers is a method provided by WebMvcConfigurer interface

        registry.addViewController("/").setViewName("index"); //this is the default page where u want user to view at the first loading of the website //("name") after setViewName is html name
        registry.addViewController("/index").setViewName("index"); //("/xxx") - is a route
        registry.addViewController("/aboutus").setViewName("aboutus");
        registry.addViewController("/products").setViewName("products");
        registry.addViewController("/productform").setViewName("productform");

        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/logout").setViewName("index");

    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //expose the images, js, css resources (from resources > static folder) to the client (browser) so that they can access the necessary files to display
        // browser is an external client that tries to access the files from the server
        // To allow external client to access the files from the static folder due to security reason (e.g. file such as images, videos, sound, js, css)
        registry.addResourceHandler("/static")
                .addResourceLocations("classpath:/static/")
                .setCachePeriod(0);


        //expose the productImages folder to allow external client to access the files from the server
        Path uploadDir = Paths.get(imageFolder);  //instead of hardcode here, add linked coding in application.properties under Resources file //ABOVE - getting the link from application.properties under Resources file
        String uploadPath = uploadDir.toFile().getAbsolutePath();

        registry.addResourceHandler("/" + imageFolder + "/**")
                .addResourceLocations("file:" + uploadPath + "/")
                .setCachePeriod(0); //- prevent cache of files (e.g images) in the browser

    }


}
