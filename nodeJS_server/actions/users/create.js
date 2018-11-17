module.exports = (api) => {
    const User = api.models.User;

    return (req, res) => {
        let isCreate = (user) => {
            if (user) {
                res.status(201).send({
                    "returnCode": 201,
                    "errorMessage": null,
                    "data": user
                });
            }
            else {
                res.status(409).send({
                    "returnCode": 409,
                    "errorMessage": 'User already existing',
                    "data": null
                });
            }
        }

        User
            .build(req.body)
            .save()
            .then(isCreate)
            .catch(error);

        function error(err) {
            res.status(500).send({
                "returnCode": 500,
                "errorMessage": JSON.stringify(err),
                "data": null
            });
        }
    };
};
