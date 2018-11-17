const Sequelize = require('sequelize');

module.exports = (api) => {
    return api.sequelize.define('StoreVisitor', {
        idStore: {type: Sequelize.INTEGER,
        primaryKey: true},
        visitor: Sequelize.INTEGER,
    }, {
        timestamps: true,
        createdAt: false,
        updatedAt: false,
        deletedAt: false,
        tableName: 'storevisitor'
      });
};
