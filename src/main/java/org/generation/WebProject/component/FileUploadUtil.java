package org.generation.WebProject.component;

//This Class Object is to provide action to "save/store" the uploaded image file from the client to a folder in the server //In the ‘WebProject’ folder, add the ‘productImages’ directory to store all the Product images for the web application.

//This Class Object will be called by the @PostMapping in the Controller Layer

//Multipart requests consist of sending data of many different types separated by a boundary as part of a single HTTP method call.

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileUploadUtil {

    /*
    This saveFile method will be called by the Controller under @PostMapping
    There are 3 parameters that the method required:
    (1) Directory Path through relative path of the root folder (WebProject folder) - String uploadDir1
    (2) File name of the image (e.g. t-shirt_new.jpg) - String fileName
    (3) actual image object itself - MultipartFile multipartFile
     */

    public static void saveFile(String uploadDir1, String fileName,
                                MultipartFile multipartFile) throws IOException
    {
        //productImage is the directory that we setup
        //Paths.get - convert a path string to the directory path for the upload
        Path uploadPath1 = Paths.get(uploadDir1);

        //getInputStream method is from the Multipart Class package
        //get path, get object, use files.copy to write imagesfile into folder itself (replace the existing same file name),
        try (InputStream inputStream = multipartFile.getInputStream()) {

            Path filePath1 = uploadPath1.resolve(fileName);
            Files.copy(inputStream, filePath1, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
            throw new IOException("Could not save image file: " + fileName, ioe);
        }
    }

}
