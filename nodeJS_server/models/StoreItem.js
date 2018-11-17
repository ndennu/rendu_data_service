const Sequelize = require('sequelize');

module.exports = (api) => {
    return api.sequelize.define('StoreItem', {
        itemId: {
            type: Sequelize.INTEGER,
            primaryKey: true
        },
        storeId: {
            type: Sequelize.INTEGER,
            primaryKey: true
        },
        quantity: Sequelize.INTEGER
    }, {
        timestamps: true,
        createdAt: false,
        updatedAt: false,
        deletedAt: false,
        tableName: 'storeitem'
      });
};
