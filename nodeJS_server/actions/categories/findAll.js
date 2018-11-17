module.exports = (api) => {
    const Category = api.models.Category;

    return (req, res) => {
        let respond = (category) => {
            if(category.lenght === 0){
                res.status(204).send({
                    "returnCode": 204,
                    "errorMessage": null,
                    "data": category
                });
            } else {
                res.status(200).send({
                    "returnCode": 200,
                    "errorMessage": null,
                    "data": category
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

        Category.findAll()
        .then(respond)
        .catch(returnError);
    };
};
