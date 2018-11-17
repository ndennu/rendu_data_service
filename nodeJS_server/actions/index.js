module.exports = (api) => {
  api.actions = {
      auth: require('./auth')(api),
      brands: require('./brands')(api),
      categories: require('./categories')(api),
      items: require('./items')(api),
      stores: require('./stores')(api),
      users: require('./users')(api)
  };
};
