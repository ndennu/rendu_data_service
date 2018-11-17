const faker = require('faker');
const dateFormat = require('dateformat');
const max = 10000;

module.exports = function(api) {
    var connection = api.models.mysql.createConnection({
        host: api.settings.db.sql.sequelizeParamSetting.host,
        user: api.settings.db.sql.dbUser,
        password: api.settings.db.sql.dbUserPassword,
        database: api.settings.db.sql.database
    }, function(err){
        console.log(err);
    });

    let myrequest = "INSERT INTO item (name, price, categoryId) VALUES ";

    for (var i = 0; i < max; i++) {
        let name = faker.commerce.productName();
        let price = faker.commerce.price();
        let categoryId = Math.floor(Math.random() * Math.floor(4)) + 1;

        myrequest += "('" + name + "', " + price + ", " + categoryId + ")"
        if (i != max - 1)
            myrequest += ", ";
    }

    connection.connect(function(err) {
        if (err)
        {
            console.log(err);
        }
    });

    connection.query(myrequest,
        function(err, rows) {
            connection.end();
        });
    };
