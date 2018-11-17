module.exports = (api) => {
    const UserLocation = api.models.UserLocation;
    const Store = api.models.Store;
    const UserStore = api.models.UserStore;
  
    return (req, res) => {
        let respond = (result) => {
            res.sendStatus(200);
        };
  
        let returnError = (err) => {
            res.status(500).send({
                ErrorCode: 500,
                message: JSON.stringify(err)
            });
        };

        var newUserLocation = {
            idUser: req.body.idUser,
            latitude: req.body.latitude,
            longitude: req.body.longitude,
            datetime: new Date().toUTCString()
        };

        var distCoordinate = 0.00005;

        Store.findAll({
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
        .then((stores) => {
            if(stores.length > 0){
                UserStore.build({
                    storeId: stores[0].id,
                    userId: newUserLocation.idUser,
                    date: newUserLocation.datetime
                })
                .save()
            }
        });

        UserLocation.findById(newUserLocation.idUser)
        .then(() => { 
            UserLocation.build(newUserLocation)
            .save()
            .then(respond)
            .catch(returnError);
        })
        .catch(returnError);
    };
  };
  