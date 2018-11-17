module.exports = (api) => {

    return (req, res) => {

        var connection = api.models.mysql.createConnection({
            host: api.settings.db.sql.sequelizeParamSetting.host,
            user: api.settings.db.sql.dbUser,
            password: api.settings.db.sql.dbUserPassword,
            database: api.settings.db.sql.database
        }, function(err){
            console.log(err);
        });

        connection.connect(function(err)
        {
            if (!err)
            {
              console.log("Connected to MySQL");
            }
            else if (err)
            {
              console.log(err);
            }
        });

        var query = "SELECT item.id, item.name " +
                    "FROM brand JOIN store ON brand.id = store.brandId " +
                        "JOIN storeitem ON store.id = storeitem.storeId " +
                        "JOIN item ON storeitem.itemId = item.id " +
                    "WHERE brand.id = " + parseInt(req.body.brandId) + " " +
                    "AND item.categoryId = " + parseInt(req.body.categoryId) + ";"

        connection.query(query,
            function(err, rows) {
                if(err)
                {
                    console.error(err);
                    connection.end();
                    return res.status(500).send({
                        "returnCode": 500,
                        "errorMessage": JSON.stringify(err),
                        "data": null
                    });
                }
                if(rows.length)
                {
                    connection.end();
                    return res.status(200).send({
                        "returnCode": 200,
                        "errorMessage": null,
                        "data": rows
                    });
                }
                else
                {
                    console.log("no data found");
                    connection.end();
                    return res.status(500).send();
                }
            }
        );
    };
};
