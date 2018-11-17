const router = require('express').Router();

module.exports = (api) => {

    // get by id
    router.get('/:id',
        api.actions.items.findOne);

    // get with storeId and categoryId
    router.post('/search',
        api.middlewares.bodyParser.json(),
        api.actions.items.searchItems);

    return router;
};