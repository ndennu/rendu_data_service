module.exports = (api) => {
    return {
        findAll: require('./findAll')(api)
    };
};
