const Sequelize = require('sequelize');

module.exports = (api) => {
    api.sequelize =  new Sequelize(
        api.settings.db.sql.database,
        api.settings.db.sql.dbUser,
        api.settings.db.sql.dbUserPassword,
        api.settings.db.sql.sequelizeParamSetting
    );

    let brand = require('./Brand')(api);
    let brandType = require('./BrandType')(api);
    let category = require('./Category')(api);
    let item = require('./Item')(api);
    let store = require('./Store')(api);
    let storeItem =  require('./StoreItem')(api);
    let storeVisitor = require('./StoreVisitor')(api);
    let user = require('./User')(api);
    let userLocation = require('./UserLocation')(api);
    let userStore = require('./UserStore')(api);

    let mysql = require('mysql');

    api.models = {

        Brand: brand,
        BrandType: brandType,
        Category: category,
        Item: item,
        Store: store,
        StoreItem: storeItem,
        StoreVisitor: storeVisitor,
        User: user,
        UserLocation: userLocation,
        UserStore: userStore,

        mysql: mysql
    };
};
