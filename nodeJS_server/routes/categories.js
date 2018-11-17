const router = require('express').Router();

module.exports = (api) => {

    /* --------------- GET ------------ */

    // FindAll
    router.get('/',
      api.actions.categories.findAll);

    // Get categories by brand 
    router.get('/brand/:brandId',
      api.actions.categories.getCategoriesByBrand);

    return router;
};
