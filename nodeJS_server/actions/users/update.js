module.exports = (api) => {
  const User = api.models.User;

  return (req, res) => {
      let respond = (result) => {
          res.status(200).send({
              "returnCode": 200,
              "errorMessage": null,
              "data": result
          });
      };

      let returnError = (err) => {
          res.status(500).send({
              "returnCode": 500,
              "errorMessage": JSON.stringify(err),
              "data": null
          });
      };

      User.update(req.body,
          { where: { id: req.params.id }
      }).then(respond)
      .catch(returnError);
  };
};
