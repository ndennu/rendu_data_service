module.exports = (api) => {
    const StoreVisitors = api.models.StoreVisitor;


        StoreVisitors
        .destroy({truncate: true, cascade: false});


};
