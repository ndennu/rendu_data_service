module.exports = (api) => {
    return {
        findOne: require('./findOne')(api),
        searchItems: require('./searchItems')(api)
    };
};
