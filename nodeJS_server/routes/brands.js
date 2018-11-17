const router = require('express').Router();

module.exports = (api) => {

    /* --------------- GET ------------ */

    // FindAll
    router.get('/typeCategory/:typeId',
      api.actions.brands.findAll);


    return router;
};
