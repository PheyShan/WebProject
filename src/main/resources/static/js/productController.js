//Task 2: Display and add items from Web Application
//This productController.js is the central between front end & backend

//Doing a Product web app, in product page to
//display the name, description, imageUrl, style, price, ..., ...,.....,....


const createHTMLList = (index, name, description, imageURL) =>
`
<div class="col-lg-4">
<div class="card" style="width: 18rem;">
    <img src=${imageURL} class="card-img-top"
        alt="image">
    <div class="card-body">
        <h5 class="card-title">${name}</h5>
        <p class="card-text">${description}</p>
        <a id="${index}" href="#" class="btn btn-primary" data-toggle="modal" data-target="#productModal">More</a>
    </div>
</div>
</div>

`;


function displayProductDetails(item)
{
    document.querySelector("#modalName").innerText = item.name;
    document.querySelector("#modalImg").src = item.imageUrl;
    document.querySelector("#modalStyle").innerText = item.style;
    document.querySelector("#modalPrice").innerText = item.price;

}


class ProductsController 
{
    constructor()
    {
        //Configuration of dev and prod URL added into constructor - usually we will fetch the json file with all the API in the dev or prod environment
        this.domainURL_Dev = "http://localhost:8080/";
        this.domainURL_Prod = "https://pswebproject.herokuapp.com/"; //get in Heroku (open app)
        this.addItemAPI = this.domainURL_Prod + "item/add";
        this.allItemAPI = this.domainURL_Prod + "item/all";
//        this.addItemAPI = 'http://localhost:8080/item/add';
//        this.allItemAPI = "http://127.0.0.1:8080/item/all";

        this._items = [];       //create an empty array to store the details of product items
    }

//Add items from ProductForm.html (with upload of Product images to the local server)

    //method to add the items into the database
    addItem(name, description, imageUrl, style, price, imageObject)  //imageObject is parameter
    {
     var productController = this;
            const formData = new FormData();
            formData.append('name', name);  //'name' is the key (must match with ItemController.java @RequestParam key- "name"); name is the value (from productFrom.js addItem)
            formData.append('description', description);
            formData.append('imageUrl', imageUrl);
            formData.append('style', style);
            formData.append('price', price);
            formData.append('imagefile',imageObject);

//  fetch('http://localhost:8080/item/add', {
           fetch(this.addItemAPI, {
                 method: 'POST', //if not specify any method here, default will be GET method, however others like POST, DELETE, PUT, PATCH have to specify method here
                 body: formData
                 })
                 .then(function(response) {
                     console.log(response.status); // Will show you the status
                     if (response.ok) {
                         alert("Successfully Added Product!")
                     }
                     else {
                        throw Error(response.statusText);  //the fetch() API only rejects a promise when a “network error is encountered, although this usually means permissions issues or similar.”
                     }
                 })
                 .catch((error) => {
                     console.error('Error:', error);
                     alert("Error adding item to Product")
                 });

    }


//DELETED
//       {
//         const itemObj = {
//                    oName: name,
//                    oDescription: description,
//                    oImageUrl: imageUrl,
//                    oStyle: style,
//                    oPrice: price
//                };
//
//                this._items.push(itemObj);
//        }

//Display items to Product.html
//Edit the ‘productController.js” to fetch the product list from the database and display on the product.html.

    //This displayItem() method will do the fetch method to fetch data from database using the REST API endpoint from Spring Boot // this is to fetch data //fetch data from database by calling API before display (renderProductPage)
    displayItem()
    {

        let productController = this;
        productController._items = [];

     //fetch data from database using the REST API endpoint from Spring Boot
     //localhost is a hostname that refers to the computer that is executing a program — you can think of it as meaning “this computer.” Localhost has the IP address 127.0.0.1, which refers back to your own server
     //if in a browser, if http://localhost:8080 is entered, it simply means to server web-pages from local web-server which is listening for web-requests on 8080 port.
//fetch(http://127.0.0.1:8080/item/all)
        fetch(this.allItemAPI)  //if not specify any method here, default will be GET method, however others like POST, DELETE, PUT, PATCH have to specify method here
            .then((resp) => resp.json())
            .then(function(data) {
                console.log("2. receive data")
                console.log(data);
                data.forEach(function (item, index) {

                    const itemObj = {
                        id: item.id,                    //1
                        name: item.name,                //Cat Tee Black T-Shirt
                        description: item.description,  //4 MSL
                        imageUrl: item.imageUrl,
                        style: item.style,
                        price: item.price
                   };
                    productController._items.push(itemObj);
              });

              productController.renderProductPage();   //call renderProductPage()

            })
            .catch(function(error) {
                console.log(error);
            });

    }

    //Based on the item fetched from the displayItem() method and show the products in the product page //main purpose is just to display products

    renderProductPage()  // replaced displayItem
    {
        let productHTMLList = [];
        
        for (let i=0; i<this._items.length; i++)
        {
            const item = this._items[i];            //assign the individual item to the variable

            const productHTML = createHTMLList(i, item.name, item.description, item.imageUrl);

            productHTMLList.push(productHTML);
        }

        //Join all the elements/items in my productHTMLList array into one string, and separate by a break
        const pHTML = productHTMLList.join('\n');
        document.querySelector('#row').innerHTML = pHTML;


        //addEventListener - click 
        for (let i=0; i<this._items.length; i++)
        {
            const item = this._items[i];
            document.getElementById(i).addEventListener("click", function() { displayProductDetails(item);} );
        }


    }


}   //End of ProductController class
