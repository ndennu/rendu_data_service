const scrape = require('website-scraper');
const csv = require('csv-parser');
const utf8 = require('to-utf-8')
const fs = require('fs');
const NodeGeocoder = require('node-geocoder');
let results = [];
let obj;

const typeStore = ["Commerce de détail d'habillement en magasin spécialisé", "Commerce de détail de la chaussure"];

const geocoderOptions = {
    provider: 'opencage',
    httpAdapter: 'https',
    apiKey: '7197ff535cb248e78f3b7bce302ec8ef', // for Mapquest, OpenCage, Google Premier
    formatter: null
};

module.exports = (api) => {

    var geocoder = NodeGeocoder(geocoderOptions);

    const Brand = api.models.Brand;
    const Store = api.models.Store;

    fs.createReadStream('extern-data/data.csv')
    .pipe(utf8())
    .pipe(csv({ separator: ';', raw: true}))
    .on('data', (data) => {
        let d = JSON.stringify(data);
        d = JSON.parse(d);
        results.push(d);
    })
    .on('end', () => {
        console.log(results.length);

        let uniqueArray = results.filter((item, pos) => {
            return results.indexOf(item) == pos;
        });
        
        results.sort(function (a,b) {
            if (a.L1_NORMALISEE < b.L1_NORMALISEE)
                return -1;
            if (a.L1_NORMALISEE > b.L1_NORMALISEE)
                return 1;
            return 0;
        });

        let tmp = "";
        let uniqueBrand = [];

        for(let res of results) {
            if(res.L1_NORMALISEE != tmp) {
                uniqueBrand.push(res);
                tmp = res.L1_NORMALISEE;
            }
        }

        uniqueBrand = uniqueBrand.filter(brand => {
            return brand.LIBAPET === "Commerce de détail d'habillement en magasin spécialisé" || brand.LIBAPET === "Commerce de détail de la chaussure";
        });

        for(let brand of uniqueBrand) {
            Brand.findOrCreate({
                where: {
                    name: brand.L1_NORMALISEE
                },
                defaults: {
                    name: brand.L1_NORMALISEE,
                    typeId: brand.LIBAPET == "Commerce de détail d'habillement en magasin spécialisé" ? 1 : 2
                }
            });
        }

        results.filter(store => {
            return store.LIBAPET === "Commerce de détail d'habillement en magasin spécialisé" || store.LIBAPET === "Commerce de détail de la chaussure";
        });

        var i = 0;

        for(let store of results) {

            let newStore = {
                name: store.L1_NORMALISEE,
                address: store.L4_NORMALISEE,
                postcode: store.CODPOS,
                city: store.LIBCOM,
                latitude: null,
                longitude: null,
                brandId: null
            }

            if(i == 20) {
                geocoder
                    .geocode(newStore.address + " " + newStore.postcode + " " + newStore.city)
                    .then(res => {
                        console.log(res);
                        newStore.latitude = res.latitude;
                        newStore.longitude = res.longitude;

                        Brand.findOne({
                            where: {
                                name: store.L1_NORMALISEE
                            }
                        }).then(brand => {
                            console.log("AZERTYUIOP", brand);
                            newStore.brandId = brand.id;

                            /*Store.findOne({
                                where: {
                                name: newStore.name,
                                latitude: newStore.latitude,
                                longitude: newStore.longitude
                                }
                            }).then(obj => {
                                    if(obj) {
                                        return Store.update(newStore, { 
                                            where: {
                                                id: obj.id
                                            }
                                        })
                                    }
                                    else {
                                        return Store.build(newStore).save();
                                    }
                                }
                            )*/
                        })
                    })
                    .catch(err => {
                        console.log(err);
                    });
            }

            i++;
        }
    });
};
