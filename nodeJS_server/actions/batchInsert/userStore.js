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

    let myrequest = "INSERT INTO userstore (storeId, userId, date) VALUES ";

    for (var i = 0; i < max; i++) {
        let storeId = Math.floor(Math.random() * Math.floor(219999)) + 10006;
        let userId = Math.floor(Math.random() * Math.floor(50003)) + 1;
        let date = faker.date.past();
        date = dateFormat(date, "yyyy-mm-dd HH:MM:ss");

        myrequest += "(" + storeId + ", " + userId + ", '" + date + "')"
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
