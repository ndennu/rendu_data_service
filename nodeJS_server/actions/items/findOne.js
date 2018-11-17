module.exports = (api) => {
    const Item = api.models.Item;

    return (req, res) => {
        let respond = (item) => {
            if(!item){
                res.status(404).send({
                    "returnCode": 404,
                    "errorMessage": "Item not found",
                    "data": null
                });
            } else {
                res.status(200).send({
                    "returnCode": 200,
                    "errorMessage": null,
                    "data": item
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

        Item.findById(req.params.id)
        .then(respond)
        .catch(returnError);
    };
  };
