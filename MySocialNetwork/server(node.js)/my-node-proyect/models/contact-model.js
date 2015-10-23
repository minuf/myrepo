var mongoose = require('mongoose');
var Schema = mongoose.Schema;

// contacts collection schema

var contactSchema = mongoose.Schema({
	id_user: String,
	contacts: [{
		id_contact: String, role: {type: String, enum:['follower', 'followed', 'friend']}}]
});
module.exports = mongoose.model('Contacts', contactSchema); 


