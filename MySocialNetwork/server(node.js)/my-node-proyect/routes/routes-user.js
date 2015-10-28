
	var User = require('../models/user-model');


module.exports = function(app) {

	var login = require('./routes-login')(app);

	
/*
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
*/
	//POST (FIND)BY EMAIL
	findByEmail = function(req, res, callback) {
		
		checkTokenLogin(req, res, function(found) {
			var result = found.res;                  
			console.log(result);
			if (result == true){
				User.find({email:req.body.email},
					{hashed_password:0, salt:0, token:0, token_created:0}, //el segundo parametro json es para limitar o excluir los elementos que se mostraran , en este caso, el valor para hassed_password y salt es 0, con lo que no lo incluira en el json resultante.
					function(err, user) { 
						if (!err)callback({"user":user});			
						else console.log('ERROR' + err);
					});
			}else {
				callback("token error: "+result);
			}
		});
	}

	//PUT (Update)profile   //CAUTION: if no especified fields in request (profile.name for example), this REPLACES ALL PROFILE FIELDS
	updateProfile = function(req, res, callback){

		checkTokenLogin(req, res, function(found) {
			var result = found.res;                  
			console.log(result);
			if (result == true){
				User.update({email:req.body.email}, 
					{$set: {profile:req.body.profile}},
					 function(err, user) {
					if(!err) callback({"user": user});
					else callback({"error ":+err});
				});
			}else {
				callback("token error: "+result);
			}
		});
	
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
		/*User.update({email:req.body.email}, 
			{$set: {req.body}},
			 function(err, user) {
			if(!err) callback({"user": user});
			else callback({"error ":+err});
		});*/
	}


	//DELETE
	deleteUser = function(req, res, callback) {
		checkTokenLogin(req, res, function(found) {
			var result = found.res;                  
			console.log(result);
			if (result == true){
				User.remove({email: req.body.email}, function(err) {
					if(!err) callback({"deleted":"ok"});
					else callback('ERROR' +err);
				});
			}else {
				callback("token error: "+result);
			}
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
/*
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
*/
	

	

	//put (update)profile
	app.put('/users/', function(req, res) {
		updateProfile(req, res, function(found) {
			res.send(found);
		});
	});

	//delete user (delete)
	app.delete('/users/', function(req, res) {
		deleteUser(req, res, function(found) {
			res.send(found);
		});
	});

	//get by email...json (find(json)) (read) 
	app.post('/users/', function(req, res) {
		findByEmail(req, res, function(found) {
			res.json(found);
		});
	});	

}