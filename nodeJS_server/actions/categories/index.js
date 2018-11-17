module.exports = (api) => {
    return {
        findAll: require('./findAll')(api),
        getCategoriesByBrand: require('./getCategoriesByBrand')(api)
    };
};
