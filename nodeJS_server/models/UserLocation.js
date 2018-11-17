const Sequelize = require('sequelize');

module.exports = (api) => {
    return api.sequelize.define('UserLocation', {
        idUser: {
            type: Sequelize.INTEGER,
            primaryKey: true,
        },
        latitude: Sequelize.STRING,
        longitude: Sequelize.STRING,
        datetime: {
            type: Sequelize.DATE,
            primaryKey: true,
        }
    }, {
        timestamps: true,
        createdAt: false,
        updatedAt: false,
        deletedAt: false,
        tableName: 'userlocation'
    });
};
