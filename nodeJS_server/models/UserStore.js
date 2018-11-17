const Sequelize = require('sequelize');

module.exports = (api) => {
    return api.sequelize.define('UserStore', {
        storeId: {
            type: Sequelize.INTEGER,
            primaryKey: true
        },
        userId: {
            type: Sequelize.INTEGER,
            primaryKey: true
        },
        date: {
            type: Sequelize.DATE,
            primaryKey: true
        }
    }, {
        timestamps: true,
        createdAt: false,
        updatedAt: false,
        deletedAt: false,
        tableName: 'userstore'
    });
};
