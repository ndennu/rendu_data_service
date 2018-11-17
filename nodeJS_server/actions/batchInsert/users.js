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

    let myrequest = "INSERT INTO user (email, password, sexe, birthdate, size, shoe_size) VALUES ";

    let tabSize = ['XS', 'S', 'M', 'L', 'XL', 'XM'];


    for (var i = 0; i < max; i++) {
        let email = faker.internet.email().replace("'", "");
        let password = faker.internet.password().replace("'", "");
        let sexe = Math.floor(Math.random() * Math.floor(2));
        let birthdate = faker.date.past();
        birthdate = dateFormat(birthdate, "yyyy-mm-dd");
        let size = tabSize[Math.floor(Math.random() * Math.floor(tabSize.length))];
        let shoe_size = Math.floor(Math.random() * Math.floor(11)) + 40;

        myrequest += "('" + email + "', '" + password + "', " + sexe + "," +
                        "'" + birthdate +"', '" + size + "', " + shoe_size + ")"
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
