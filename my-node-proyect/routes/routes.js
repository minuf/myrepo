
	var User = require('../models/models');
	var crypto = require('crypto');
	var rand = require('csprng');


module.exports = function(app) {

	

	//GET ALL
	getAllUsers = function(req, res, callback) {
		User.find(function(err, user) {
			if (!err) callback(user);
			else console.log('ERROR' + err);
		});
	};

	//GET BY ID
	findByID = function(req, res, callback) {
		User.findById(req.params.id, function(err, user) {
			if (!err) callback(user);
			else console.log('ERROR' + err);
		});
	};

	//POST (FIND)BY EMAIL
	findByEmail = function(req, res, callback) {
		User.find({email:req.body.email}, function(err, user) {
			if (!err) callback(user);
			else console.log('ERROR' + err);
		});
	};

	//POST
	addUser = function(req, res, callback) {
		console.log('POST');
		console.log(req.body);

		var eml = req.body.email;
		var password = req.body.password;

		var temp = rand(160, 36);
		var newpass = temp + password;
		var token = crypto.createHash('sha512').update(eml +rand).digest("hex");
		var hashed_password = crypto.createHash('sha512').update(newpass).digest("hex");

		var newuser = new User({ 
			token: token,
			email: eml, 
			hashed_password: hashed_password,
			salt :temp });

		newuser.save(function(err) {
			if (!err) {
				//callback({'response':"Sucessfully Registered"});
				console.log('User Guardado');
				console.log('newuser = '+newuser);
			}
			else console.log('ERROR'+ err);
		});

		callback(newuser);//res.send(newuser); //res.json(newuser); tambien sive
	};

	// PUT (Update)
	updatePass = function(req, res, callback) {
		User.find({email:req.params.email}, function(err, user) {
			//user.password = req.body.password;			


			/*user.save(function(err) {
				if(!err) console.log('Password Actualizado');
				else console.log('ERROR:' + err);
			});*/

			callback("\n**** METODO NO IMPLEMENTADO ****\n\n"+user); //res.send(user);
		});

		
	};

	//DELETE
	deleteUser = function(req, res, callback) {
		User.remove({email: req.params.email}, function(err) {
			if(!err) callback('User Borrado');
				else callback('ERROR' +err);
		});
		/*User.findById(req.params.id, function(err, user) {
			user.remove(function(err) {
				if(!err) console.log('User Borrado');
				else console.log('ERROR' +err);
			})

			callback(user); //res.send(user);
		});*/
	};

	// Routes

	//getAll (read)
	app.get('/users', function(req, res) {
		getAllUsers(req, res, function(found) {
			res.send(found);
			//console.log("ALL USERS : "+found);
		});
	});

	//get by id (read)
	app.get('/users/:id', function(req, res) {
		findByID(req, res, function(found) {
			res.send(found);
		});
	});

	//get by email...json (find(json)) (read) 
	app.post('/users/getbyemail/', function(req, res) {
		findByEmail(req, res, function(found) {
			res.send(found);
		});
	});

	//post new user (create)
	app.post('/users', function(req, res) {
		addUser(req, res, function(found) {
			res.send(found);
		});
	});

	//put (update)existent user (update)
	app.put('/users/:email', function(req, res) {
		updatePass(req, res, function(found) {
			res.send(found);
		});
	});

	//delete user (delete)
	app.delete('/users/:email', function(req, res) {
		deleteUser(req, res, function(found) {
			res.send(found);
		});
	});
}