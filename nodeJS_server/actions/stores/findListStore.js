const sequelize = require('sequelize');

module.exports = (api) => {
    const Store = api.models.Store;

    return (req, res) => {
        let respond = (stores) => {
            if(stores.lenght === 0){
                res.status(204).send({
                    "returnCode": 204,
                    "errorMessage": null,
                    "data": stores
                });
            } else {
                res.status(200).send({
                    "returnCode": 200,
                    "errorMessage": null,
                    "data": stores
                });
            }
        };

        let returnError = (err) => {
            res.status(500).send({
                "returnCode": 500,
                "errorMessage": JSON.stringify(err),
                "data": null
            });
        };

        Store.findAll({
            where: { typeId: parseInt(req.params.storeTypeId) },
            attributes: [[sequelize.fn('DISTINCT', sequelize.col('name')), 'name']]
        })
        .then(respond)
        .catch(returnError);
    };
};
