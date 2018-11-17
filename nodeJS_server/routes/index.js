module.exports = (api) => {
    api.use('/auth', require('./auth')(api));
    api.use('/brands', require('./brands')(api));
    api.use('/categories', require('./categories')(api));
    api.use('/items', require('./items')(api));
    api.use('/stores', require('./stores')(api));
    api.use('/users', require('./users')(api));
};
