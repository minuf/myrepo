var Contact = require('../models/contact-model');
	var crypto = require('crypto');
	var rand = require('csprng');


module.exports = function(app) {

	var login = require('./routes-login')(app);

	//contact and user methods and routes

	//add contact
	addContact = function(req, res, callback) {
		
		checkTokenLogin(req, res, function(found) {
			var result = found.res;
			console.log(result);
			if (result == true){
				var id_user = found.user_id;
				console.log(id_user);
				Contact.update({"id_user":id_user},
					{$addToSet: {contacts:req.body.contact}},{upsert:true},
					function(err, result) {
						if(!err) {
							if (result == 0) {
								var newContact = new Contact({
									id_user: id_user,
									contacts: 
										{
										_id:req.body.contact.id_contact, 
										role:req.body.contact.role
										}
								});
								newContact.save(function(err) {
									if(!err) res.send({"Result":"Insertado con exito"});
									else console.log({"Error":err});
								});
							}
							callback({"Result":result});
						}
						else callback({"Error adding contact ":err});
					});
				
			}else{
				callback("token error: "+result);
			}
		});	
		
	}

	//remove contact
	removeContact = function(req, res, callback) {
		checkTokenLogin(req, res, function(found) {
			var result = found.res;                  
			console.log(result);
			if (result == true){
				var id_user = found.user_id;
				Contact.update( {id_user:id_user},
				 {$pull: {id_contacts:req.body.id_contact}},
				 function(err, user) {
				 	if(!err) callback({'Removed':user, res:true});
				 	else callback({'Error':err});
				 });
			}else{
				callback("token error: "+result);
			}
		});	
	}
	//get contacts
	getContacts = function(req, res, callback) {
		checkTokenLogin(req, res, function(found) {
			var result = found.res;
			console.log(result);
			if (result == true) {
				var id_user = found.user_id;
				Contact.find({id_user:id_user}, function(err, contacts) {
					if (!err) callback(contacts);
					else callback({"error ":err});
				});
			}else{
				callback("token error: "+result);
			}
		});
	}


	//post new contact (create)
	app.post('/contacts', function(req, res) {
		getContacts(req, res, function(found) {
			res.json(found);
		});
	});
	//put contact (create)
	app.put('/contacts', function(req, res) {
		addContact(req, res, function(found) {
			res.json(found);
		});
	});
	//delete contact
	app.delete('/contacts', function(req, res) {
		removeContact(req, res, function(found) {
			res.json(found);
		});
	});
}
	