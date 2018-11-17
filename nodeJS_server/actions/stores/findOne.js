module.exports = (api) => {
  const Store = api.models.Store;

  return (req, res) => {
      let respond = (store) => {
          if(!store){
              res.status(404).send({
                  "returnCode": 404,
                  "errorMessage": "Store not found",
                  "data": null
              });
          } else {
              res.status(200).send({
                  "returnCode": 200,
                  "errorMessage": null,
                  "data": store
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

      Store.findById(req.params.id)
      .then(respond)
      .catch(returnError);
  };
};
