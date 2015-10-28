var mongoose = require('mongoose');
var Schema = mongoose.Schema;

// contacts collection schema

var contactSchema = mongoose.Schema({// _id is user_id reference to _id in user collection
	contacts: [{role: {type: String, enum:['follower', 'followed', 'friend']}}] // _id in contacts array is the contact_id
});
module.exports = mongoose.model('Contacts', contactSchema); 


