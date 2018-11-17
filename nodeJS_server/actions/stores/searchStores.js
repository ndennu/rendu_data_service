module.exports = (api) => {
    const UserLocation = api.models.UserLocation;
    const UserStore = api.models.UserStore;

    // get stores with itemId, rangeUser, coordinate, userId,
    // return store list qui possede l'article en stock et nb personne et la distance
  
    return (req, res) => {

        function getDistanceFromLatLonInKm(lat1,lon1,lat2,lon2) {
            var R = 6371; // Radius of the earth in km
            var dLat = deg2rad(lat2-lat1);  // deg2rad below
            var dLon = deg2rad(lon2-lon1); 
            var a = 
              Math.sin(dLat/2) * Math.sin(dLat/2) +
              Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * 
              Math.sin(dLon/2) * Math.sin(dLon/2); 
            var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a)); 
            var d = R * c; // Distance in km
            return d;
        }
          
        function deg2rad(deg) {
            return deg * (Math.PI/180)
        }

        var newUserLocation = {
            idUser: req.body.idUser,
            latitude: req.body.latitude,
            longitude: req.body.longitude,
            datetime: new Date().toUTCString()
        };

        UserLocation.findById(newUserLocation.idUser)
        .then(() => { 
            UserLocation.build(newUserLocation).save();
        })
        .then(() => {
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
    
            connection.query("SELECT id, name, address, postcode, city, latitude, longitude " +
                                "FROM store WHERE id IN (" +
                                    "SELECT store.id " +
                                    "FROM store JOIN storeitem ON store.id = storeitem.storeId " +
                                    "WHERE storeitem.itemId = " + parseInt(req.body.itemId) + " " +
                                    "AND storeitem.quantity > 0 );",
                function(err, rows) {
                    if(err)
                    {
                        console.error(err);
                        connection.end();
                        return res.status(500).send();
                    }
                    if(rows.length)
                    {
                        //connection.end();

                        /*var distCoordinate = 0.045;

                        UserStore.findAll({
                            where: {
                                $and: [
                                    {
                                        latitude: {
                                            $gt: newUserLocation.latitude - distCoordinate
                                        }
                                    },
                                    {
                                        latitude: {
                                            $lt: newUserLocation.latitude + distCoordinate
                                        }
                                    },
                                    {
                                        longitude: {
                                            $gt: newUserLocation.longitude - distCoordinate
                                        }
                                    },
                                    {
                                        longitude: {
                                            $lt: newUserLocation.longitude + distCoordinate
                                        }
                                    }
                                ]
                            }
                        })
                        .then((stores) => {*/

                            var result = [];
                        
                            for(var i = 0; i < rows.length; i++) {
                                let distance = Math.round(getDistanceFromLatLonInKm(rows[i].latitude, rows[i].longitude, req.body.latitude, req.body.longitude)  * 1000) / 1000;
        
                                if(distance < req.body.rangeUser) {

                                    connection.query("SELECT * " + 
                                                        "FROM userlocation " + 
                                                        "WHERE TIMESTAMPDIFF(SECOND, date, NOW()) < 300 " +
                                                        "AND storeId = " + rows[i].id, 
                                        function (err, res){
                                            console.log("RES", res);

                                            result.push({
                                                store: rows[i],
                                                nbPersonnes: 0, // Count nbPersonnes in the store
                                                distance: distance
                                            });
                                    });
                                }
                            }
                        //});
    
                        return res.status(200).send(result);
                    } 
                    else 
                    {
                        console.log("no data found");
                        connection.end();
                        return res.status(500).send();
                    }
              });
        });
    };
};
  