var express = require("express");
var bodyParser = require("body-parser");
var mongoose = require('mongoose');
var routes = require("./routes/routes.js");
var app = express();

//----Kết nối Mongodb bằng mongoose
var mongodb_url = 'mongodb://localhost:27017/tink40_1';
mongoose.Promise = global.Promise;
var MongoOptions = {
    poolSize: 10,
    useNewUrlParser: true,
    useUnifiedTopology: true
};
mongoose.connect(mongodb_url, MongoOptions);
var db = mongoose.connection;
db.on('error', function (err) {
    console.log('...MONGO EVENT ERROR');
});
db.on('connected', function () {
    console.log('...MONGO EVENT CONNECTED');
    console.log('Database name:' + db.name);
});
//---------------

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));

routes(app);

var server = app.listen(3000, function () {
    console.log("Server running @ port: ", server.address().port);
});