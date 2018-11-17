const geolib = require('geolib');
const Op = require('sequelize').Op;

module.exports = (api) => {
    const UserLocation = api.models.UserLocation;
    const Store = api.models.Store;
    const StoreVisitor = api.models.StoreVisitor;

    return (req, res) => {

        let newUserLocation = {
            idUser: req.body.idUser,
            latitude: req.body.latitude,
            longitude: req.body.longitude,
            datetime: new Date().toUTCString()
        };
        let coordsUser = { latitude: newUserLocation.latitude, longitude: newUserLocation.longitude }
        let currentStore = req.body.currentStore;
        let closestStore = 0;

        UserLocation
            .build(newUserLocation)
            .save()
            .then(getStoreByRange)
            .catch(error);

        function getStoreByRange() {
            Store.findAll({
                where: {
                    latitude: {
                        [Op.gt]: parseFloat(newUserLocation.latitude) - 1,
                        [Op.lt]: parseFloat(newUserLocation.latitude) + 1
                    },
                    longitude: {
                        [Op.gt]: parseFloat(newUserLocation.longitude) - 1,
                        [Op.lt]: parseFloat(newUserLocation.longitude) + 1
                    }
                }
            })
            .then(respond)
            .catch(returnError);
        }

        function respond(stores) {
            let storeMap = [];
            for (s of stores) {
                storeMap.push(s.dataValues);
            }
            if (storeMap.length === 0) {
                updateVisitor(closestStore);
                return;
            }
            let filterStores = closeToShop(storeMap);
            closestStore = getClosestStore(filterStores);
            updateVisitor(closestStore);
        }

        function closeToShop(stores) {
            let result = [];
            for (let s of stores) {
                let tempObj = { latitude: parseFloat(s.latitude), longitude: parseFloat(s.longitude) };

                let isCloseTo = geolib.isPointInCircle(tempObj,
                    coordsUser,
                    200000);

                if (isCloseTo) {
                    result.push(s);
                }
            }
            return result;
        }

        function getClosestStore(storeArray) {

            let distance = 5000;
            let closestStore = null;

            for (let store of storeArray) {
                let tempObj = { latitude: parseFloat(store.latitude), longitude: parseFloat(store.longitude) };

                let d = geolib.getDistanceSimple(coordsUser, tempObj);
                if (d < distance) {
                    distance = d;
                    closestStore = store;
                }
            }
            return closestStore.id;
        }

        function updateVisitor(closestStore) {
            if (currentStore == 0 && closestStore != 0) {
                getClosestListStore();
                sendReturn();
            } else if (currentStore == closestStore) {
                res.status(200).send({
                    "returnCode": 204,
                    "errorMessage": null,
                    "data": closestStore
                });
            } else if (currentStore != 0 && closestStore == 0) {
                getCurrentStore();
                sendReturn();
            } else if (currentStore != closestStore) {
                getCurrentStore();
                getClosestListStore();
                sendReturn();
            }
        }

        function getCurrentStore() {
            StoreVisitor
            .findAll({
                where: {idStore: currentStore}
            })
            .then(buildOrUpdateCurrentStore);
        }

        function buildOrUpdateCurrentStore(store) {
            let visitor = store[0].visitor;
            visitor -= 1;

            StoreVisitor
            .update({visitor: visitor},
                {where: {idStore: currentStore}}
            );
        }

        function sendReturn() {
            res.status(200).send({
                "returnCode": 204,
                "errorMessage": null,
                "data": closestStore
            });
        }

        function getClosestListStore() {
            StoreVisitor
            .findAll({
                where: {idStore: closestStore}
            })
            .then(buildOrUpdateClosestStore);
        }

        function buildOrUpdateClosestStore(stores) {
            if(stores.length === 0) {
                StoreVisitor
                .build({
                    idStore: closestStore,
                    visitor: 1
                })
                .save();
            } else {
                let visitor = stores[0].visitor;
                visitor += 1;

                StoreVisitor
                .update({visitor: visitor},
                    {where: {idStore: closestStore}}
                );
            }
        }

        function returnError(err) {
            res.status(500).send({
                "returnCode": 500,
                "errorMessage": JSON.stringify(err),
                "data": null
            });
        }

        function error(err) {
            console.log(err);
            res.status(500).send({
                "returnCode": 500,
                "errorMessage": JSON.stringify(err),
                "data": null
            });
        }
    };
};
