module.exports = (api) => {
    return {
        signIn: require('./signIn')(api)
    };
};
