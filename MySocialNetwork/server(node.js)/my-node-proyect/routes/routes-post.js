
var Post = require('../models/post-model');
var Contact = require('../models/contact-model');
	

module.exports = function(app) {

	var login = require('./routes-login')(app);

	// CREAR POST

	addPost = function(req, res, callback) {
		checkTokenLogin(req, res, function(found) {
			var result = found.res;                  
			console.log(result);
			if (result == true){

				var newPost = new Post({
					post_type: req.body.post_type,
					post_url: req.body.post_url,
					user_comment: req.body.user_comment,
					life_time: req.body.life_time,
					id_user: found.user_id
				});

				newPost.save(function(err, result) {
					if(!err) callback({'Posted':true, 'Result':result});
				});

			}else {
				callback({'response':'error', 'res':false});
			}
		});
	}

	deletePost = function(req, res, callback) {
		checkTokenLogin(req, res, function(found) {
			var result = found.res;
			console.log(result);
			if (result == true) {
				Post.remove({_id:req.body.post_id}, function(err, resu) {
					if (!err) callback({'Deleted':resu});
				});
			}else callback({'response':'error', 'res':false});
		});
	}

	// with token login and email, return all posts from user contacts and sort by created field(Date)
	getPosts = function(req, res, callback) {
		checkTokenLogin(req, res, function(found) {
			var result = found.res;
			console.log(result);
			if (result == true) {
				user_id = found.user_id;
				var qu = Contact.find({_id:user_id}, {'contacts._id':1});
				 qu.exec(function(err, resu) {
					if (!err) {						
						console.log(resu);
						var list = [];
						for (i=0;i<resu[0].contacts.length; i++) {
							//console.log(resu[0].contacts[i]._id);
							list[i] = ""+resu[0].contacts[i]._id;
						}						
						console.log(list);
						//create query
						var q = Post.find({id_user:{$in: list}}).sort({'created': -1}).skip(req.body.skip).limit(req.body.skip+50);
						//execute query
						q.exec(function(err, posts) {
							if (!err) callback({'response':'ok','res':true, 'posts':posts});
							else callback({'response':'error', 'res':false, 'error':err});
						});
						/*
						Post.find({$query:{id_user:{$in: list}}, $orderby: {created:-1}},
						function(err, resu) {
							if(!err) {
								callback(resu);
								console.log({'result':resu});
							}else {
								callback({"error":err});
								console.log(err);
							}
						});	*/					
					} else callback({'response':'error', 'res':false, 'error':err});
				});

			}else callback({'response':'error', 'res':false, 'error':req.body});
		});
	}

	getPost = function(req, res, callback) {
		checkTokenLogin(req, res, function(found) {
			var result = found.res;
			console.log(result);
			if (result == true) {
				Post.find({_id:req.body.post_id}, function(err, post) {
					if(!err) callback(post);
					else callback({'response':'error', 'res':false, 'error':err});
				});
			}else callback({'response':'error', 'res':false});
		});
	}


	app.post('/posts/', function(req, res) {
		getPosts(req, res, function(found) {
			res.json(found);
		});
	});

	app.post('/post/', function(req, res) {
		getPost(req, res, function(found) {
			res.json(found);
		});
	});

	app.put('/post/', function(req, res) {
		addPost(req, res, function(found) {
			res.json(found);
		});
	});

	app.delete('/post/', function(req, res) {
		deletePost(req, res, function(found) {
			res.json(found);
		});
	});
	

}