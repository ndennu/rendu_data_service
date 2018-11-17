const Sequelize = require('sequelize');

module.exports = (api) => {
    return api.sequelize.define('BrandType', {
        id: {
            type: Sequelize.INTEGER,
            autoIncrement: true,
            primaryKey: true
        },
        name: Sequelize.STRING
    }, {
        timestamps: true,
        createdAt: false,
        updatedAt: false,
        deletedAt: false,
        tableName: 'brandtype'
      });
};
