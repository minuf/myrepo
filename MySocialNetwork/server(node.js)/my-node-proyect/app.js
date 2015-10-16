var express         = require("express"),
    app             = express(),
    bodyParser      = require("body-parser"),
    methodOverride  = require("method-override"),
    mongoose        = require('mongoose');

//connection to DB
mongoose.connect('mongodb://localhost/myusersdb', function(err, res) {
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


//without router
app.get('/', function(req, res) {
	res.send('Hola Mundo');
});
//add routes
require('./routes/routes')(app);

app.listen(5000);
console.log('Servidor Express escuchando puerto 5000');