let mysql = require('mysql');

const minItemId = 1;
const maxItemId = 950000;

const minStoreId = 1;
const maxStoreId = 228626;

const maxQuantity = 20;

var connection = mysql.createConnection({
    host: "localhost",
    user: "root",
    password: "",
    database: "dataservicedb2.0" //TODO: change ta base
}, function(err){
    console.log(err);
});

connection.connect((err) => {
    if (err) {
        console.log('Error during connection at database: ' + err);
    }
});

var total = 1000000; // TODO: Change total

var usedValues = [];

var cpt = 0;


let myrequest = "INSERT INTO `storeitem`(`itemId`, `storeId`, `quantity`) VALUES";

for (let i = 0; i < 3; i++) {
    let itemId =  Math.floor(Math.random() * maxItemId - minItemId) + minItemId;
    let storeId = Math.floor(Math.random() * maxStoreId - minStoreId) + minStoreId;
    let quantity = Math.floor(Math.random() * maxQuantity);

    var valueFound = usedValues.find((value)=> {
        return value.itemId == itemId && value.storeId == storeId;
    });

    if(valueFound == undefined) {

        usedValues.push({
            itemId: itemId,
            storeId: storeId,
            quantity: quantity
        });

        if (i != 0)
            myrequest += ", ";
        myrequest += "(" + itemId + ", " + storeId + ", " + quantity + ")"
    }
}

connection.query(myrequest, function(err, rows) {
    if(err) {
        console.log(err);
    } else {
        cpt++;
        console.log(cpt);
    }
});
