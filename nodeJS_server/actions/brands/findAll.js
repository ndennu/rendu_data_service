module.exports = (api) => {
    const Brand = api.models.Brand;

    return (req, res) => {
        let respond = (brands) => {
            if(brands.lenght === 0){
                res.status(204).send({
                    "returnCode": 204,
                    "errorMessage": null,
                    "data": brands
                });
            } else {
                res.status(200).send({
                    "returnCode": 200,
                    "errorMessage": null,
                    "data": brands
                });
            }
        };

        let returnError = (err) => {
            res.status(500).send({
                "returnCode": 500,
                "errorMessage": null,
                "data": JSON.stringify(err)
            });
        };

        Brand.findAll({
            where: {typeId: req.params.typeId}
        })
        .then(respond)
        .catch(returnError);
    };
};
