var appRouter = function (app) {

  app.get("/", function (req, res) {
    res.status(200).send("Welcome to RESTFUL API - NODEJS - TINK39");
  });

}

module.exports = appRouter;