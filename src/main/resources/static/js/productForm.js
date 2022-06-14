// for productform.html

//Created to capture all the uploaded image files in Product Form and send to backend

const productsControl = new ProductsController();
let storeImage = ""

//When user clicks on 'Save Item', calls API to add items to the database
//Add an 'onsubmit' event listener for productform to add a product
newItemForm.addEventListener('submit', (event) => { //upon submit, execute the below actions

    // Prevent default action, do not need the form to submit first because want to do (1) Form Validation (2) In fact, we are using our own fetch method to send the data over to the backend (REST API)
    event.preventDefault();
    // Select the inputs
    const newItemNameInput = document.querySelector('#newItemNameInput');
    const newItemDescription = document.querySelector('#newItemDescription');
    const newItemImageUrl = document.querySelector('#newItemImageFile');
    const newItemStyle = document.querySelector('#newItemStyle');
    const newItemPrice = document.querySelector('#newItemPrice');

    /*
        Do the Validation code here
    */

    // Get the values of the inputs - variable names to be same as MySQL columns
    const name = newItemNameInput.value;
    const description = newItemDescription.value;
//    const imageUrl = "images/" + newItemImageUrl.value.replace("C:\\fakepath\\", "");  //do not need to include image path here anymore (front end), already done in SQL database table (back end)
// C:\desktop\t-shirt_new.jpg -> C:\fakepath\t-shirt_new.jpg //browser will not get the info from desktop (violation-security concern, cannot let browser to access desktop, otherwise browser/anybody will delete/override something in user's own desktop), it will convert to fakepath first
//Here we just need to send the image filename (t-shirt_new.jpg) to the backend
//imageUrl will only contain the image filename (t-shirt_new.jpg) without the fakepath
    const imageUrl = newItemImageUrl.value.replace("C:\\fakepath\\", "");
    const style = newItemStyle.value;
    const price = newItemPrice.value;

    // Clear the form
    newItemNameInput.value = '';
    newItemDescription.value = '';
    newItemImageUrl.value = '';
    newItemStyle.value = '';
    newItemPrice.value = '';

    // Add the task to the task manager
    //After we get all the values and object from the form, we will call the addItem method in the ProductController class to perform the POST HTTP Request by calling the REST API provided by the backend
    productsControl.addItem(name, description, imageUrl, style, price, storeImage);  //storeImage is argument to pass in to parameter

});

// select file input
const input = document.querySelector('#newItemImageFile');

// add event listener
input.addEventListener('change', () => {
    storeImage = input.files[0]; //Storing the first file element to the variable // file[0] object can be image, audio, video, pdf, word doc
    //storeImage variable is the one that need to pass to backend
});
