const router = require('express').Router();

module.exports = (api) => {
    /* ------------- POST ----------------*/
    // Sign in
    router.post('/login',
        api.middlewares.bodyParser.json(),
        api.actions.auth.signIn);

    return router;
};
