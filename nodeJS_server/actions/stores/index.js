module.exports = (api) => {
    return {
        findOne: require('./findOne')(api),
        findListStore: require('./findListStore')(api),
        findAll: require('./findAll')(api),
        searchStores: require('./searchStores')(api),
        searchStoresV2: require('./searchStoresV2')(api)
    };
};
