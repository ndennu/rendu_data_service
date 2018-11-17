const router = require('express').Router();

module.exports = (api) => {

    /* --------------- GET ------------ */

    // FindAll
    router.get('/',
      api.actions.stores.findAll);

    // Find list Stores by Store Type
    router.get('/storeList/:storeTypeId',
      api.actions.stores.findListStore);

    // FindOne
    router.get('/:id',
      api.actions.stores.findOne);

    // (peut etre post) get stores with itemId, rangeUser, coordinate, userId,
    // return store list qui possede l'article en stock et nb personne et la distance
    router.post('/search',
      api.middlewares.bodyParser.json(),
      api.actions.stores.searchStores);

    router.post('/searchV2/',
      api.middlewares.bodyParser.json(),
      api.actions.stores.searchStoresV2);


    return router;
};
