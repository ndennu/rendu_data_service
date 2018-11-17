module.exports = (api) => {
    return {
      create: require('./create')(api),
        getById: require('./getById')(api),
        update: require('./update')(api),
        updateLocation: require('./updateLocation')(api),
        updateLocationV2: require('./updateLocationV2')(api)
    };
};
