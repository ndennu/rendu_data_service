const Sequelize = require('sequelize');

module.exports = (api) => {
    return api.sequelize.define('User', {
        id: {
            type: Sequelize.INTEGER,
            autoIncrement: true,
            primaryKey: true
        },
        email: Sequelize.STRING,
        password: Sequelize.STRING,
        sexe: Sequelize.BOOLEAN,
        birthdate: Sequelize.TIME,
        size: Sequelize.STRING,
        shoe_size: Sequelize.INTEGER
    }, {
        timestamps: true,
        createdAt: false,
        updatedAt: false,
        deletedAt: false,
        tableName: 'user' // Forcer l'utilisation du nom de la table specifier
    });
};
