//
var USERS = require('../models/users');

var appRouter = function (app) {
  //Kiểm tra API có hoạt động hay không
  app.get("/", function(req, res) {
    res.status(200).send("Welcome to our restful API");
  });

  //Hàm hiển thị tất cả users trong Database
  app.get("/users", function(req, res) {
    USERS.find({},function (err,_data) {
    if (err) return res.status(404);
    res.status(200).json({'data':_data});
    });
  });

  //Hàm kiểm tra đăng nhập
  app.post("/login", function(req, res) {
    var u = req.body.username;
    var p = req.body.password;
    USERS.find({username:u,password:p},function (err,_data) {
        if (err) return res.status(404).json({'msg':'Error'});
        //console.log(_data);
        if (!_data || _data.length == 0) res.status(405).json({'msg':'FAILED'});
        else res.status(200).json({'msg':'OKIE'});
    });
  });

  //Hàm đăng ký mới users
  app.post("/register", function(req, res) {
  	var data = {};
  	data.username = req.body.username;
  	data.password = req.body.password;
  	data.fullname = req.body.fullname;
  	
  	//Nên kiểm tra username đã tồn tại hay chưa để tránh việc trùng username
		USERS.create(data,function (err,_data) {
			if (err) return res.status(404).json({'msg':'Error'});
			res.status(200).json({'msg':'Register OKIE'});			
		});
  });

  //Hàm nhận thông tin tài khoản sau khi đã login
  app.get("/userinfo", function(req, res) {
    res.status(200).send("Getinfo .....");
  });
  app.use(function (req,res) {
		return res.status(404).send("Function not found!");
	});
}

module.exports = appRouter;