var mongoose = require('mongoose');
var Schema = mongoose.Schema;

// users collection schema

var userSchema = mongoose.Schema({ 	
	email: String,
	profile: {
		url_profile_photo: String,
		first_name: String,
		last_name: String,
		age: Date,
		biography: String,
		gender: String,
		phone: String,
		website: String },
	hashed_password: String, 
	salt : String,
	temp_str:String,
	token : String,
	token_created: {type:Date, default:Date.now},
	device_id: String,
	created : {type:Date, default:Date.now}
});
module.exports = mongoose.model('Users', userSchema); 