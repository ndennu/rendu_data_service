const express = require('express');
const api = express();
const CronJob = require('cron').CronJob;
const schedule = require('node-schedule');
const fs = require('fs');
const path = require('path');
let filePath = path.join(__dirname, '/extern-data/magasins.json');

let batchStore = require('./actions/batchInsert/stores');
let batchUser = require('./actions/batchInsert/users');
let batchUserLocation = require('./actions/batchInsert/userLocations');
let batchItem = require('./actions/batchInsert/items');
let batchUserStore = require('./actions/batchInsert/userStore');

let dropVisitors = require('./batch/dropVisitors');
let xx = require('./batch/fetchStore');

require('./settings')(api);       console.log('>> Loaded settings');
require('./models')(api);         console.log('>> Loaded models');
require('./middlewares')(api);    console.log('>> Loaded middleware');
require('./actions')(api);        console.log('>> Loaded actions');
require('./routes')(api);         console.log('>> Loaded routes');

console.log(`Server started and listening on port ${api.settings.port}`);
api.listen(api.settings.port);

//xx(api);

/*let cmp = 0;

let fetchStore = () => {
    fs.readFile(filePath, {encoding: 'utf-8'}, function(err, data) {
        if (!err) {
            console.log('received data: ' + data);
        } else {
            console.log(err);
        }
    });
};*/

/*const cron1 = new CronJob('* * * * * *', function() {
    console.log('This is a test batch job started at ' + new Date());
}, null, true, '');*/

/*const cron2 = new CronJob(' * * * * *', function() {
    console.log('POST STORE');
    batchUserStore(api);
}, null, true, '');*/

/*var batch = schedule.scheduleJob(' 0,20,40 * * * * * *', function(){
    if (cmp != 100) {
        console.log('POST ' + new Date());
        batchUserStore(api);
        cmp++;
    } else {
        console.log('FINISH');
        this.cancel();
    }
});*/
