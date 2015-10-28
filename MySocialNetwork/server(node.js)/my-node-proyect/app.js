var express         = require("express"),
    app             = express(),
    bodyParser      = require("body-parser"),
    methodOverride  = require("method-override"),
    mongoose        = require('mongoose');

//connection to DB
mongoose.connect('mongodb://localhost/mysocialnetwork', function(err, res) {
	if (err) console.log('Error conectando a la BD' + err);
	else console.log('Connection a la BD realizada');
});

// Middlewares
app.use(bodyParser.urlencoded({ extended: false }));
app.use(bodyParser.json());
app.use(methodOverride());

// Example Route
/**var router = express.Router();
router.get('/', function(req, res) {
  res.send("Hello world!");
});
app.use(router); */


//add routes here, without reference
app.get('/', function(req, res) {
	res.send('My Social Network Server RUNNING!!!');
});

// redirect example
app.get('/a', function(req, res) {
	res.redirect('/');
});
//add routes reference
require('./routes/routes-user')(app);
require('./routes/routes-contact')(app);
require('./routes/routes-post')(app);

app.listen(5000);
console.log('Servidor Express escuchando puerto 5000');