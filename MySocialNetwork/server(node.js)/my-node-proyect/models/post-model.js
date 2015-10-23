var mongoose = require('mongoose');
var Schema = mongoose.Schema;

// posts collection schema      

var postSchema = mongoose.Schema({ 
	post_type: String,
	post_url: String,
	user_comment: String,
	created: {type:Date, default:Date.now},
	life_time: {type:Date},
	id_user: String
});
module.exports = mongoose.model('Posts', postSchema); 

// likes collection schema      

var likeSchema = mongoose.Schema({ 
	id_post: String,
	id_users: [{type:String}]
});
//module.exports = mongoose.model('Likes', likeSchema); 

// comments collection schema      

var commentSchema = mongoose.Schema({ 
	id_post: String,
	comments: [{
			id_user: String,
			comment: String
		}]
});
//module.exports = mongoose.model('Comments', commentSchema);