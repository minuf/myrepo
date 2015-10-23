
	var User = require('../models/user-model');
	var Contact = require('../models/contact-model');
	var crypto = require('crypto');
	var rand = require('csprng');


module.exports = function(app) {


	addContact = function(req, res, callback) {
		var contact = new Contact({
			id_user: req.body.id_user,
			id_contacts: req.body.id_contacts
		});

		contact.save(function (err) {
			if (!err) callback("SAVED "+contact);
		});
	}
	

	//GET ALL
	getAllUsers = function(req, res, callback) {
		User.find(function(err, users) {
			if (!err) callback({"users":users});
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
			if (!err) callback({"user":user});
			else console.log('ERROR' + err);
		});
	};

	//PUT (Update)first_name
	updateFirstName = function(req, res, callback){
		// este script encuentra un registro en funcion de su email, le asigna la nueva variable a profile.first_name y la guarda (user.save())
		/**User.findOne({email:req.body.email}, function(err, user) {
			user.profile.first_name = req.body.first_name;
			user.save(function(err) {
				if(!err) callback({"user":user});
				else callback({"error":"ERROR: "+err});
			})
		});*/
		
		//este script actualiza el first_name dentro del campo profile, en funcion del email del user
		/**User.update({email:req.body.email}, 
			{$set: {"profile.first_name": req.body.first_name}},
			 function(err, user) {
			if(!err) callback({"user": user});
			else callback({"error ":+err});
		});**/

		//UPDATE FULL USER
		// este script actualiza un User con todos los campos descritos en el objeto json incluido en la consulta(req.body)
		// cuidado con este script..actualiza el user entero
		User.update({email:req.body.email}, 
			{$set: req.body},
			 function(err, user) {
			if(!err) callback({"user": user});
			else callback({"error ":+err});
		});
	}

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
			if(!err) callback({"deleted":"ok"});
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
			res.json(found);
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
			res.json(found);
		});
	});

	//post new user (create)
	app.post('/contacts', function(req, res) {
		addContact(req, res, function(found) {
			res.json(found);
		});
	});

	//put (update)first_name
	app.put('/users/', function(req, res) {
		updateFirstName(req, res, function(found) {
			res.send(found);
		})
	})

	//put (update)existent user (update)  //NO IMPLEMENTADO
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