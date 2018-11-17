const geolib = require('geolib');

module.exports = (api) => {
    const UserLocation = api.models.UserLocation;

    return (req, res) => {

        let coordsUser = { latitude: req.body.latitude, longitude: req.body.longitude }

        let distanceInMeter = req.body.distance;

        let connection;

        connectToDatabase();
        request();

        function connectToDatabase() {
            connection = api.models.mysql.createConnection({
                host: api.settings.db.sql.sequelizeParamSetting.host,
                user: api.settings.db.sql.dbUser,
                password: api.settings.db.sql.dbUserPassword,
                database: api.settings.db.sql.database
            }, function(err){
                console.log(err);
            });

            connection.connect((err) => {
                 if (err) {
                  console.log('Error during connection at database: ' + err);
                }
            });
        }

        function request() {
            connection.query("SELECT store.id, store.name, store.address, store.postcode, store.city, store.latitude, store.longitude, storevisitor.visitor, storeitem.quantity " +
                                "FROM store JOIN storeitem ON store.id = storeitem.storeId " +
                                            "LEFT JOIN storevisitor ON store.id = storevisitor.idStore " +
                                "WHERE storeitem.itemId = " + parseInt(req.body.itemId) + " " +
                                "AND storeitem.quantity > 0;",
                function(err, rows) {
                    if(err) {
                        console.error(err);
                        connection.end();
                        return res.status(500).send({
                            "returnCode": 500,
                            "errorMessage": JSON.stringify(err),
                            "data": null
                        });
                    }
                    if(rows.length) {
                        console.log("SRC",rows);
                        let filteredStores = closeToShop(rows);
                        filteredStores = getDistanceUserStore(filteredStores);
                        console.log(filteredStores);
                        res.status(200).send({
                            "returnCode": 200,
                            "errorMessage": null,
                            "data": filteredStores
                        });
                    }
                    else {
                        return res.status(204).send({
                            "returnCode": 204,
                            "errorMessage": null,
                            "data": []
                        });
                    }
                    connection.end();
                });
        }

        function closeToShop(dataRows) {

            let result = [];

            for (let r of dataRows) {
                let tempObj = { latitude: parseFloat(r.latitude), longitude: parseFloat(r.longitude) };

                let isCloseTo = geolib.isPointInCircle(tempObj,
                    coordsUser,
                    distanceInMeter);

                if (isCloseTo) {
                    result.push(r);
                }
            }

            return result;
        }

        function getDistanceUserStore(storeArray) {

            for (let store of storeArray) {
                let tempObj = { latitude: parseFloat(store.latitude), longitude: parseFloat(store.longitude) };

                let d = geolib.getDistanceSimple(coordsUser, tempObj);

                store.distance = d;
            }
            return storeArray;
        }

        function error(err) {
            res.status(500).send({
                "returnCode": 500,
                "errorMessage": JSON.stringify(err),
                "data": null
            });
        }
    };

};
