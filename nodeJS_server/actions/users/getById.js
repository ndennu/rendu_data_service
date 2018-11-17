module.exports = (api) => {
  const User = api.models.User;

  return (req, res) => {
      let respond = (user) => {
          if(!user){
              res.status(404).send({
                  "returnCode": 404,
                  "errorMessage": 'User not found',
                  "data": null
              });
          } else {
              res.status(200).send({
                  "returnCode": 200,
                  "errorMessage": null,
                  "data": user
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

      User.findById(req.params.id)
      .then(respond)
      .catch(returnError);
  };
};
