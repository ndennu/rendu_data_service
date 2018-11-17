const Sequelize = require('sequelize');

module.exports = (api) => {
    return api.sequelize.define('Brand', {
        id: {
            type: Sequelize.INTEGER,
            autoIncrement: true,
            primaryKey: true
        },
        name: Sequelize.STRING,
        typeId: Sequelize.INTEGER
    }, {
        timestamps: true,
        createdAt: false,
        updatedAt: false,
        deletedAt: false,
        tableName: 'brand'
      });
};
