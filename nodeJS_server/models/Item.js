const Sequelize = require('sequelize');

module.exports = (api) => {
    return api.sequelize.define('Item', {
        id: {
            type: Sequelize.INTEGER,
            autoIncrement: true,
            primaryKey: true
        },
        name: Sequelize.STRING,
        price: Sequelize.DOUBLE,
        categoryId: Sequelize.INTEGER
    }, {
        timestamps: true,
        createdAt: false,
        updatedAt: false,
        deletedAt: false,
        tableName: 'item'
      });
};
