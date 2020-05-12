var appRouter = function (app) {
  app.get("/", function(req, res) {
    res.status(200).send("Welcome to our restful API");
  });
  app.post("/login", function(req, res) {
    var u = req.body.username;
    var p = req.body.password
    res.status(200).send("Login (" + u + "," + p +")");
  });
  app.post("/register", function(req, res) {
    res.status(200).send("Register .....");
  });
  app.get("/userinfo", function(req, res) {
    res.status(200).send("Getinfo .....");
  });
  app.use(function (req,res) {
		return res.status(404).send("Function not found!");
	});
}

module.exports = appRouter;