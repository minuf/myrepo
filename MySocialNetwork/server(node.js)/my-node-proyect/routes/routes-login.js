var User = require('../models/user-model');
var crypto = require('crypto');
var rand = require('csprng');


module.exports = function(app) {

	//POST
	register = function(req, res, callback) {
		console.log('POST');
		console.log(req.body);

		var eml = req.body.email;
		User.find({email:eml}, function(err, users) {
			if (users.length != 0) {
				callback("El usuario ya existe");
			}else {
				var password = req.body.password;
				var device_id = req.body.device_id;

				var temp = rand(160, 36);
				var newpass = temp + password;
				var token = crypto.createHash('sha512').update(eml +rand).digest("hex");
				var hashed_password = crypto.createHash('sha512').update(newpass).digest("hex");

				var newuser = new User({ 
					email: eml,
					profile: {
						first_name: req.body.profile.first_name,
						last_name: req.body.profile.last_name,
						age: req.body.profile.age,
						biography: req.body.profile.biography,
						gender: req.body.profile.gender,
						phone: req.body.profile.phone,
						website: req.body.profile.website
					},
					hashed_password: hashed_password,
					salt :temp,
					token: token,
					device_id: device_id
					 });

				newuser.save(function(err) {
					if (!err) {
						callback({'Registrado con exito ': newuser});
					}
					else console.log('ERROR'+ err);
				});
					}
				});
		

		//callback(newuser);//res.send(newuser); //res.json(newuser); tambien sirve, pero callback lo hace al acabar la tarea
	};

	// login
	login = function(req, res, callback) {
		User.find({email: req.body.email}, function(error, users) {
			if (users.length > 0){
				var password = req.body.password;
				var email = users[0].email;
				var dev_id = req.body.device_id;
				var temp = users[0].salt;
				var hash_db = users[0].hashed_password;
				var id = users[0].token;

				var newpass = temp + password;
				var hashed_password = crypto.createHash('sha512').update(newpass).digest("hex");

				if(hash_db == hashed_password){
					var tmp = rand(160, 36);
					var newpass = tmp + password;
					var token = crypto.createHash('sha512').update(email+tmp).digest("hex");
					var hashed_password = crypto.createHash('sha512').update(newpass).digest("hex");
					User.update({email: req.body.email},
						{$set: {hashed_password: hashed_password,
								salt :tmp,
								token: token,
								device_id: dev_id,
								token_created: Date.now()}
							},function(err, user){
							if(err) callback({'response':"error: "+err, 'res':false});
							else console.log(user);
						});
					callback({'response':"Login Sucess",'res':true,'token':token});
				}else{
					callback({'response':"Invalid Password",'res':false});
				}
			}else{
				callback({'response':"User not exist",'res':false});
			}

		});
	}

	// logout
	logout = function(req, res, callback) {
		User.update({email:req.body.email},
			{$set: {token:"", device_id:""}},
			function(err, updated){
				if(!err)callback({'response':"Logged out", 'res':false});
				else callback({'response':"Error"+err, 'res':false});
			});
	}

	// comprobar(check) token correcto
	checkTokenLogin = function(req, res, callback) {
		var dev_id = req.body.device_id;
		var usrToken = req.body.token;
		var eml = req.body.email;

		User.find({email:eml}, function(err, user) {
			if (user.length > 0) {
				if (user[0].token==usrToken && user[0].device_id==dev_id){
					callback({'response':"Login Correct", 'res':true, 'user_id':user[0]._id});
				}else{					
					User.update({email:eml},
						{$set: {token:"", device_id:""}},
						function(err, user) {
							if(!err) console.log("token expired and deleted");
							else console.log("error "+err);
						});
					callback({'response':"Token expired and deleted",'res':false});
				}
			}else{
				callback({'response':"User not exist",'res':false});
			}
		});
	}



	//register
	app.post('/register', function(req, res) {
		register(req, res, function(found) {
			res.send(found);
		});
	});
	//login
	app.post('/login', function(req, res) {
		login(req, res, function(found) {
			res.json(found);
		});
	});
	//check
	app.post('/check', function(req, res) {
		checkTokenLogin(req, res, function(found) {
			res.json(found);
		});
	});
	//logout
	app.post('/logout', function(req, res) {
		logout(req, res, function(found) {
			res.json(found);
		});
	});
}