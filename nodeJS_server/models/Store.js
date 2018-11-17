const Sequelize = require('sequelize');

module.exports = (api) => {
    return api.sequelize.define('Store', {
        id: {
            type: Sequelize.INTEGER,
            autoIncrement: true,
            primaryKey: true
        },
        name: Sequelize.STRING, //L1_NORMALISEE
        address: Sequelize.STRING, //L4_NORMALISEE
        postcode: Sequelize.STRING, //CODPOS
        city: Sequelize.STRING, //LIBCOM
        latitude: Sequelize.STRING, //
        longitude: Sequelize.STRING,
        brandId: Sequelize.INTEGER
    }, {
        timestamps: true,
        createdAt: false,
        updatedAt: false,
        deletedAt: false,
        tableName: 'store'
      });
};
