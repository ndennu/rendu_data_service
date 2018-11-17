const router = require('express').Router();

module.exports = (api) => {

    /* --------------- GET ------------ */

    // get by userId
    router.get('/:id',
      api.actions.users.getById);

    /* -------------- PUT -------------- */
    // Update user
    router.put('/:id',
      api.middlewares.bodyParser.json(),
      api.actions.users.update);

    /* -------------- POST ------------ */
    // create user (sign Up)
    router.post('/',
        api.middlewares.bodyParser.json(),
        api.actions.users.create);

    router.post('/location',
        api.middlewares.bodyParser.json(),
        api.actions.users.updateLocation);

    router.post('/locationV2',
        api.middlewares.bodyParser.json(),
        api.actions.users.updateLocationV2);
    return router;
};
