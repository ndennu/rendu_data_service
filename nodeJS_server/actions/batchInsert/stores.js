const faker = require('faker');

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

    let myrequest = "INSERT INTO store (name, address, postcode, city, latitude, longitude, typeId) VALUES ";

    for (var i = 0; i < max; i++) {
        let name = faker.name.findName().replace("'", "");
        let address = faker.address.streetAddress().replace("'", "");
        let postcode = faker.address.zipCode().substr(0, 5);
        let city = faker.address.city().replace("'", "");
        let latitude = faker.address.latitude();
        let longitude = faker.address.longitude();
        let typeId = Math.floor(Math.random() * Math.floor(2)) + 1;

        myrequest += "('" + name + "', '" + address + "', '" + postcode + "'," +
                        "'" + city +"', '" + latitude + "', '" + longitude + "', " + typeId + ")"
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
